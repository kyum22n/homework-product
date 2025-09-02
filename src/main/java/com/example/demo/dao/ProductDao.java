package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.Pager;
import com.example.demo.dto.Product;

@Mapper
public interface ProductDao {
	// 상품 등록하기
	public int insertProduct(Product product);
	// 상품 정보 불러오기
	public Product selectByPid(int pid);
	// 상품 정보 수정하기
	public int updateProduct(Product product);
	// 상품 삭제하기
	public int deleteProduct(int pid);

	// 상품 정보 페이지로 불러오기
	public List<Product> selectByPage(Pager pager);
	// 상품 테이블 모든 행 수
	public int countAll();
}
