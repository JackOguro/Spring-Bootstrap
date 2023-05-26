package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.model.MUser;

@Mapper
public interface SpringMySQLSampleMapper {

	/* ユーザー登録 */
	public int insertOne(MUser user);
	
	/* ユーザー削除(1件) */
	public int deleteOneMUser(@Param("username") String username);
	
	/* ユーザー更新(1件) */
	public void updateOneMUser(@Param("username") String username, 
			@Param("password") String password);
	
	/* ユーザー表示(複数件の場合はリスト型で返す) */
	public List<MUser> findAllUser();
	
	/* ログインユーザーの取得 */
	public Optional<MUser> findLoginUser(String username);
		
	/* 検索条件で一致するユーザーを取得 */
	public List<MUser> searchMUser(MUser user);
}

