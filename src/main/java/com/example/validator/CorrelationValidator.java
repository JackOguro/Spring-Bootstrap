package com.example.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.form.SignupForm;

@Component
public class CorrelationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		// 引数で渡されたFormが入力チェックの対象かを論理値で返す
		return SignupForm.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
		// 対象のFormを取得する
		SignupForm form = (SignupForm) target;
		
		// 値が入っているかの判定
		if(form.getPassword() != null && form.getPassword2() != null) {
			
			// 2つの値が一致しなければ
			if(form.getPassword() != form.getPassword2()) {
				
				// エラーの場合は、引数Errorsインターフェースの
				//「reject」メソッドにエラーメッセージのキーを指定する
				errors.reject("com.example.demo.validatior.CorrelationValidator.message");
			}
		}
	}
}
