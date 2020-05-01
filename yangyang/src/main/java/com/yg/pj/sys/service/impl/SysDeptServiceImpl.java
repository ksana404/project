package com.yg.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yg.pj.sys.common.vo.Node;
import com.yg.pj.sys.dao.SysDeptDao;
import com.yg.pj.sys.service.SysDeptService;


/*定义部门接口的实现类
 * */
@Service
public class SysDeptServiceImpl implements SysDeptService {

	
	@Autowired
	private SysDeptDao sysDeptDao;
	
	
	
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysDeptDao.findZtreeMenuNodes();
		
	}



}
