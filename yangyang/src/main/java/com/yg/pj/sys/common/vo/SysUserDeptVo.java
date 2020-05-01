package com.yg.pj.sys.common.vo;

import com.yg.pj.sys.entity.SupEntity;
import com.yg.pj.sys.entity.SysDept;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=true)  //extends SupEntity
public class SysUserDeptVo extends SupEntity {

	/**
	 * com.yg.pj.sys.common.vo.SysUserDeptVo
	 */
	private static final long serialVersionUID = 4586780169432665115L;

	private Integer id;
	private String username;
	private String password;//md5
	private String salt;
	private String email;
	private String mobile;
	private Integer valid=1;
	private SysDept sysDept; //private Integer deptId;
	
	
}
