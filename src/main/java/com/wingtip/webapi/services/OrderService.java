package com.wingtip.webapi.services;

import java.util.List;

import com.wingtip.webapi.models.OrderCreateRequest;
import com.wingtip.webapi.models.OrderUpdateRequest;
import com.wingtip.webapi.services.dto.OrderDto;

public interface OrderService {
	List<OrderDto> findOrderByUserId(String userId);
	OrderDto getOrder(String userId, String orderId);
	OrderDto createOrder(String userId, OrderCreateRequest order);
	OrderDto doPayment(String userId, String orderId, String paymentTransId, double amount);
	OrderDto updateOrder(String userId, String orderId, OrderUpdateRequest order);
	void deleteOrder(String userId, String orderId);
}
