package com.wingtip.webapi.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.wingtip.webapi.exception.BadRequestException;
import com.wingtip.webapi.model.CartCreateModel;
import com.wingtip.webapi.repository.CartItemRepository;
import com.wingtip.webapi.repository.ProductRepository;
import com.wingtip.webapi.repository.entities.CartItemEntity;
import com.wingtip.webapi.repository.entities.ProductEntity;
import com.wingtip.webapi.services.CartService;
import com.wingtip.webapi.services.dto.CartItemDto;

@Service
public class CartServiceImpl implements CartService{
	private CartItemRepository cartItemRepository;
	@Lazy
	@Autowired
	private ProductRepository productRepository;
	public CartServiceImpl(CartItemRepository cartItemRepository) {
		this.cartItemRepository = cartItemRepository;
	}	

	@Override
	public List<CartItemDto> getCartItems(String cartId) {
		List<CartItemEntity> list = cartItemRepository.findAllByCartId(cartId);
		ModelMapper mapper = new ModelMapper();
		
		return list.stream().map(c -> {
			CartItemDto dto = mapper.map(c, CartItemDto.class);
			return dto;
		}).collect(Collectors.toList());
	}
	@Override
	public CartItemDto createCart(CartCreateModel model) {
		if(model.getQuantity() <= 0) model.setQuantity(1);
		if(model.getProductId() <= 0) throw new BadRequestException("Product Id Required.");
		ProductEntity prod = productRepository.getOne(model.getProductId());
		if(prod == null) {
			throw new BadRequestException("Cannot find the product with id-" + model.getProductId());
		}
		ModelMapper mapper = new ModelMapper();
		CartItemEntity item = null;
		if(model.getId() == null || model.getId().isEmpty()) {
			item = cartItemRepository.getOne(model.getId());
			if(item != null) {
				if(item.getProduct().getId() != model.getProductId()) {
					throw new BadRequestException("Wrong cart item id-"+model.getId());
				}
				item.setQuantity(model.getQuantity());
				cartItemRepository.save(item);
				
				return mapper.map(item, CartItemDto.class);
			}
		}else {
			model.setId(UUID.randomUUID().toString());
		}
		item = mapper.map(model, CartItemEntity.class);
		item.setProduct(prod);
		cartItemRepository.save(item);
		
		return mapper.map(item, CartItemDto.class);
	}
	@Override
	public void updateQuantity(String cartId, String itemId, int quantity) {
		CartItemEntity item = null;
		item = cartItemRepository.getOne(itemId);
		if(item == null) throw new BadRequestException("Wrong cart item id-"+itemId);
		
		if(!item.getCartId().equals(cartId)) throw new BadRequestException("Wrong cart id-"+cartId);
		
		item.setQuantity(quantity);
		cartItemRepository.save(item);
		
	}

	@Override
	public void deleteCartItem(String cartId, String itemId) {
		CartItemEntity item = null;
		item = cartItemRepository.getOne(itemId);
		if(item == null) throw new BadRequestException("Wrong cart item id-"+itemId);
		if(!item.getCartId().equals(cartId)) throw new BadRequestException("Wrong cart id-"+cartId);
		cartItemRepository.delete(item);
	}

	@Override
	public void deleteCart(String cartId) {
		cartItemRepository.deleteByCartId(cartId);
	}
	
}
