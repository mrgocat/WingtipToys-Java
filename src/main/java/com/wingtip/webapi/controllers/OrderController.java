package com.wingtip.webapi.controllers;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wingtip.webapi.exception.AuthException;
import com.wingtip.webapi.models.OrderCreateRequest;
import com.wingtip.webapi.models.OrderPaymentRequest;
import com.wingtip.webapi.models.OrderUpdateRequest;
import com.wingtip.webapi.services.OrderService;
import com.wingtip.webapi.services.dto.OrderDto;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {	
	private OrderService service;
	
	public OrderController(OrderService service) {
		this.service = service;
	}
	@GetMapping
	public List<OrderDto> getOrders() {
		String userId = getUserId();
		return service.findOrderByUserId(userId);
	}
	@GetMapping("{orderId}")
	public OrderDto getOrder(@PathVariable String orderId) {
		String userId = getUserId();
		return service.getOrder(userId, orderId);
	}
	@PostMapping
	public OrderDto createOrder(@RequestBody OrderCreateRequest order) {
		String userId = getUserId();
		if(order.getCartId() == null) order.setCartId(userId);
		OrderDto dto = service.createOrder(userId, order);
		return dto;
	}
	@PostMapping("{orderId}/dopayment")
	public OrderDto doPayment(@PathVariable String orderId, @RequestBody OrderPaymentRequest payment) {
		String userId = getUserId();
		OrderDto dto = service.doPayment(userId, orderId, payment.getPaymentTransId(), payment.getAmount());
		return dto;
	}
	@PutMapping("{orderId}")
	public OrderDto updateOrder(@PathVariable String orderId, @RequestBody OrderUpdateRequest order) {
		String userId = getUserId();
		OrderDto dto = service.updateOrder(userId, orderId, order);
		return dto;
	}
	@DeleteMapping("{orderId}")
	public void deleteOrder(@PathVariable String orderId) {
		String userId = getUserId();
		service.deleteOrder(userId, orderId);
		
	}
	private String getUserId() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null) {
			throw new AuthException("No auth info. null");
		}
		UsernamePasswordAuthenticationToken userInfo = (UsernamePasswordAuthenticationToken)auth;
		if(userInfo.getPrincipal() == null) {
			throw new AuthException("No auth info. no principal");
		}
		return (String)userInfo.getPrincipal();
	}	
}
