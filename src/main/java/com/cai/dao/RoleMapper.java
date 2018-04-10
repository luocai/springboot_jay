package com.cai.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.cai.pojo.Role;

@Mapper
public interface RoleMapper {
	
	@Select("select * from role where roleId = #{id}")
	public Role findById(int id) throws Exception;
}
