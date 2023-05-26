package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.MUser;

public interface UserService {
		
	/* ユーザー登録 */
	public void signup(MUser user);
	
	/* ログインユーザー情報取得 */
	public Optional<MUser> getLoginUser(String username);
	
	/* ユーザー情報取得(全件) */
	public List<MUser> getAllUser();
}
