package com.yg.pj.sys.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;


@Data
public class SysRoleMenuVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7143878668440644327L;
	/**角色id*/
	private Integer id;
	/**角色名称*/
	private String name;
/**角色备注*/
	private String note;
	/**角色对应的菜单id*/
	private List<Integer> menuIds;
	

}
