package kr.ac.hansung.cse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

@Controller 
public class ProductController { // controller가 service 호출, service가 dao 호출

	@Autowired
	private ProductService productService;
	
	// " " 안의 경로로 리퀘스트가 들어오면 해당 함수(getProducts)가 실행되고 
	@RequestMapping("/products")
	public String getProducts(Model model) {
		
		// Service 레이어에 있는 getProducts()를 호출해서 Product형 리스트로 가져와 model에 저장한다. 
		List<Product> products = productService.getProducts();
		model.addAttribute("products", products);
		
		// " " 안은 view의 logical name로써 예전에는 /products 로 리퀘스트가 들어오면 products.jsp로 넘어간다.
		// 이젠 tiles를 사용하기때문에 products라는 definition으로 넘어간다.
		return "products";
	
	}
	
	// " " 안의 경로로 리퀘스트가 들어오면 해당 함수(getProducts)가 실행되고 
		@RequestMapping("/products/viewProduct/{id}")
		public String getProductDetail(@PathVariable int id, Model model) {
			// PathVariable 어노테이션을 사용하면 RequestMapping value의 url의 {id}를 메서드 파라미터에 스프링이
			// 넣어준다.
			
			// Service 레이어에 있는 getProductById()를 호출해서 Product를 가져와 model에 저장한다. 
			// DB에서 해당 제품의 필드 내용들을 가져온다
			Product product = productService.getProductById(id);

			// 가져온 product 내용을 model에 넣어준다.
			model.addAttribute("product", product);

			
			// " " 안은 view의 logical name로써 예전에는 /products 로 리퀘스트가 들어오면 products.jsp로 넘어간다.
			// 이젠 tiles를 사용하기때문에 products라는 definition으로 넘어간다.
			return "viewProduct";
		
		}
}
