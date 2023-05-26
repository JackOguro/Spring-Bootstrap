package com.example.application.service;

import org.springframework.web.multipart.MultipartFile;

public interface ApplicationService {
		
	/* アップロード実行処理 */
	public String uploadFile(MultipartFile multipartFile);
	
}
