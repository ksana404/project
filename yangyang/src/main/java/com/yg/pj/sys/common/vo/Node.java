package com.yg.pj.sys.common.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Node implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 409634809368135190L;
	
	
	private Integer id;
	private String name;
	private Integer parentId;
	

}
