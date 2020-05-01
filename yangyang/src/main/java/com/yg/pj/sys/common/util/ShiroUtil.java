package com.yg.pj.sys.common.util;

import org.apache.shiro.SecurityUtils;

import com.yg.pj.sys.entity.SysUser;

/**通过SecurityUtils 获取用户名字 
 * 在获取名字之前 需要获取用户对象
 * 
 * */
public class ShiroUtil {
	//获取用户名字
	public static String getUsername() {
		String name = getUser().getUsername();
		return name;
	}
	
	public static SysUser getUser() {
		//调用 getSubject() 获取用户对象
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		return user;
	}
	
	

}
