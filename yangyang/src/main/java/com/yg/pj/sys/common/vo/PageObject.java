package com.yg.pj.sys.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
@Data
public class PageObject<T> implements Serializable {

	/**
	 * 业务值对象定义，基于此对象封装数据层返回的数据以及计算的分页信息
	 */
	private static final long serialVersionUID = -1114512017327734661L;

	 	/**当前页的页码值*/
		private Integer pageCurrent=1;
	    /**页面大小 一页多少条信息？ */
	    private Integer pageSize=3;
	    /**总行数(通过查询获得)*/
	    private Integer rowCount=0;
	    /*总页数页(通过计算获得)*/
	    private Integer pageCount=0;
	    /**当前页记录*/
	    private List<T> records;
	    
		public PageObject(Integer pageCurrent, Integer pageSize, Integer rowCount, Integer pageCount, List<T> records) {
			super();
			this.pageCurrent = pageCurrent;
			this.pageSize = pageSize;
			this.rowCount = rowCount;
			this.pageCount = pageCount;
			this.records = records;
		}
		
		public PageObject() {
		}
		
		public Integer getPageCurrent() {
			return pageCurrent;
		}
		public void setPageCurrent(Integer pageCurrent) {
			this.pageCurrent = pageCurrent;
		}
		public Integer getPageSize() {
			return pageSize;
		}
		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}
		public Integer getRowCount() {
			return rowCount;
		}
		public void setRowCount(Integer rowCount) {
			this.rowCount = rowCount;
		}
		public Integer getPageCount() {
			return pageCount;
		}
		public void setPageCount(Integer pageCount) {
			this.pageCount = pageCount;
		}
		public List<T> getRecords() {
			return records;
		}
		public void setRecords(List<T> records) {
			this.records = records;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		
		
	
	
	
	
}
