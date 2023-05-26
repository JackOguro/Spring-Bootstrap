package com.example.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.service.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// 自作バリデーションを作成する場合、ConstraintValidatorインターフェースを実装する
// String: バリデーション対象の型、Form全体にバリデーションをかける場合はObject型を指定
public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {

	private final UserService userService;
	
	public UniqueLoginValidator() {
		this.userService = null;
	}
	
	// コンストラクタが2件以上ある場合、@Autowiredアノテーションは省略できない
	@Autowired
	public UniqueLoginValidator(UserService userService) {
		this.userService = userService;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return userService == null || userService.getLoginUser(value).isEmpty();
	}
}
