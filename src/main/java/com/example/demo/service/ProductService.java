package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDao;
import com.example.demo.dto.Product;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	
	// 상품 조회
	public Product getProductInfo(int pid) {
		Product product = productDao.selectByPid(pid);
		return product;
	}
	
	// 상품 등록
	public void createProduct(Product product) {
		productDao.insertProduct(product);
	}
	
	// 상품 수정
	public int modifyProduct(Product product) {
		int rows = productDao.updateProduct(product);
		return rows;
	}
	
	// 상품 삭제
	public int removeProduct(int pid) {
		int rows = productDao.deleteProduct(pid);
		return rows;
	}
}
