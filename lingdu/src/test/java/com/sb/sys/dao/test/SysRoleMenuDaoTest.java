package com.sb.sys.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sb.sys.dao.SysRoleMenuDao;

import lombok.extern.slf4j.Slf4j;

/*SysRoleMenuDao 测试类
 * 
 * */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest

public class SysRoleMenuDaoTest {
	
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	@Test
	public void getChildCount() {
		
		int row = sysRoleMenuDao.deleteObjectBymenuId(129);
		log.info("-------删除菜单-角色关系数据行数为  row:"+row);
	}

}
