package com.yg.pj.sys.service;

import java.util.List;

import com.yg.pj.sys.common.vo.PageObject;
import com.yg.pj.sys.common.vo.SysRoleMenuVo;
import com.yg.pj.sys.entity.SysRole;

public interface SysRoleService {

	
	/**获取角色列表信息
	 * @return
	 */
	PageObject<SysRole> findPageObjects(String name,Integer pageCurrent);

	/**根据用户提交的id删除角色信息
	 * @param id
	 */
	int deleteObject(Integer id);

	/**根据用户提交的角色信息 和 菜单授权信息  添加角色
	 * @param sysRole
	 * @param menuIds
	 */
	int  saveObject(SysRole sysRole, Integer[] menuIds);

	/**根据用户提交的角色id 查询角色信息  角色关联 菜单id数组  SysRoleMenuVo返回
	 * @param id
	 * @return
	 */
	SysRoleMenuVo findObjectById(Integer id);

	/**根据提交数据SysRoleMenuVo
	 * 修改角色数据SysRole  角色-菜单关系数据 SysRoleMenu
	 * @param sysRoleMenuVo
	 */
	int updateObject(SysRoleMenuVo sysRoleMenuVo);
	
	
	/**就是直接查询所有role 信息
	 * @return
	 */
	List<SysRole> findRoles();

}
