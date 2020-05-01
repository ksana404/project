package com.yg.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yg.pj.common.exception.ServiceException;
import com.yg.pj.sys.common.config.PageProperties;
import com.yg.pj.sys.common.vo.PageObject;
import com.yg.pj.sys.common.vo.SysRoleMenuVo;
import com.yg.pj.sys.dao.SysRoleDao;
import com.yg.pj.sys.dao.SysRoleMenuDao;
import com.yg.pj.sys.dao.SysRoleUserDao;
import com.yg.pj.sys.entity.SysRole;
import com.yg.pj.sys.service.SysRoleService;
@Service

public class SysRoleServiceImpl implements SysRoleService {

	//角色dao 注入
	@Autowired
	private SysRoleDao sysRoleDao;
	//角色菜单dao 注入
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	//角色用户dao 注入
	@Autowired
	private SysRoleUserDao sysRoleUserDao;
	
	@Autowired
	private PageProperties pageProperties;
	
	
	
	
	


	@Override
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
		//1,对参数进行校验   不对用户进行校验 是因为在登录情况下  不需要进行用户名校验
				if(pageCurrent==null || pageCurrent<1)
					throw new IllegalArgumentException("当前页码值不正确！");
				//2，查询总记录数并进行校验  通过用户名字查询角色记录
				//角色是是属于用户的一个属性  一个用户可能有多个角色
				//角色查询出来后 需要进行分页展示 页面大小6  当前页3  起始13  结束19
				int rowCount =sysRoleDao.getRowCount(name);
				if(rowCount==0)
					throw new IllegalArgumentException("该用户没有角色信息记录");
				//3，查询当前页记录
				int pageSize =pageProperties.getPageSize();
				int startIndex =(pageCurrent-1)*pageSize;
				
				List<SysRole> records= sysRoleDao.findPageObjects(name,startIndex,pageSize);
				//4，对结果进行封装并返回
				PageObject<SysRole> pageObject =new PageObject<>();
				pageObject.setRecords(records);
				pageObject.setPageSize(pageSize);
				
				int pageCount =((rowCount-1)/pageSize)+1;
				pageObject.setPageCount(pageCount);
				pageObject.setPageCurrent(pageCurrent);
				pageObject.setRowCount(rowCount);
				
				return pageObject;
	}



	@Override
	public int deleteObject(Integer id) {
		/**用户基于角色id,删除角色信息，
		 * 删除之前，需要删除角色-用户关系   角色-菜单关系。
		 * 再进行删除。
		 * */
		
		
		//1.校验参数
		if(id==null || id<0)
			throw new IllegalArgumentException("请先选择需要删除项");
		//1.5删除角色之前需要删除  角色-用户关系   角色-菜单关系
		
		//1.5.2 删除角色-菜单关系   直接根据角色ID删除
	sysRoleMenuDao.deleteObjectsByRoleId(id);
		//1.5.1 删除角色-用户关系  直接根据角色ID删除
	sysRoleUserDao.deleteObjectsByRoleId(id);
		
		//2.调用方法删除
	int rows =sysRoleDao.deleteObjects(id);
		//3.结果处理
	if(rows==0)
		throw new IllegalArgumentException("该角色信息可能已经不存在！");
	
	return rows;
		
		
	}



	@Override
	public int saveObject(SysRole sysRole, Integer[] menuIds) {
		//1.校验参数
		//1.1 角色信息 非null 
		if(sysRole==null)
			throw new IllegalArgumentException("添加角色信息不能为空");
		//1.2 角色信息 name 非空 
		if(StringUtils.isEmpty(sysRole.getName()))
			throw new IllegalArgumentException("添加角色名字不能为空");
		//1.3 角色信息 note 非空 
		if(StringUtils.isEmpty(sysRole.getNote()))
			throw new IllegalArgumentException("添加角色备注不能为空");
		
		//1.4 授权信息  非空 校验
		if(menuIds==null || menuIds.length <1)
			throw new ServiceException("请添加授权信息");
		
		//1.5 授权信息  合法 校验
		//如果menuIds 中存在不合法id,那么会插入失败，所以不需要自己校验合法性
		
		
		
		//2.调用dao层方法  
		//2.1 保存 角色信息sysRole   
		//为何使用insert方法  因为和dao层SQL语法相似
		int rows =sysRoleDao.insert(sysRole);
		System.out.println("=============  =============  sysRole ID:"+sysRole.getId());
		
		//2.2 保存角色-菜单 关系数据 roleId - menuId
		sysRoleMenuDao.insert(sysRole.getId(),menuIds);
		//3.结果处理
		//4.返回结果
		if(rows ==0)
			throw new ServiceException("角色信息 保存失败！");
		
		
		return rows;
	}



	@Override
	public SysRoleMenuVo findObjectById(Integer id) {

		//1.校验参数
		if(id==null || id <=0)
			throw new IllegalArgumentException("角色id不正确!");
		//2.调用dao层方法
		//2.1 通过角色id 查询角色信息
		SysRoleMenuVo  sysRoleMenuVo =sysRoleDao.findObjectById(id);
		//2.1.1结果校验
		if(sysRoleMenuVo==null )
			throw new ServiceException("该角色可能已经不存在");
		
		return sysRoleMenuVo;
	}



	@Override
	public int updateObject(SysRoleMenuVo sysRoleMenuVo) {
		//1.校验参数
		if(sysRoleMenuVo==null)
			throw new IllegalArgumentException("提交的数据不能为空！");
		
		//1.2 角色信息 name 非空 
		if(StringUtils.isEmpty(sysRoleMenuVo.getName()))
		throw new IllegalArgumentException("添加角色名字不能为空");
		
				//1.3 角色信息 note 非空 
				if(StringUtils.isEmpty(sysRoleMenuVo.getNote()))
					throw new IllegalArgumentException("添加角色备注不能为空");
				
				//1.4 授权信息  非空 校验
				List<Integer> menuIds = sysRoleMenuVo.getMenuIds();
				if(menuIds ==null || menuIds.size() <1)
					throw new ServiceException("请添加授权信息");
				
		//2.调用dao层方法
				
				
				
				//2.1 根据 角色id 修改角色信息
				SysRole sysRole =new SysRole();
				sysRole.setName(sysRoleMenuVo.getName());
				sysRole.setNote(sysRoleMenuVo.getNote());
				sysRole.setId(sysRoleMenuVo.getId());
				
				//2.1.1 修改角色信息
			int rows =sysRoleDao.updateObject(sysRole);
			
				//2.1
				//2.1 根据角色id 修改角色-菜单 信息
			//因为可以不固定关系的id 先删除原来的 再添加新的
			Integer roleId=sysRoleMenuVo.getId();
			
			sysRoleMenuDao.deleteObjectsByRoleId(roleId);
			
			//List转换成数组
			Integer[] array =new Integer[menuIds.size()];
			 array = menuIds.toArray(array);
			
			sysRoleMenuDao.insert(roleId, array);
				//2.1
		//3.结果处理
		//4.返回结果 /null
		return rows;
	}
	
	
	@Override
	public List<SysRole> findRoles() {
		//1.
	List<SysRole> list =sysRoleDao.finds();
		//2.
		//3.
		//4.
		//5.
		
		return list;
	}

}
