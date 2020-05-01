package com.yg.pj.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yg.pj.sys.common.vo.JsonResult;
import com.yg.pj.sys.common.vo.PageObject;
import com.yg.pj.sys.common.vo.SysRoleMenuVo;
import com.yg.pj.sys.entity.SysRole;
import com.yg.pj.sys.service.SysRoleService;

/**
 * 本模块通过业务层对象执行业务逻辑， 再通过VO对象封装响应结果(主要对业务层数据添加状态信息)， 最后将响应结果转换为JSON格式的字符串响应到客户端。
 * 
 * @author Administrator 角色模块的控制层
 *
 */
@RequestMapping("/role/")
@RestController
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * http://localhost:9099/role/doFindPageObjects?pageCurrent=1 通过用户名，当前页码数，获取当前页面
	 * 角色列表信息 返回JSON数据格式
	 */
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(String name, Integer pageCurrent) {
		PageObject<SysRole> pageObject = sysRoleService.findPageObjects(name, pageCurrent);

		return new JsonResult(pageObject);

	}

	/**
	 * 根据用户提交的id删除角色信息 http://localhost:9099/role/doDeleteObject post param Integer
	 * id
	 * 
	 */
	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id) {

		sysRoleService.deleteObject(id);

		return new JsonResult("成功删除！");

	}

	/**
	 * 根据用户提交的角色信息 和 菜单授权信息 添加角色 http://localhost:9099/role/doSaveObject post param=
	 * name: 开发主管 note: 开发人员的领导 menuIds: 8,45,119,126,127
	 * 
	 */
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysRole sysRole, Integer... menuIds) {

		sysRoleService.saveObject(sysRole, menuIds);

		return new JsonResult("成功添加！");

	}

	/**
	 * 根据用户提交的角色id 查询角色信息 角色关联 菜单id数组 SysRoleMenuVo返回
	 * http://localhost:9099/role/doFindObjectById?id=51 post param= id: 51
	 * 
	 */
	@RequestMapping("doFindObjectById")
	public JsonResult doFindObjectById(Integer id) {

		SysRoleMenuVo sysRoleMenuVo = sysRoleService.findObjectById(id);

		return new JsonResult(sysRoleMenuVo);

	}

	/**
	 * 根据提交数据修改角色数据 角色-菜单关系数据 
	 * http://localhost:9099/role/doUpdateObject 
	 * post 
	 * name:
	 * 开发队长 note: 开发人员的队长 
	 * menuIds: 8,24,45,119,126,127 
	 * id: 51
	 */
	
	/**根据提交数据SysRoleMenuVo
	 * 修改角色数据SysRole  角色-菜单关系数据 SysRoleMenu
	 * @param id
	 * @return
	 */
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysRoleMenuVo sysRoleMenuVo) {

		sysRoleService.updateObject(sysRoleMenuVo);

		return new JsonResult("角色修改成功!");

	}
	
	/*http://localhost:9099/role/doFindRoles
	 * 就是直接查询所有role 信息
	 * */
	@RequestMapping("doFindRoles")
	public JsonResult doFindRoles() {
	List<SysRole> list = sysRoleService.findRoles();

		return new JsonResult(list);

	}

}
