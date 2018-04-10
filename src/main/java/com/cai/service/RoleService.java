package com.cai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cai.dao.RoleMapper;
import com.cai.pojo.Role;

@Service
public class RoleService{

	@Autowired
	private RoleMapper roleMapper;
	
	public Role findById(int id) throws Exception{
		
		return roleMapper.findById(id);
	}

}
