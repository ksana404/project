package com.sb.sys.entity.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Node implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4743055792313379837L;
	
	private Integer id;
	private String name;
	private Integer parentId;

}
