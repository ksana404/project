package com.sb.sys.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
//com.sb.sys.config.PageProperties
/*
lingdu:
  page:
    pageSize: 4
 * */
@ConfigurationProperties(prefix = "lingdu.page")
@Data
public class PageProperties{
		private Integer pageSize = 6;
}
