package com.cai.shiro;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cai.pojo.Role;
import com.cai.pojo.User;
import com.cai.service.RoleService;
import com.cai.service.UserService;

@Component
public class ShiroRealm extends AuthorizingRealm{

 // 不用autowired
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	//权限认证
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		
		String username = (String) getAvailablePrincipal(principalCollection);
		Role role= null;
		
		try{
			User user = userService.findByName(username);
			role = roleService.findById(user.getRole());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		Set<String> r = new HashSet<>();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		if (role != null){
			r.add(role.getRoleName());
			info.setRoles(r);
		}
		return info;
	}
	
	//登录验证 身份验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		String username = (String) token.getPrincipal();
		String password = new String((char[])token.getCredentials());
		
		User user = null;
		try{
System.out.println(username);
			user = userService.findByName(username);
System.out.println(user);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		if (user == null){
			/*throw new UnknownAccountException();*/
System.out.println("用户不存在");
		}else if (!user.getPassword().equals(password)){
		/*	throw new IncorrectCredentialsException();*/
System.out.println("密码错误");
		}
		
		AuthenticationInfo info = new SimpleAuthenticationInfo(username, password, getName());
		
		return info;
	}

}
