package com.yg.pj.sys.common.config;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
/******/
@Slf4j
@Setter
@Configuration
@ConfigurationProperties("async-thread-poolp")
//配置文件暂时不写  /**** 实现AsyncConfigurer *******/
public class SpringAsyncConfigP implements AsyncConfigurer {
	/** 核心线程数 */
	private int corePoolSize = 20;
	/** 最大线程数 */
	private int maxPoolSize = 1000;
	/** 线程空闲时间 */
	private int keepAliveSeconds = 50;
	/** 阻塞队列容量 */
	private int queueCapacity = 200;
	
	/*
	  计算密集型的任务比较占cpu，所以一般线程数设置的大小 等于或者略微大于 cpu的核数；
	  但IO型任务主要时间消耗在 IO等待上，cpu压力并不大，所以线程数一般设置较大，
	  例如 多线程访问数据库，数据库有128个表，可能就直接考虑使用128个线程。
	 https://blog.csdn.net/zhanht/article/details/79513134
	 
application.yml 配置文件

spring:
 task:
    execution:
      pool:
        core-size: 8
        max-size: 128
        allow-core-thread-timeout: true
        keep-alive: 3
        queue-capacity: 200
    scheduling:
      thread-name-prefix: YANG-Thread
    
    
	 * */

	/** 构建线程工厂 */
	/** 导入JUC包 */
	private ThreadFactory threadFactory = new ThreadFactory() {
		// CAS算法  自增 不重复  初始值1000
		private AtomicInteger at = new AtomicInteger(1000);

		@Override
		public Thread newThread(Runnable r) {
			// 配置文件需要填入
			return new Thread(r, "async-thread-poolp" + at.getAndIncrement());
			//任务执行   线程命名
		}
	};

	// 构建一个线程池  初始化各种属性 包括拒绝执行策略
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setKeepAliveSeconds(keepAliveSeconds);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadFactory(threadFactory);
		// Rejected 拒绝执行策略

		executor.setRejectedExecutionHandler((Runnable r, ThreadPoolExecutor exe) -> {
			log.warn("当前任务线程池队列已满.");
			System.out.println("啊啊啊啊啊啊啊啊啊啊啊啊啊");
			//
		});
		executor.initialize();  //初始化
		return executor;
	}

	// 处理异常
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {

		return new AsyncUncaughtExceptionHandler() {
			@Override
			public void handleUncaughtException(Throwable ex, Method method, Object... params) {
				log.error("线程池执行任务发生未知异常", ex);
			}
		};
	}

}
