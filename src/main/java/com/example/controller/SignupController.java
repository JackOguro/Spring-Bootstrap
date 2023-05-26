package com.example.controller;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.application.service.ApplicationService;
import com.example.form.GroupOrder;
import com.example.form.SignupForm;
import com.example.model.MUser;
import com.example.service.UserService;
import com.example.util.Authority;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignupController {
	
	private final ModelMapper modelMapper;
	
	private final ApplicationService applicationservice;
	
	private final UserService userService;
	
	// 相関チェックのインジェクション
	//private final CorrelationValidator validator;

	@GetMapping("/signup") 
	public String getSignup(Model model, @ModelAttribute SignupForm form) {
		return "/signup";
	}
	
	@PostMapping("/signup")
	public String postSignup(Model model, @Validated(GroupOrder.class) @ModelAttribute SignupForm form,
			BindingResult bindingResult) {
				
		// 入力チェック
		if(bindingResult.hasErrors()) {
			
			// エラーが発生したので登録画面に戻る
			return getSignup(model, form);
		}
		
		// 本日のシステム日付を取得
		Date now = new Date();
		
		// 曖昧な一致は無視する
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		
		// formをMUserクラスに変換
		MUser user = modelMapper.map(form, MUser.class);
		
		// 住所を繋げて格納
		user.setAddress(form.getAddress1() + form.getAddress2());
	
		// アイコンが設定されていれば
		if(!form.getAccountIcon().isEmpty()) {
			
			// アイコン画像の格納(指定のパスを格納)
			user.setAccountIcon(applicationservice.uploadFile(form.getAccountIcon()));
		// アイコンが設定されていなければ
		} else {
			user.setAccountIcon("/img/default.png");
		}
		
		
		// ユーザー権限(全員をUSERとして登録)
		user.setAuth(Authority.USER);
		
		// Version
		user.setVersion(0);
		
		// アカウント作成日時
		user.setCreateDate(now);
		
		// ユーザー登録
		userService.signup(user);
		
		// ログイン画面にリダイレクト
		return "redirect:/login?register";
		
	}
	
	// 相関チェックtの登録
//	@InitBinder("signupForm")
//	public void initBinder(WebDataBinder webDataBinder) {
//		
//		// WebDataBinderインターフェースのaddValidatorsメソッドで相関チェックを登録できる
//		webDataBinder.addValidators(validator);
//	}
}
