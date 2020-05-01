package com.yg.pj.sys.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestThreadPool01 {
	/**
	 * 最大吞吐量 即最多可以接收的任务数量  阻塞队列容量 + 最大线程数
	 * --》平时是在核心线程    核心满了后   --》放入阻塞队列   
	 * 阻塞队列满了后   --》 新建线程（新建线程数量+核心数量 <= 最大线程）
	 * 
	 * 任务 --》 阻塞队列  --》{  核心线程 + 其他线程（核心都在忙 +队列已满 ）         最大线程}
	 * 
	 * 所以最多接受的任务数量为  阻塞队列+ 最大线程
	 * */
	
	static ThreadPoolExecutor pExecutor;  //该类属性 线程池对象
	static  BlockingQueue<Runnable> workQueue =new ArrayBlockingQueue<>(3); 
	// 阻塞队列容量 最多放三个任务
	
	//弄一个线程工厂 给线程起名字
	private static ThreadFactory threadFactory = new ThreadFactory() {
		// CAS算法  自增 不重复  初始值1000
		private AtomicInteger at = new AtomicInteger(500);

		@Override
		public Thread newThread(Runnable r) {
			// 配置文件需要填入
			return new Thread(r, "async-thread-poolp" + at.getAndIncrement());
			//任务执行   线程命名
		}
	};
	
	
	static {
	//ThreadPoolExecutor
		//加上这个是创建一个新的类 进行设置    而不是为 TestThreadPool01.pExecutor进行构建
		 pExecutor =	new ThreadPoolExecutor(
				2, //核心线程池数量 5-2=3
				5, //最大线程池数量
				2, //核心 线程最大空闲时间
				TimeUnit.SECONDS,  //设置时间单位
				workQueue,			// 阻塞队列
				threadFactory);  //线程工厂 源码中有介绍  各种方法的参数
		
		/**
		  public ThreadPoolExecutor(int corePoolSize, 核心线程池数量
	                              int maximumPoolSize, 最大线程池数量
	                              long keepAliveTime,  线程最大空闲时间 -1 没有空闲时间
	                              TimeUnit unit,   //设置时间单位（线程最大空闲时间）
	                              BlockingQueue<Runnable> workQueue) {  // 阻塞队列
	        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
	             Executors.defaultThreadFactory(), defaultHandler);
	    }
		  1.任务来的时候，先看核心线程有没有空闲，
		  2.优先使用空闲核心线程，核心线程满了后任务放入阻塞队列中，
		  3.阻塞队列满了后，创建新的行程。（能够创建新线程的数量= 最大线程-核心线程数）
		  4.阻塞队列 和 最大线程数都满了后，执行拒绝策略  java.util.concurrent.RejectedExecutionException
		
		阻塞案例：
		核心 2  最大5  队列3  最多任务数 8 ，阻塞4s，超过8的调用任务执行拒绝策略！！！
		线程总数 1-5  队列意思是 利用重复线程
		最后出来的 是1-3 因为有3个任务在队列里面  而队列是等线程执行完后 再由核心线程执行
		
		拒绝执行后 后面就被卡住了，如何解决？
		 ***/
	
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		//调用执行方法
		pExecutor.execute(new Runnable() {
			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.println(name+"执行了 task 01");
			}
		});
		
		
		//调用执行方法
				pExecutor.execute(new Runnable() {
					@Override
					public void run() {
						String name = Thread.currentThread().getName();
						System.out.println(name+"执行了 task 02");
					}
				});
				
				
				//调用执行方法
				pExecutor.execute(new Runnable() {
					@Override
					public void run() {
						String name = Thread.currentThread().getName();
						System.out.println(name+"执行了 task 03");
					}
				});
				
				
				//调用执行方法
				pExecutor.execute(new Runnable() {
					@Override
					public void run() {
						String name = Thread.currentThread().getName();
						System.out.println(name+"执行了 task 04");
					}
				});
				
				
				//调用执行方法
				pExecutor.execute(new Runnable() {
					@Override
					public void run() {
						String name = Thread.currentThread().getName();
						System.out.println(name+"执行了 task 05");
					}
				});
		
		
		
		 System.out.println("工作结束！");

	}

}
