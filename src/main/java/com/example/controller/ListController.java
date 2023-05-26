package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ListController {
	
	private final UserService userService;

	@GetMapping("/admin/list") 
	public String getList(Model model) {
								
		// Modelに登録
		model.addAttribute("userList", userService.getAllUser());
		
		return "list";
	}
}
