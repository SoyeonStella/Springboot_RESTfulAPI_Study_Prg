package kr.ac.hansung.cse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

	@RequestMapping
	public String getCart(Model model) {
		
		model.addAttribute("cartId", 1);
		
		return "cart";
		
	}
	
}
