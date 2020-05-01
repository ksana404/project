package com.yg.pj.sys.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestThreadPool02 {
	//第一步  构建一件线程池
	// 1.0构建池之前  可以把池声明为这个类的static 属性，为了好调用
	
	private static ThreadPoolExecutor pool;
	
	static int corePoolSize =2;
	static int maximumPoolSize = 5;
	static long keepAliveTime =2;
	static TimeUnit unit =TimeUnit.SECONDS;
	static BlockingQueue<Runnable> workQueue =new ArrayBlockingQueue<>(2);
	// 接口 搞一个？？？？
	private static ThreadFactory threadFactory =new ThreadFactory() {

		AtomicInteger atomic =new AtomicInteger(2000);
		
		@Override
		public Thread newThread(Runnable r) {
			
			return new Thread(r,"pool 02号"+atomic.getAndIncrement());
		}
		
	};
	
	//2.0具体构建包括  线程池的 构造方法中的参数：   核心线程  最大线程   最大闲置时间  时间单位   阻塞队列   线程工厂   拒绝策略
	// 3.0 构建 之前需要把参数进行  构建准备 
	//3.1 阻塞队列的构建
	//3.2 线程工厂构建
	//3.3 拒绝策略 构建 在线程池里面 
	// 4.构建线程池 static{} 块中   需要程序一九需要使用
	
	static{
		
		pool = new ThreadPoolExecutor(corePoolSize,
				maximumPoolSize, 
				keepAliveTime, 
				unit,
				workQueue, 
				threadFactory, 
				(Runnable r, ThreadPoolExecutor exe) -> {
					log.warn("当前任务线程池队列已满.");
				}); //拒绝策略
	} 
	
	/*
	ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)
                              
        拒绝执行策略：
1. AbortPolicy 
默认。直接抛异常。
2. CallerRunsPolicy 
只用调用者所在的线程执行任务,重试添加当前的任务，它会自动重复调用execute()方法
3. DiscardOldestPolicy 
丢弃任务队列中最久的任务。
4. DiscardPolicy 
   丢弃当前任务。
————————————————
版权声明：本文为CSDN博主「Zach_ZSZ」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/z_s_z2016/article/details/81674893
	
	 * */
	//构造方法的参数可以通过源码  查看
	//第二步 调用 线程池 调用方法
	
	public static void main(String[] args) {
		
		//调用方法执行 
		pool.execute(new Runnable() {

			@SuppressWarnings("static-access")
			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.println(name+"执行 task 01");
				
				try {
					Thread.currentThread().sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			
		});
		
		
		//调用方法执行 
				pool.execute(new Runnable() {

					@Override
					public void run() {
						String name = Thread.currentThread().getName();
						System.out.println(name+"执行 task 02");
						
						try {
							Thread.currentThread().sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				
				
				//调用方法执行 
				pool.execute(new Runnable() {

					@Override
					public void run() {
						String name = Thread.currentThread().getName();
						System.out.println(name+"执行 task 03");
						
						try {
							Thread.currentThread().sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});
		
				
				//调用方法执行 
				pool.execute(new Runnable() {

					@Override
					public void run() {
						String name = Thread.currentThread().getName();
						System.out.println(name+"执行 task 04");
						
						try {
							Thread.currentThread().sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
				});
				
				//调用方法执行 
				pool.execute(new Runnable() {

					@Override
					public void run() {
						String name = Thread.currentThread().getName();
						System.out.println(name+"执行 task 05");
						
						try {
							Thread.currentThread().sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});
				
				
				//调用方法执行 
				pool.execute(new Runnable() {

					@Override
					public void run() {
						String name = Thread.currentThread().getName();
						System.out.println(name+"执行 task 06");
						
						try {
							Thread.currentThread().sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
				});
				
				
				//调用方法执行 
				pool.execute(new Runnable() {

					@Override
					public void run() {
						String name = Thread.currentThread().getName();
						System.out.println(name+"执行 task 07");
						
						try {
							Thread.currentThread().sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});
				
				
				//调用方法执行 
				pool.execute(new Runnable() {

					@Override
					public void run() {
						String name = Thread.currentThread().getName();
						System.out.println(name+"执行 task 08");
						
						try {
							Thread.currentThread().sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});
				
				//调用方法执行 
				pool.execute(new Runnable() {

					@Override
					public void run() {
						String name = Thread.currentThread().getName();
						System.out.println(name+"执行 task 09");
						
						try {
							Thread.currentThread().sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
					}
				});
		
		
		
	}

}
