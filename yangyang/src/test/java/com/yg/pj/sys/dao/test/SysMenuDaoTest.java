package com.yg.pj.sys.dao.test;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yg.pj.sys.dao.SysMenuDao;

@SpringBootTest
public class SysMenuDaoTest {
	
	@Autowired
	private SysMenuDao sysMenuDao;
	
	
	@Test
	public void findObjectsTest() {
		
		List<Map<String, Object>> findObjects = sysMenuDao.findObjects();
		
		for(Map<String, Object> map:findObjects) {
			
			System.out.println("-------------->>>  map:"+map.toString());
		}
	}

}
