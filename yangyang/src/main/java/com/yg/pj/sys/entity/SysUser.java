package com.yg.pj.sys.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=true)  //extends SupEntity
public class SysUser extends SupEntity{
/**
 * 
 * Generating equals/hashCode implementation but without a call to superclass, 
 * even though this class does not extend java.lang.Object.
 *  If this is intentional, add '@EqualsAndHashCode(callSuper=false)' to your type.
 *  
 生成equals/hashCode实现，但不调用超类，
 即使该类不扩展java.lang.Object。如果这是有意的，
 请在类型中添加“@EqualsAndHashCode（callSuper=false）”。
 
 需要调用 超类属性，很明显继承超类的pojo 都需要超类 的 日志属性！！！！！

 * */
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2463057681284793466L;
	/**com.yg.pj.sys.entity.SysUser
	 * 
	 */
	private Integer id;
	private String username; //用户名
	private String password; //密码
	private String salt; //'盐  密码加密时前缀，使加密后的值不同'
	private String email; //邮箱
	private String mobile; //手机号
	private Integer  valid;//'状态  0：禁用   1：正常  默认值 ：1'
	private Integer deptId; // 部门id
	
	

}
