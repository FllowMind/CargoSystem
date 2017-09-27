package com.itran.cargosystem.function.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class Realm extends AuthorizingRealm {
	
	//用于认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// token是用户输入的
		// 第一步从token中取出身份信息
		String username = (String) token.getPrincipal();

		// 第二步：根据用户输入的userCode从数据库查询
		// ....
	

		// 如果查询不到返回null
		//数据库中用户账号是zhangsansan
		if(!username.equals("zhangsansan")){//
			return null;
		}
		
		
		// 模拟从数据库查询到密码
		String password = "111112";

		// 如果查询到返回认证信息AuthenticationInfo

		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
				username, password, this.getName());

		return simpleAuthenticationInfo;
	}

	//用于授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		
		return null;
	}

	

}
