package com.example.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * @Target:     このアノテーションが使える場所を指定する
 * 			     ElementType.METHODとElementType.FIELDを指定するとメソッドとフィールドで使える
 * @Retention:  このアノテーションの保持期間を指定する
 * 			     RetentionPolicy.RUNTIMEを指定すると、実行時にVMに保持される = いつでも使える
 * @Constraint: このアノテーションの制約対処を指定する
 * @interface:  インターフェースに付ける。このアノテーションを付けると名前をアノテーションとして使用できる
 * 	             @interface UniqueLoginと宣言すると、@UniqueLoginアノテーションが使用できるようになる
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueLoginValidator.class)
public @interface UniqueLogin { 
	String message() default "";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};
}
