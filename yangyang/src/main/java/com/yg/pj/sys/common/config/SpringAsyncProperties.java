package com.yg.pj.sys.common.config;

import lombok.Data;

/**基于此类封装page参数信息，此类设计和实现参考springboot中的
 * ThymeleafProperties 的设计
 * 把配置信息提取出来，读取application.yml文件，
 * 等效于application.properties
 * 
 * */
 //这个文件读取类没必要写  配置类里面有 
//@ConfigurationProperties(prefix = "async-thread-poolp")
@Data
public class SpringAsyncProperties {
	private Integer corePoolSize;
	private Integer maxPoolSize;
	private Integer keepAliveSeconds;
	private Integer queueCapacity;

}
