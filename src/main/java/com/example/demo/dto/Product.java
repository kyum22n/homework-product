package com.example.demo.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class Product {
	private int pid;
	private String pcategory;
	private String pname;
	private int pprice;
	private int pnum;
	private String pdetail;
	private MultipartFile pattach;
	private String pattachoname;
	private String pattachtype;
	private Date pdate;
}
