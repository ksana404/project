package com.sb.sys.entity.vo;

import java.io.Serializable;
import java.util.List;

import com.sb.sys.entity.SysLog;

import lombok.Data;
@Data
public class PageObject<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7961388491187551739L;

	 /**当前页的页码值*/
		private Integer pageCurrent=1;
	    /**页面大小*/
	    private Integer pageSize=3;
	    /**总行数(通过查询获得)*/
	    private Integer rowCount=0;
	    /**总页数(通过计算获得)*/
	    private Integer pageCount=0;
	    /**当前页记录*/
	    private List<SysLog> records;
}
