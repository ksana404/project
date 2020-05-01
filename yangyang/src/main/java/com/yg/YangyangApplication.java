package com.yg;

import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync //spring容器启动时会创建线程池
//@EnableTransactionManagement  //目的是声明事务 早期需要这样加 多早,哪个版本之前? 
@SpringBootApplication
@EnableCaching //开启缓存配置
public class YangyangApplication  implements CommandLineRunner {
/*为了获取spring 容器加载后 获取 目标 对象
 * AnnotationAwareAspectJAutoProxyCreator 此类 实例  如何获取
 * */
	
	@Autowired  //先初始化 再实例化  如果没有这个值  那么程序启动会报错   因为注入失败
	private AnnotationAwareAspectJAutoProxyCreator  proxy;
	
	public static void main(String[] args) {
		SpringApplication.run(YangyangApplication.class, args);
		
		
	}

	
	
	//程序启动后 会初始化  一旦初始化 就会 加载 构造方法
	public YangyangApplication() {
		//System.out.println("main proxy:"+proxy);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println("run proxy:"+proxy);
		//System.out.println("run proxyClass:"+proxy.getClass());
		//class org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator
	}
	

}
