package com.yg.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class SupEntity implements Serializable {
	/**1. @Data 注解 是给对象 的属性默认生成 很多方法  
	 * 包括 set get 全参构造  无参构造 toString() hashCode() 等等
	 * @Data 注解 就是Lombok 全功能注解  全功能注解 全功能注解 
	 * 
	 * import lombok.Data
	 * 2. Lombok 还有其他注解：
	 * 
	 * 3.问题：
	 * 子类继承之后，@Data注解只是作用于 这个父类，不作用子类
	 * 子类需要 添加@Data注解，同时由于子类    需要自己属性 和 父类属性 生成   hashCode
	 *子类使用  所以需要加上 
	 *
	 * 需要调用 超类属性，很明显继承超类的pojo 都需要超类 的 日志属性！！！！！
	 * 
	 * @EqualsAndHashCode(callSuper=true)  //extends SupEntity 
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 2239255867722474087L;
	private Date createdTime; //创建时间
	private Date modifiedTime; //修改时间
	private String createdUser; //创建用户
	private String modifiedUser; //修改用户

}
