package com.yg.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yg.pj.sys.common.vo.SysUserDeptVo;
import com.yg.pj.sys.entity.SysUser;

@Mapper
public interface SysUserDao {

	/**通过用户名查询当前记录数
	 * 注意名字 参数
	 * @param name
	 * @return
	 */
	int getRowCount(String name);

	/**通过用户名  起始记录数 页面大小 查询当前页面 记录信息
	 * 
	 * @param name
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<SysUserDeptVo> findPageObjects(String name, int startIndex, int pageSize);

	/**在页面中 用户通过用户id 状态码 修改用户状态
	 * @param id
	 * @param valid
	 * @return
	 */
	int validById(Integer id, Integer valid,String modifiedUser);

	/**根据用户提交信息 添加用户
	 * @param user
	 * @return
	 */
	int insert(SysUser user);

	/**根据id，查询到一个完整页面信息，返回（和添加时的页面一样）
	 * @param id
	 * @return
	 */
	SysUserDeptVo findObjectById(Integer id);
	
	

	/**根据用户 提交的页面信息 修改用户信息
	 * @param user
	 * @return
	 */
	int updateObject(SysUser user);

	
	/**根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	SysUser findUserByUserName(String username);

	/**调用方法 根据用户id 使用新盐值 新密码 修改人 更新密码
	 * @param id
	 * @param mD5Newpwd
	 * @param newSalt
	 * @param modifiedUser
	 * @return
	 */
	int updateNewMd5Psd(
			@Param("id") Integer id, 
			@Param("password")	String mD5Newpwd, 
			@Param("salt")	String newSalt, 
			@Param("modifiedUser")   String modifiedUser);

}
