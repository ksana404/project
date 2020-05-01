package com.yg.pj.sys.service;

import com.yg.pj.sys.common.vo.PageObject;
import com.yg.pj.sys.common.vo.SysUserDeptVo;
import com.yg.pj.sys.entity.SysUser;

public interface SysUserService {

	/**页面展现  查询数据 根据用户名和当前页数查询数据
	 * @param pageCurrent 当前页数
	 * @param username  用户名
	 * @return 返回页面
	 */
	PageObject<SysUserDeptVo> findPageObjects(Integer pageCurrent, String username);

	/**在页面中 用户通过用户id 状态码 修改用户状态
	 * @param id
	 * @param valid
	 */
	int  validById(Integer id, Integer  valid,String modifiedUser);

	/**根据用户信息 user 角色id roleIds 创建人 createdUser
	 * 来新建用户
	 * @param roleIds
	 * @param user
	 * @param createdUser
	 */
	void saveObject(Integer[] roleIds, SysUser user, String createdUser);

	/**根据id，查询到一个完整页面信息，返回（和添加时的页面一样）
	 * @param id
	 * @return
	 */
	SysUserDeptVo findObjectById(Integer id);

	/**根据用户提交页面数据进行修改
	 * @param modifiedUser
	 * @param user
	 * @param roleIds
	 */
	void updateObject(String modifiedUser, SysUser user, Integer[] roleIds);

	/**根据用户提交原密码 新密码 新密码确认  修改人 数据进行修改
	 * @param pwd
	 * @param newPwd
	 * @param cfgPwd
	 */
	void updatePassword(String pwd, String newPwd, String cfgPwd,String modifiedUser);

	

	

}
