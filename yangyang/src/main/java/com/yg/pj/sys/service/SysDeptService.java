package com.yg.pj.sys.service;

import java.util.List;

import com.yg.pj.sys.common.vo.Node;

public interface SysDeptService {
	
	/*
	 * 定义部门接口
	 * */
	
	//添加查询菜单信息的方法
	/**
	 * @return
	 */
	List<Node> findZtreeMenuNodes();
	
	

}
