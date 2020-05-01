package com.yg.pj.sys.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=true)  //extends SupEntity
public class SysRole extends SupEntity{

	/**com.yg.pj.sys.entity.SysRole
	 * 
	 */
	private static final long serialVersionUID = -5660255935915201530L;

	private Integer id;
	private String name;
	private String note;
	
}
