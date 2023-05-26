package com.example.form;


import java.util.Objects;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import com.example.validator.UniqueLogin;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupForm {
	
	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp="^[a-z0-9_]+$", groups = ValidGroup2.class, 
		message="使用できる文字は半角英数字(大文字は禁止)とアンダースコア(_)のみです")
	@Length(min=4, max=15, groups = ValidGroup2.class)
	@UniqueLogin(groups = ValidGroup2.class) // 自作のバリデーション
	private String username;
	
	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp="^(?=.*[A-Z])[a-zA-Z0-9_]+$", groups = ValidGroup2.class,
			message="1文字以上大文字を含む半角英数字で入力してください")
	@Length(min=6, max=16, groups = ValidGroup2.class)
	private String password;
	
	@NotBlank(groups = ValidGroup1.class)
	private String password2;
	
	@AssertTrue(message="パスワートが一致しません", groups = ValidGroup2.class)
	public boolean isCheck() {
		if(password != null && password2 != null) {
				return Objects.equals(password, password2);
		}
		return false;
	}
	
	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	private String mailAddress;
	
	@NotBlank(groups = ValidGroup1.class)
	@Pattern(regexp="0\\d{1,4}-\\d{1,4}-\\d{4}", groups = ValidGroup2.class,
			message="電話番号の形式で入力してください")
	private String phoneNumber;
	
	@Size(max=50, groups = ValidGroup2.class)
	private String address1;
	
	private String address2;
	
	@Max(value=80, groups = ValidGroup2.class)
	@Min(value=10, groups = ValidGroup2.class)
	private Integer age;
	
	private Integer gender;
	
	private MultipartFile accountIcon;
	
}
