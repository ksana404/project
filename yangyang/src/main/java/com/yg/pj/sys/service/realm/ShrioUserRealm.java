package com.yg.pj.sys.service.realm;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yg.pj.sys.dao.SysMenuDao;
import com.yg.pj.sys.dao.SysRoleMenuDao;
import com.yg.pj.sys.dao.SysRoleUserDao;
import com.yg.pj.sys.dao.SysUserDao;
import com.yg.pj.sys.entity.SysUser;

@Service
public class ShrioUserRealm extends AuthorizingRealm {
	
	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysRoleUserDao sysRoleUserDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	//SysRoleUserDao
	@Autowired
	private SysMenuDao sysMenuDao;


	
	
	@Override    //构建凭证匹配对象   
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		//构建凭证匹配对象
		HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
		//设置加密算法  盐值从用户  在数据库查询得到
		cMatcher.setHashAlgorithmName("MD5");
		//设置加密次数
		cMatcher.setHashIterations(1);
		
		//System.out.println(" 1----    setCredentialsMatcher");
		//
		super.setCredentialsMatcher(cMatcher);
	
	}
	
	
	
	
	/**通过此方法完成授权信息的获取以及封装
	 *    授权		授权		授权			授权
	 * */
	@Override // 授权  对一些方法进行功能增强  @RequiresPermissions("sys:user:valid")
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//1.获取登录用户信息，例如用户id
		SysUser user = (SysUser) principals.getPrimaryPrincipal();
		//结果校验
		if(user ==null)
			throw new UnknownAccountException();
		if(user.getId() ==null)
			throw new UnknownAccountException();
		
		//2.基于用户id获取用户拥有的角色(sys_user_roles)
		 List<Integer> rouleIds =sysRoleUserDao.findRoleIdsByUserId(user.getId());
		//结果校验
		 if(rouleIds ==null ||rouleIds.size()==0)
				throw new UnknownAccountException();
		//3.基于角色id获取菜单id(sys_role_menus)
		//sysRoleMenuDao
		
		 java.util.List<Integer> menusIds= sysRoleMenuDao.findMenuIdsByRoleIds(rouleIds);
		 
		//4.基于菜单id获取权限标识(sys_menus)
		List<String> permission =sysMenuDao.findPermission(menusIds);
		 System.out.println("打印授权标识 permission："+permission.toString());
		 
		//5.对权限标识信息进行封装并返回
		SimpleAuthorizationInfo info =new SimpleAuthorizationInfo();
		info.addStringPermissions(permission);
		
		return info; //返回给授权管理器
	}

	
	

	/**
	 * 通过此方法完成认证数据的获取及封装,系统
	 * 底层会将认证数据传递认证管理器，由认证
	 * 管理器完成认证操作。
	 */
	/**
	 * AuthenticationToken token 有Controller中
	 * token --> SecurityManager--> AuthenticationInfo 认证管理器--> -->
	 *
	 */
	@Override  //认证管理对象 参数从 容器 中自动注入 获取   上一级处理是 Cotroller
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
			throws AuthenticationException {
		//System.out.println(" 4----    doGetAuthenticationInfo");
		
		//1.获取用户名(用户页面输入)  自动注入
		UsernamePasswordToken upTaken=(UsernamePasswordToken)token;
		String username = upTaken.getUsername();
		
		//2.基于用户名查询用户信息
		SysUser user = sysUserDao.findUserByUserName(username);
		
		//3.判定用户是否存在
		if(user==null)
			throw new UnknownAccountException("该用户已经不存在");
		//4.判定用户是否已被禁用。
		if(user.getValid()==0)
			throw new LockedAccountException("该用户已经被禁用");
		//5.封装用户信息
		ByteSource bytes =ByteSource.Util.bytes(user.getSalt() );
		//记住：构建什么对象要看方法的返回值
		
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
				user, //身份
				user.getPassword(),  // 用户数据库查询出来的加密 密文   已加密
				bytes,  //  加密盐值  数据库是 varchar
				getName());  // 用户名字
		//6.返回封装结果
		//返回值会传递给认证管理器(后续
				//认证管理器会通过此信息完成认证操作)
		
	/*
	 	SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
	 				principal,   //身份
				hashedCredentials,    //加密信息
				credentialsSalt,    //盐值   凭证盐
				realmName);    // 用户真实名字
	 * */
		
		return info;
	}






	

}


















