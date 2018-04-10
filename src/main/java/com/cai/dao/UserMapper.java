package com.cai.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cai.pojo.User;

@Mapper
public interface UserMapper {

	@Insert("insert into user(username,password,role) values(#{username},#{password},#{role})")
	public void add(User user)throws Exception;
	
	@Select("select *from user where username=#{name}")
	public User findByName(String name)throws Exception;
}
