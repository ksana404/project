package com.sb.sys.dao.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sb.sys.dao.SysMenuDao;

import lombok.extern.slf4j.Slf4j;

/*SysLogDao 测试类
 * 
 * */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysMenuDaoTest {
	
	@Autowired
	private SysMenuDao sysMenuDao;
	
	@Test
	public void getChildCount() {
		
		int childCount = sysMenuDao.getChildCount(8);
		log.info("-------获取子菜单数据  childCount:"+childCount);
	}

}
