package kr.ac.hansung.cse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.ac.hansung.cse.model.Cart;
import kr.ac.hansung.cse.model.CartItem;
import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.CartItemService;
import kr.ac.hansung.cse.service.CartService;
import kr.ac.hansung.cse.service.ProductService;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api/cart")
public class CartRestController {

	@Autowired
	private CartService cartService;

	@Autowired
	private CartItemService cartItemService;
	
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public ResponseEntity<Cart> getCartById(@PathVariable(value = "cartId") int cartId) {
		
		Cart cart = cartService.getCartById(cartId);
		return new ResponseEntity<Cart>(cart, HttpStatus.OK);
		
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> clearCart(@PathVariable(value = "cartId") int cartId) {
		
		Cart cart = cartService.getCartById(cartId);
		cartItemService.removeAllCartItems(cart);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
	}

	@RequestMapping(value = "/cartItem/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> addItem(@PathVariable(value = "productId") int productId) {

		Product product = productService.getProductById(productId);
		
		Cart cart = cartService.getCartById(1);  // temporary

		
		List<CartItem> cartItems = cart.getCartItems();

		// check if cartitem for a given product already exists
		// 카트에 해당 제품이 존재하는지 체크해서 존재하면 수량만 1 증가 시킴 
		for (int i = 0; i < cartItems.size(); i++) {
			if (product.getId() == cartItems.get(i).getProduct().getId()) {
				CartItem cartItem = cartItems.get(i);
				if(cartItem.getQuantity() < product.getUnitInStock())
					cartItem.setQuantity(cartItem.getQuantity() + 1);
					
				else
					return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				
				cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
				
				cartItemService.addCartItem(cartItem);

				return new ResponseEntity<Void>(HttpStatus.OK);
			}
		}

		// create new cartItem
		// 카트에 해당 제품이 존재하지 않으면 새롭게 추가하고 수량을 1로 설정해줌 
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(1);
		cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
		
		cartItem.setProduct(product);
		cartItem.setCart(cart);
		
		cartItemService.addCartItem(cartItem);

		// bidirectional
		cart.getCartItems().add(cartItem);		

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cartItem/{productId}", method = RequestMethod.PATCH)
	   public ResponseEntity<Void> minusItem(@PathVariable(value = "productId") int productId) {

	      Product product = productService.getProductById(productId);
	      
	      Cart cart = cartService.getCartById(1); 

	      List<CartItem> cartItems = cart.getCartItems();

	      for (int i = 0; i < cartItems.size(); i++) {
	         if (product.getId() == cartItems.get(i).getProduct().getId()) {
	            
	            CartItem cartItem = cartItems.get(i);
	            
	            if (cartItem.getQuantity() == 0) {
	               return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	            }
	            
	            cartItem.setQuantity(cartItem.getQuantity() - 1);
	            cartItem.setTotalPrice(product.getPrice() * cartItem.getQuantity());
	            
	            cartItemService.addCartItem(cartItem);

	            return new ResponseEntity<Void>(HttpStatus.OK);
	         }
	      }   

	      return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	   }

	@RequestMapping(value = "/cartItem/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeItem(@PathVariable(value = "productId") int productId) {

		Cart cart = cartService.getCartById(1);  // temporary

		CartItem cartItem = cartItemService.getCartItemByProductId(cart.getId(), productId);
		cartItemService.removeCartItem(cartItem);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}

}
