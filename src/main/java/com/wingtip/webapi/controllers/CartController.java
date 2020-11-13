package com.wingtip.webapi.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wingtip.webapi.exception.BadRequestException;
import com.wingtip.webapi.model.CartCreateModel;
import com.wingtip.webapi.model.CartResponseModel;
import com.wingtip.webapi.services.CartService;
import com.wingtip.webapi.services.dto.CartItemDto;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
	private CartService cartService;

	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@GetMapping("{cartId}")
	public List<CartResponseModel> getCartItems(@PathVariable String cartId){
		List<CartItemDto> dtoList = cartService.getCartItems(cartId);
		ModelMapper mapper = new ModelMapper();
		PropertyMap<CartItemDto, CartResponseModel> modelMap = new PropertyMap<CartItemDto, CartResponseModel>() { 
			protected void configure() {
				map().setId(source.getId());
				map().setCartId(source.getCartId());
				map().setQuantity(source.getQuantity());
				map().setCreated(source.getCreated());

				map().setProductId(source.getProduct().getId());
				map().setProductName(source.getProduct().getProductName());
				map().setImagePath(source.getProduct().getImagePath());
				map().setUnitPrice(source.getProduct().getUnitPrice());
			} 
		};
		mapper.addMappings(modelMap);
		return dtoList.stream().map(c -> { 
			CartResponseModel vo = mapper.map(c, CartResponseModel.class);
			return vo;
		}).collect(Collectors.toList());
	}

	@PostMapping
	public ResponseEntity<CartReturnModel> createCart(@Valid @RequestBody CartCreateModel cartRequest) {
		CartItemDto dto = cartService.createCart(cartRequest);
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{cartId}")
				.buildAndExpand(dto.getCartId()).toUri();
		
		return ResponseEntity.created(location)
	              .body(new CartReturnModel(dto.getCartId()));
	}
	
	@PatchMapping
	public void patchCart(@RequestBody CartItemDto dto) {
		String cartId = dto.getCartId();
		String itemId = dto.getId();
		int quantity = dto.getQuantity();
        if (cartId == null || itemId == null || quantity <= 0)
        {
            throw new BadRequestException("Not enough information to update.");
        }
        cartService.updateQuantity(cartId, itemId, quantity);
	}
	@DeleteMapping("{cartId}")
	public void deleteCart(@PathVariable String cartId) {
		cartService.deleteCart(cartId);
	}
	@DeleteMapping("{cartId}/{itemId}")
	public void deleteCart(@PathVariable String cartId, @PathVariable String itemId) {
		cartService.deleteCartItem(cartId, itemId);
	}
}
class CartReturnModel{
	private String cartId;

	public CartReturnModel(String cartId) {
		super();
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
}
