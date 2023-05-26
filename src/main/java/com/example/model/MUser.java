package com.example.model;

import java.util.Date;

import com.example.util.Authority;

import jakarta.persistence.Version;
import lombok.Data;

@Data
public class MUser {

	private String username;
	private String password;
	private String phoneNumber;
	private String mailAddress;
	private String address;
	private Integer age;
	private Integer gender;
	private String accountIcon;
	private Authority auth;
	private Date createDate;
	
	/* 排他制御を行うためのVersion列 */
	@Version
	private Integer version;
}
