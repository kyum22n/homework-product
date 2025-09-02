package com.example.demo.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.Pager;
import com.example.demo.dto.Product;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;

	@PostMapping("/create")
	public Product productCreate(Product product) throws Exception {
		MultipartFile mf = product.getPattach();
		if (mf != null && !mf.isEmpty()) {
			product.setPattachdata(mf.getBytes());
			product.setPattachoname(mf.getOriginalFilename());
			product.setPattachtype(mf.getContentType());
		}

		productService.createProduct(product);

		Product dbProduct = productService.getProductInfo(product.getPid());

		return dbProduct;
	}

	@GetMapping("/info")
	public Product productInfo(@RequestParam("pid") int pid) {
		Product product = productService.getProductInfo(pid);
		return product;
	}

	@PutMapping("/update")
	public Map<String, Object> productUpdate(Product product) throws Exception {

		Map<String, Object> map = new HashMap<>();

		MultipartFile mf = product.getPattach();
		if (mf != null && !mf.isEmpty()) {
			product.setPattachdata(mf.getBytes());
			product.setPattachoname(mf.getOriginalFilename());
			product.setPattachtype(mf.getContentType());
		}

		Product dbProduct = productService.modifyProduct(product);

		if (dbProduct == null) {
			map.put("result", "fail");
		} else {
			map.put("result", "success");
			map.put("product", dbProduct);
		}

		return map;
	}

	@GetMapping("/temp")
	public String temp() {
		for (int i = 0; i < 10000; i++) {
			Product product = new Product();
			product.setPcategory("카테고리"); // NOT NULL
			product.setPname("상품" + i); // NOT NULL
			product.setPprice(1000 + i); // NOT NULL
			product.setPnum(10 + i); // NOT NULL
			product.setPdate(new Date()); // NOT NULL

			productService.createProduct(product);
		}
		return "1만개 데이터 생성됨";
	}

	@GetMapping("/page")
	public List<Product> page(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		int totalRows = productService.countAll();
		Pager pager = new Pager(10, 10, totalRows, pageNo);
		List<Product> list = productService.getListBypage(pager);
		return list;
	}

	@DeleteMapping("/delete")
	public String productDelete(@RequestParam("pid") int pid) {
		productService.removeProduct(pid);

		return "상품이 삭제되었습니다.";
	}

}
