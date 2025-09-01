package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.Product;

@Mapper
public interface ProductDao {
	public Product selectByPid(int pid);
	public int insertProduct(Product product);
	public int updateProduct(Product product);
	public int deleteProduct(int pid);
}
