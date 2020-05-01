package com.yg.pj.sys.dao.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yg.pj.sys.dao.SysDeptDao;
import com.yg.pj.sys.dao.SysMenuDao;
import com.yg.pj.sys.dao.SysRoleMenuDao;
import com.yg.pj.sys.dao.SysRoleUserDao;
import com.yg.pj.sys.entity.SysDept;

/*SysLogDao接口测试类*/
@SpringBootTest
public class SysDaoSSS {
	

	@Autowired
	private SysRoleUserDao sysRoleUserDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	//SysRoleUserDao
	@Autowired
	private SysMenuDao sysMenuDao;
	
	@Autowired
	private SysDeptDao sysDeptDao;
	
	//SysDeptDao
	
	
	/***/
	@Test
	public void findRoleIdsByUserIdTest() {
		//2.基于用户id获取用户拥有的角色(sys_user_roles)
		List<Integer>  rouleIds =sysRoleUserDao.findRoleIdsByUserId(26);
		System.out.println(" rouleIds:"+rouleIds.toString());
	}
	
	
	/***/
	@Test
	public void findMenuIdsByRoleIdTest() {
		List<Integer> roleIds =new ArrayList<>();
		roleIds.add(54);
		
		 List<Integer> menusIds= sysRoleMenuDao.findMenuIdsByRoleIds(roleIds);
	
		System.out.println("menusIds:"+menusIds.toString());
	}
	
	
	/***/
	@Test
	public void findPermissionTest() {
		List<Integer> menusIds =new ArrayList<>();
		menusIds.add(8);
		menusIds.add(45);
		menusIds.add(119);
		menusIds.add(126);
		menusIds.add(127);
		menusIds.add(131);
		
		//4.基于菜单id获取权限标识(sys_menus)
		List<String> permission =sysMenuDao.findPermission(menusIds);
		
		System.out.println("permission:"+permission.toString());
	}
	
	//findById
	
	@Test
	public void findByIdTest() {
		
		SysDept dept = sysDeptDao.findById(7);
		System.out.println("---------dept:"+dept.toString());
	}
	
}
