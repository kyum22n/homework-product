package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping("/info")
	public Product productInfo(@RequestParam("pid") int pid) {
		Product product = productService.getProductInfo(pid);
		return product;
	}
	
	@PostMapping("/create")
	public String productCreate(Product product) throws Exception {
		MultipartFile mf = product.getPattach();
		if(mf != null && !mf.isEmpty()) {
			product.setPattachoname(mf.getOriginalFilename());
			product.setPattachtype(mf.getContentType());
		}
		
		productService.createProduct(product);
		
		Product dbProduct = productService.getProductInfo(product.getPid());
		
		return "상품이 등록되었습니다.";
	}
	
	@PutMapping("/update")
	public int productUpdate(@RequestBody Product product) {
		int rows = productService.modifyProduct(product);
		return rows;
	}
	
	@DeleteMapping("/delete")
	public String productDelete(@RequestParam("pid") int pid) {
		productService.removeProduct(pid);
		
		return "상품이 삭제되었습니다.";
	}
	
}
