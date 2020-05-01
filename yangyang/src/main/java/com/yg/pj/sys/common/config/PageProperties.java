package com.yg.pj.sys.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
/**基于此类封装page参数信息，此类设计和实现参考springboot中的
 * ThymeleafProperties 的设计
 * 把配置信息提取出来，读取application.yml文件，
 * 等效于application.properties
 * 
 * */
@ConfigurationProperties(prefix = "yang.page")
@Data
public class PageProperties {
	
	private Integer pageSize =5;
	
	
}
