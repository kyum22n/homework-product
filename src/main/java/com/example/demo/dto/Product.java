package com.example.demo.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Product {
	private Integer pid;
	private String pcategory;
	private String pname;
	private Integer pprice;
	private Integer pnum;
	private String pdetail;
	private MultipartFile pattach;
	private String pattachoname;
	private String pattachtype;
	private Date pdate;
}
