package com.wingtip.webapi.services;

import java.util.List;

import com.wingtip.webapi.model.CartCreateModel;
import com.wingtip.webapi.services.dto.CartItemDto;

public interface CartService {
	List<CartItemDto> getCartItems(String cartId);
	CartItemDto createCart(CartCreateModel model);
	void updateQuantity(String cartId, String itemId, int quantity);
	void deleteCartItem(String cartId, String itemId);
	void deleteCart(String cartId);
}
