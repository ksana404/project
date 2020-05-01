package com.yg.pj.sys.thread.pool;

import java.util.concurrent.ThreadPoolExecutor;

public class TestThreadPool03 {
	//第一步  构建一件线程池
	// 1.0构建池之前  可以把池声明为这个类的static 属性，为了好调用
	private static ThreadPoolExecutor pool;
	
	//2.0具体构建包括  线程池的 构造方法中的参数：   核心线程  最大线程   最大闲置时间  时间单位   阻塞队列   线程工厂   拒绝策略
	// 3.0 构建 之前需要把参数进行  构建
	//3.1 阻塞队列的构建
	//3.2 线程工厂构建
	//3.3 拒绝策略 构建 在线程池里面 
	// 4.构建线程池 static{} 块中   需要程序一九需要使用
	
	static{
		
		
	} 
	
	//构造方法的参数可以通过源码  查看
	//第二步 调用 线程池 调用方法

}
