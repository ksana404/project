package com.yg.pj.sys.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration 注解描述的类为一个配置对象,
 * 此对象也会交给spring管理
 * 
 * 记得引入包的时候要引入-shro 包
 * org.apache.shiro.mgt.SecurityManager;
 * SecurityManager 是shrio 框架的核心
 */

/**
 * @author Administrator
 *
 */
@Configuration
public class SpringShiroConfig {
	
	/*Shiro中内置缓存应用实现
	 * 
	 * */
	@Bean
	public CacheManager shiroCacheManager() {
		
		return new MemoryConstrainedCacheManager();
		
	}
	
	/*Shiro中内置cookie应用实现
	 * 方法返回值，参数   能用抽象 尽量使用抽象
	 * */
	@Bean
	public RememberMeManager rememberMeManager() {
		
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		SimpleCookie simpleCookie = new SimpleCookie("rememberme");
		simpleCookie.setMaxAge(60*60); //单位秒
		cookieRememberMeManager.setCookie(simpleCookie);
		
		return cookieRememberMeManager;
		
	}
	
	
	/*Shiro中内置 Session应用实现
	 * 
	 * */
	@Bean
	public SessionManager sessionManager() {
		
		//获取shiro提供的 Session 实例
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		//设置失效时间
		sessionManager.setGlobalSessionTimeout(60*60*1000);

		return sessionManager;
		
	}
	
	
/**添加SecurityManager配置   为了获取SecurityManager 实现类实例
 * 把他放到spring容器  
 * @Bean 表示给spring容器管理    默认key 是方法名
 * 
 * 
 * @return
 */
@Bean   //参数对象来自容器  上一级操作在 ShrioUserRealm   service  
		//获得Realm AuthorizingRealm  说携带凭证
public SecurityManager securityManager(Realm realm,CacheManager cacheManager,
		RememberMeManager rememberManager,SessionManager sessionManager) {
	/**这是一个  默认web项目   SecurityManager接口实现类  
	 * */
	DefaultWebSecurityManager sManager =new DefaultWebSecurityManager();
	//realm set 注入
	sManager.setRealm(realm);
	
	// 业务层缓存操作
	sManager.setCacheManager(cacheManager);
	
	//cookie 浏览器缓存
	sManager.setRememberMeManager(rememberManager);
	
	//设置会话
	sManager.setSessionManager(sessionManager);
	
	return sManager;
}
//浏览器查看cookie输入         chrome://settings/content/cookies


@Bean  //参数对象从上面 获取   获得Realm AuthorizingRealm  说携带凭证
public ShiroFilterFactoryBean shiroFilterFactory (
			 org.apache.shiro.mgt.SecurityManager securityManager) {
		 ShiroFilterFactoryBean sfBean= new ShiroFilterFactoryBean();
		 
		 System.out.println(" 2.2----    shiroFilterFactory");
		 
		 //注入
		 sfBean.setSecurityManager(securityManager);
		 sfBean.setLoginUrl("/doLoginUI");
		 
		 //定义map指定请求过滤规则(哪些资源允许匿名访问,哪些必须认证访问)
		 //之所以使用 LinkedHashMap 是因为有顺序，需要认证的要放在后面
		 LinkedHashMap<String,String> map= new LinkedHashMap<>();
		 
		 //静态资源允许匿名访问:"anon"
		 //"anon" 指的是对应 相应过滤规则 的过滤器
		 
		 map.put("/bower_components/**","anon");
		 map.put("/build/**","anon");
		 map.put("/dist/**","anon");
		 map.put("/plugins/**","anon");
		// map.put("/doIndexUI","anon");
		 map.put("/user/doLogin","anon");
		 map.put("/doLogout","logout");
		 
		 
		 //除了匿名访问的资源,其它都要认证("authc")后访问
		 //"user" 会访问cookie   authc 不会
		 map.put("/**","user"); //authc
		 //完成 shrio过滤器工厂   的拦截配置 等待用户调用
		 sfBean.setFilterChainDefinitionMap(map);
		 
		 System.out.println(" 2.3----    shiroFilterFactory-map");
		 
		 return sfBean;
	 }

	/*
	 * 配置advisor对象,shiro框架底层会通过此对象的matchs方法返回值(切入点)
	 * 决定是否创建代理对象,进行权限控制。
	 * */
@Bean
@Autowired
  public AuthorizationAttributeSourceAdvisor  newAuthorizationAttributeSourceAdvisor(
		 SecurityManager  securityManager) {
	  
	  AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
			
	  advisor.setSecurityManager(securityManager);
	  return advisor;
	  /*
	   * 说明:使用框架最重要的尊重规则,框架规则指定了什么方式就使用什么方式。
	   * */
	  
  }
}
