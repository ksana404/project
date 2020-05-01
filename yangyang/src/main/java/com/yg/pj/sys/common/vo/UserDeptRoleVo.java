package com.yg.pj.sys.common.vo;

import com.yg.pj.sys.entity.SupEntity;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=true)  //extends SupEntity
public class UserDeptRoleVo extends SupEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7853579748104778747L;
	
	private Integer id;
	private String username;
	private String password;//md5
	private String email;
	private String mobile;
	
	private Integer deptId; //private Integer deptId;
	private Integer[] roleIds;

}
