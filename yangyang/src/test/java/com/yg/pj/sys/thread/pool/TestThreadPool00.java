package com.yg.pj.sys.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPool00 {
	
	static ThreadPoolExecutor pExecutor;  //该类属性 线程池对象   空的线程池
	// workQueue 阻塞队列
	static BlockingQueue<Runnable> workQueue =new ArrayBlockingQueue<Runnable>(2);
	
	
	
	static {
		//ThreadPoolExecutor
			//加上这个是创建一个新的类 进行设置    而不是为 TestThreadPool01.pExecutor进行构建
			 pExecutor =	new ThreadPoolExecutor(
					2, //核心线程池数量 5-2=3
					5, //最大线程池数量
					10, //核心 线程最大空闲时间
					TimeUnit.SECONDS,  //设置时间单位
					workQueue	// 阻塞队列
					); 
			 
			/**
			 public ThreadPoolExecutor(int corePoolSize,
                     int maximumPoolSize,
                     long keepAliveTime,
                     TimeUnit unit,
                     BlockingQueue<Runnable> workQueue) {
this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
    Executors.defaultThreadFactory(), defaultHandler);
}
			 ***/
		
		}
	
	public static void main(String[] args) {
		
		//调用执行方法
		pExecutor.execute(new Runnable() {

			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.println(name+" 执行了 Task 01");
			}
		});
		
		//调用执行方法
				pExecutor.execute(new Runnable() {

					@Override
					public void run() {
						String name = Thread.currentThread().getName();
						System.out.println(name+" 执行了 Task 02");
					}
				});
				
				
				//调用执行方法
				pExecutor.execute(new Runnable() {

					@Override
					public void run() {
						String name = Thread.currentThread().getName();
						System.out.println(name+" 执行了 Task 03");
					}
				});
				
				//调用执行方法
				pExecutor.execute(new Runnable() {

					@Override
					public void run() {
						String name = Thread.currentThread().getName();
						System.out.println(name+" 执行了 Task 04");
					}
				});
		
	}

}
