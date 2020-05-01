package com.yg.pj.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yg.pj.sys.common.util.ShiroUtil;
import com.yg.pj.sys.common.vo.JsonResult;
import com.yg.pj.sys.common.vo.PageObject;
import com.yg.pj.sys.common.vo.SysUserDeptVo;
import com.yg.pj.sys.entity.SysUser;
import com.yg.pj.sys.service.SysUserService;

/**
 * 本模块通过业务层对象执行业务逻辑， 再通过VO对象封装响应结果(主要对业务层数据添加状态信息)， 最后将响应结果转换为JSON格式的字符串响应到客户端。
 * 
 * @author Administrator 角色模块的控制层
 *
 */
@RequestMapping("/user/")
@RestController
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;

	
	/**页面展现  查询数据 根据用户名和当前页数查询数据
	 * url:http://localhost:9099/user/doFindPageObjects?pageCurrent=1&username=
	 * 返回一个   页面    records 用户部门vo对象集合  List<SysUserDeptVo>
	 * */
	/**
	 * @param pageCurrent
	 * @param username
	 * @return PageObject<SysUserDeptVo> page
	 */
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(Integer  pageCurrent,String username) {

		PageObject<SysUserDeptVo> page =sysUserService.findPageObjects(pageCurrent,username);

		return new JsonResult(page);

	}
	
	/**在页面中 用户通过点击启用-禁用 按钮修改用户状态
	 * 用户id 和状态码 0-1
	 * url: http://localhost:9099/user/doValidById
	 * id: 4
valid: 0

http://localhost:9099/user/doValidById?id=4&valid=2
	 * */
	
	/**
	 * @param id
	 * @param valid
	 * @return
	 */
	@RequestMapping("doValidById")
	public JsonResult doValidById(Integer  id,Integer  valid) {
		//添加用户名字 来修改
		String modifiedUser = ShiroUtil.getUsername();
		
	sysUserService.validById(id,valid,modifiedUser);
	
	

		return new JsonResult("修改成功！");

	}
	
	/**
	 http://localhost:9099/user/doSaveObject
post
--username: kong
password: 123123
email: 123@123
mobile: 123123
deptId: 3
roleIds: 1
--
	 * */
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysUser user,Integer...roleIds) {
		
				
		//添加创建用户名
		String createdUser =ShiroUtil.getUsername();
	sysUserService.saveObject(roleIds,user,createdUser);

		return new JsonResult("用户添加成功！");

	}
	
	/**http://localhost:9099/user/doFindObjectById?id=21
	 * get
	 * 根据id，查询到一个完整页面信息，返回（和添加时的页面一样）
	 * 返回一个user+roleIds
	 * */
	@RequestMapping("doFindObjectById")
	public JsonResult doFindObjectById(Integer  id) {

		SysUserDeptVo sysUserDeptVo =sysUserService.findObjectById(id);

		return new JsonResult(sysUserDeptVo);

	}
	
	
	/*http://localhost:9099/user/doUpdateObject
	 post
	 username: 甜甜
email: 456456
mobile: 1231213
deptId: 3
roleIds: 51,52
id: 21
	 * 根据用户提交数据进行修改
	 * 
	 * */
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysUser user,Integer...roleIds) {
		
		//添加用户名字 来修改
		String modifiedUser = ShiroUtil.getUsername();
		
		sysUserService.updateObject(modifiedUser,user,roleIds);

		return new JsonResult("用户修改成功！！！");

	}
	
	
	 /* 根据用户提交原密码 新密码 新密码确认 数据进行修改
	 http://localhost:9099/user/doUpdatePassword
post
原密码
密码确认
pwd: 123123
newPwd: 123
cfgPwd: 123
	 * */
	@RequestMapping("doUpdatePassword")
	public JsonResult doUpdatePassword(
			@RequestParam("pwd")	String pwd,
			@RequestParam("newPwd") String newPwd,
			@RequestParam("cfgPwd") String cfgPwd) {
		//添加用户名字 来修改
				String modifiedUser = ShiroUtil.getUsername();
				
		sysUserService.updatePassword(pwd,newPwd,cfgPwd,modifiedUser);

		return new JsonResult("用户修改密码成功！！！");

	}
	
	/**根据用户提交原密码 用户名进行登录操作 admin 123456
	 *http://localhost:9099/user/doLogin
	 *POST  302 ？
username: 蜂王浆
password: 7777
isRememberMe: true
{username: "蜂王浆", password: "7777", isRememberMe: true}
	 * */
	@RequestMapping("doLogin")
	public JsonResult doLogin(
			@RequestParam("username")	String username,
			@RequestParam("password") String password,
			@RequestParam("isRememberMe") Boolean isRememberMe) {
		
		
		//1.获取 Subject 对象
		Subject subject = SecurityUtils.getSubject();
		//2. 通过 Subject 提交用户信息，交给shro框架进行操作
		//2.1对用户进行封装
		UsernamePasswordToken token =new UsernamePasswordToken(username,password);
		
		// 2.2记住用户名判断
		if(isRememberMe) {
			token.setRememberMe(isRememberMe);
		}
		
		
		//3.调用 shiro 内的方法  对用户信息 进行身份认证
				subject.login(token);
	/*所以 在shiro 框架中 一旦用户登录 用户信息 就会被 toke(username,password) 上传到  shiro 框架中
	 * 通过  加密匹配  之后 完成  认证 ，在认证过程中 会查询数据库获取用户其他信息。
	 * 封装到 shiro 框架里面， 通过 下面这个方法获取
	SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();  
	
	SysUser(id=26, username=ling, password=af3835c577bd7af6cfaef2e0cee852b7, 
	salt=1467496a-f16d-4a0f-bb6f-0c736a79ed6c, email=ling123, mobile=123, valid=1, deptId=2);
	
	信息包括：相当于 直接查询数据库 SysUser 的信息
	 * */
		
		
		//分析
		//1） token 回传给 shrio 的SecurityManager
		//2）SecurityManager 将token 传递给认证管理器
		//3） 认证管理器 将token 传递给 realm
		//
		
		
		return new JsonResult("登录成功!");

	}
	
	
	
	

}
