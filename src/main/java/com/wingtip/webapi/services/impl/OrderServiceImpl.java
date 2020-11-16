package com.wingtip.webapi.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wingtip.webapi.exception.AuthException;
import com.wingtip.webapi.exception.BadRequestException;
import com.wingtip.webapi.exception.ResourceNotFoundException;
import com.wingtip.webapi.models.OrderCreateRequest;
import com.wingtip.webapi.models.OrderUpdateRequest;
import com.wingtip.webapi.repository.CartItemRepository;
import com.wingtip.webapi.repository.OrderRepository;
import com.wingtip.webapi.repository.entities.CartItemEntity;
import com.wingtip.webapi.repository.entities.OrderDetailEntity;
import com.wingtip.webapi.repository.entities.OrderEntity;
import com.wingtip.webapi.repository.entities.ProductEntity;
import com.wingtip.webapi.services.OrderService;
import com.wingtip.webapi.services.dto.OrderDto;

@Service
public class OrderServiceImpl implements OrderService {
	private CartItemRepository cartRepository;
	private OrderRepository orderRepository;

	public OrderServiceImpl(OrderRepository orderRepository, CartItemRepository cartRepository) {
		this.orderRepository = orderRepository;
		this.cartRepository = cartRepository;
	}
	@Override
	public List<OrderDto> findOrderByUserId(String userId) {
	//	List<OrderEntity> list = orderRepository.findByUserIdNotCancelled(userId);
		List<OrderEntity> list = orderRepository.findByUserId(userId, Sort.by(Sort.Direction.DESC, "orderDate"));
		return list.stream().map(entity -> {
			OrderDto dto = new OrderDto();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		}).collect(Collectors.toList());
	}
	@Override
	public OrderDto getOrder(String userId, String orderId) {
		OrderEntity entity = null;
		Optional<OrderEntity> optional = orderRepository.findById(orderId);
		if(optional.isPresent()) entity = optional.get();

		if(entity == null) throw new ResourceNotFoundException("Cannot find order with id-"+orderId);
		if(!entity.getUserId().equals(userId)) new AuthException("You are not authorized to for this order");
		OrderDto dto = new OrderDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	@Override
	@Transactional
	public OrderDto createOrder(String userId, OrderCreateRequest order) {
		String cartId = order.getCartId();
		List<CartItemEntity> cartList = cartRepository.findAllByCartId(cartId);
		if(cartList.size() == 0) throw new BadRequestException("Cannot find cart data with cartId-"+cartId);

		double total = 0;
		OrderEntity entity = new OrderEntity();
		BeanUtils.copyProperties(order, entity);
		List<OrderDetailEntity> detailList = new ArrayList<>();
		for(CartItemEntity cart  : cartList){
			if(cart.getProduct() == null) {
				//cartRepository.delete(cart);
				throw new BadRequestException("Cannot find product in cart.");
			}
			ProductEntity product = cart.getProduct();
			total += product.getUnitPrice() * cart.getQuantity();
			OrderDetailEntity item = new OrderDetailEntity();
			item.setUnitPrice(product.getUnitPrice());
			item.setQuantity(cart.getQuantity());
			item.setProduct(product);
		//	item.setOrder(entity);
			detailList.add(item);			
		}
		if(total != order.getTotal()) {
			throw new BadRequestException("total money is not equal. calculated-" + total + ", requestd-" + order.getTotal());
		}
		String orderId = UUID.randomUUID().toString();
		
		entity.setOrderId(orderId);
		entity.setUserId(userId);
		entity.setStatus(OrderDto.ORDER_STATUS.CREATED.toString());
		entity.setShiped(false);
		
		entity.setOrderDetails(detailList);
		
		entity.setOrderDate(new Date());
		
		entity = orderRepository.save(entity);
		cartRepository.deleteInBatch(cartList);
		
		OrderDto dto = new OrderDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
	@Override
	public OrderDto doPayment(String userId, String orderId, String paymentTransId, double amount) {
		OrderEntity entity = null;
		Optional<OrderEntity> optional = orderRepository.findById(orderId);
		if(optional.isPresent()) entity = optional.get();
		if(entity == null) throw new ResourceNotFoundException("Cannot find order with id-" + orderId);
		if(!entity.getUserId().equals(userId)) new AuthException("You are not authorized to for this order");
		if(entity.getTotal() != amount) throw new BadRequestException("Paid amount is not match to total amount-" + entity.getTotal());
		if(!entity.getStatus().equals(OrderDto.ORDER_STATUS.CREATED.toString())) {
			throw new BadRequestException("Invalid order status-" + entity.getState());
		}
		
		paymentTransId = checkTransaction(orderId, paymentTransId, amount);
		
		entity.setPaymentTransId(paymentTransId);
		entity.setStatus(OrderDto.ORDER_STATUS.CREATED.toString());
		entity = orderRepository.save(entity);

		OrderDto dto = new OrderDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
	private String checkTransaction(String orderId, String paymentTransId, double amount) {
		if(paymentTransId == null) paymentTransId = UUID.randomUUID().toString();
		return paymentTransId;
	}
	@Override
	public OrderDto updateOrder(String userId, String orderId, OrderUpdateRequest order) {
		OrderEntity entity = null;
		Optional<OrderEntity> optional = orderRepository.findById(orderId);
		if(optional.isPresent()) entity = optional.get();

		if(entity == null) throw new ResourceNotFoundException("Cannot find order with id-" + orderId);
		if(!entity.getUserId().equals(userId)) new AuthException("You are not authorized to for this order");
		if(entity.isShiped()) throw new BadRequestException("The order has allready been shipped. Please contact help desk.");
		BeanUtils.copyProperties(order, entity);
		
		entity = orderRepository.save(entity);
		OrderDto dto = new OrderDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}

	@Override
	public void deleteOrder(String userId, String orderId) {
		OrderEntity entity = null;
		Optional<OrderEntity> optional = orderRepository.findById(orderId);
		if(optional.isPresent()) entity = optional.get();

		if(entity == null) throw new ResourceNotFoundException("Cannot find order with id-" + orderId);
		if(!entity.getUserId().equals(userId)) new AuthException("You are not authorized to for this order");
		if(entity.isShiped()) throw new BadRequestException("The order has allready been shipped. Please contact help desk.");
		
		orderRepository.delete(entity);
		//	entity.setStatus(OrderDto.ORDER_STATUS.CANCELED.toString());
		//	orderRepository.save(entity);
	}
}
