package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dao.ProductDao;
import com.example.demo.dto.Pager;
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
	public Product modifyProduct(Product product) {
    // 기존 데이터 조회
    Product dbProduct = productDao.selectByPid(product.getPid());
    if (dbProduct == null) {
      return null;
    } else {
      // 문자열 필드: null/빈문자/공백만이면 무시, 값이 있으면 기존 값 반영
      if (StringUtils.hasText(product.getPcategory())) {
        dbProduct.setPcategory(product.getPcategory());
      }
      if (StringUtils.hasText(product.getPname())) {
        dbProduct.setPname(product.getPname());
      }
      if (StringUtils.hasText(product.getPdetail())) {
        dbProduct.setPdetail(product.getPdetail());
      }

      // 숫자 필드: primitive int는 null 체크가 불가능 하기 때문에 Dto에 int를 Integer로 변경
      if (product.getPprice() != 0) {
        dbProduct.setPprice(product.getPprice());
      }
      if (product.getPnum() != 0) {
        dbProduct.setPnum(product.getPnum());
      }

    }

    productDao.updateProduct(dbProduct);

    return productDao.selectByPid(product.getPid());
  }

	
	// 상품 삭제
	public int removeProduct(int pid) {
		int rows = productDao.deleteProduct(pid);
		return rows;
	}

	//페이지
	public List<Product> getListBypage(Pager pager){
		List<Product> list = productDao.selectByPage(pager);
		return list;
	}
}
