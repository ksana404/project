package com.yg.pj.sys.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=true)  //extends SupEntity
public class SysDept extends SupEntity{
	//com.yg.pj.sys.entity.SysDept
	/**
	 * 
	 */
	private static final long serialVersionUID = 472464330816694983L;
	private Integer id;
	private String name;
	private Integer parentId;//parentId
	private Integer sort;//
	private String note;
	
	
}
