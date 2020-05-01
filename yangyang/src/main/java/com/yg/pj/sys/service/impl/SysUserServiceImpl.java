package com.yg.pj.sys.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import com.yg.pj.common.exception.ServiceException;
import com.yg.pj.sys.common.anno.RequestLogP;
import com.yg.pj.sys.common.anno.RequestTime;
import com.yg.pj.sys.common.config.PageProperties;
import com.yg.pj.sys.common.util.ShiroUtil;
import com.yg.pj.sys.common.vo.PageObject;
import com.yg.pj.sys.common.vo.SysUserDeptVo;
import com.yg.pj.sys.dao.SysRoleUserDao;
import com.yg.pj.sys.dao.SysUserDao;
import com.yg.pj.sys.entity.SysUser;
import com.yg.pj.sys.service.SysUserService;

import lombok.extern.slf4j.Slf4j;


@Transactional(timeout = 30,  //超时时间,超时多久回滚  请求?
readOnly = false,		//只读选择  
isolation = Isolation.READ_COMMITTED, //事务隔离级别  读已提交(避免脏读,不能避免不可重复读和幻读)
rollbackFor = Throwable.class,			//指定回滚 类型
propagation = Propagation.REQUIRED)
/**  !!!!!!!!!!!!!!!!!!!!!!!!注解的特性进入源码查看!!!!!!!!!!!!!!!!!!!!!!!!
 @Transactional 加载类上面定义类里面方法的共性
 				加在方法上,可以定义特性,优先级高于类
 timeout   超时时间,超时多少秒需要 回滚! 默认是 -1,不能出现超时.
 readOnly  默认为false,有些只读方法可以设置true   只读的时候 是允许事务并发  性能较好   ???
 isolation = Isolation.READ_COMMITTED, //事务隔离级别 
  读已提交(避免脏读,不能避免不可重复读和幻读)
  READ_UNCOMMITTED   READ_COMMITTED  REPEATABLE_READ  SERIALIZABLE
  依次为:   1)读未提交        2)读已提交        3) 可重复读         4)串行化
  常用2,3. 从左到右,安全性越来越高,性能越来越差.
  

 rollbackFor 出现什么异常回滚,一般写RuntimeException.class 运行时异常
 			所有异常都回滚 Throwable.class
 noRollbackFor  指定哪些异常不需要回滚
 
 
 
 * */







@Service
@Slf4j
public class SysUserServiceImpl implements SysUserService {
	
	
	
		
		//用户dao 注入  //SysUserDao
		@Autowired
		private SysUserDao sysUserDao;
		//角色用户dao 注入
		@Autowired
		private SysRoleUserDao sysRoleUserDao;
		
		@Autowired
		private PageProperties pageProperties;
		
		

		
		 //在需要异步执行的业务方法上，使用@Async方法进行异步声明。为什么不能？
		@CachePut(value = "userCache")
		@Transactional(readOnly = true)
		@RequestLogP("select") //默认为value,value默认为""  空字符串 
		@RequestTime
		@Override
		public PageObject<SysUserDeptVo> findPageObjects(Integer pageCurrent, String username) {
			
		try {
			Thread.sleep(0);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
			String tName = Thread.currentThread().getName();
		
		  //Thread t = new Thread(); String tName = t.getName();
		 
			System.out.println("走业务层  查询 用户----------------findPageObjects tName:"+tName);
			
			//1.校验参数
			//pageCurrent
			if(pageCurrent==null ||pageCurrent<=0)
				throw new IllegalArgumentException("当前页码值不正确");
			//username
			int rowCount =sysUserDao.getRowCount(username);
			if(rowCount==0)
				throw new ServiceException("没有找到该用户名对应记录");
			//2.0  数据准备
			int pageSize = pageProperties.getPageSize();
			int pageCount =(rowCount-1)/pageSize +1;
			int startIndex =(pageCurrent-1)*pageSize;
			
			
			
			//2.调用dao层方法
			List<SysUserDeptVo> records =sysUserDao.findPageObjects(username,startIndex,pageSize);
			
			
			//3.结果处理
			
			//3.5添加数据
			
			
			//4.返回结果 /null
			PageObject<SysUserDeptVo> pageObject = new PageObject<SysUserDeptVo>(pageCurrent,pageSize,rowCount,pageCount,records);
			
			System.out.println("pageObject================="+pageObject.toString());
			return pageObject;
		}

		
		@RequiresPermissions("sys:user:valid")
		@RequestLogP("validById") //默认为value,value默认为""  空字符串 
		@Transactional(propagation = Propagation.REQUIRES_NEW) //开启新事务
		@Override
		public int validById(Integer id, Integer  valid,String modifiedUser) {
			
		/*
		 * Thread t = new Thread(); String tName = t.getName();
		 */
			
			
			//1.校验参数
			if(id==null ||id<=0)
				throw new IllegalArgumentException("用户id不正确");
			
			if(valid==null)
				throw new IllegalArgumentException("用户修改状态参数不能为null");
			
			if(StringUtils.isEmpty(modifiedUser))
				throw new IllegalArgumentException("修改状态的用户名不能为null");
			
			//2.0 执行前判断
			int row = 0;
			if(valid==0 ||valid==1) {
				//2.1参数调节
				valid=((valid == 0) ? 1:0);  //整数会自动转化为int运算，需要强转为Short
				
				
				
				//2.调用dao层方法
			row =sysUserDao.validById(id,valid,modifiedUser); //需要添加修改用户名
			
			if(row==0)
				throw new ServiceException("修改不成功！");
				
			}else {
				throw new IllegalArgumentException("修改参数有误");
			}
				
			//3.结果处理
			//4.返回结果 /null
			
			return row;
		}


		//插入数据后  全部清除缓存 更新
		@CacheEvict(value="userCache", allEntries = true)
		@Override
		public void saveObject(Integer[] roleIds, SysUser user, String createdUser) {
			//1.校验参数
			if(roleIds==null || roleIds.length ==0)
				throw new IllegalArgumentException("参数角色不能为空");
			
			if(user==null)
				throw new IllegalArgumentException("添加用户信息不能为空");
			
			if(StringUtils.isEmpty(user.getUsername()))
				throw new IllegalArgumentException("添加用户名不能为空");
			
			if(StringUtils.isEmpty(user.getPassword()))
				throw new IllegalArgumentException("添加用户密码不能为空");
			
			//1.5 对密码进行加密处理
			//1.6 通过UUID 获取盐值
			String salt =UUID.randomUUID().toString();
			
			String password =user.getPassword();
			
			//使用Shiro框架进行加密
		SimpleHash simpleHash =   //
				new	SimpleHash(  //Shiro框架
						"MD5",   //algorithmName 算法
						password,  //原密码
						salt,  //盐值
						1);  //hashIterations表示加密次数
		
			//String saltpassword = getMD5pwd(salt, password);
		String saltpassword = simpleHash.toHex(); //使用Shrio加密密码
			//1.9 完善user数据 
			// 默认状态是0
			Integer valid =1; //默认状态是1
			user.setValid(valid);
			user.setSalt(salt);
			user.setPassword(saltpassword); //密码脱敏
			user.setCreatedTime(new Date());
			user.setModifiedTime(user.getCreatedTime());
			user.setCreatedUser(createdUser);
			user.setModifiedUser(user.getCreatedUser());
			
			//2.0 添加用户前数据准备
			
			//2.调用dao层方法
			int row =sysUserDao.insert(user);
			if(row==0)
				throw new ServiceException("用户添加不成功！");
			//2.2 插入用户-部门 关系数据
			Integer userId =user.getId();
			
		int rowDept =sysRoleUserDao.insert(userId,roleIds);
		if(rowDept==0)
			throw new ServiceException("部门添加不成功！");
			//3.结果处理
			//4.返回结果 /null
			
		}




		/**
		 * 核心算法 密文=MD5(salt+密码明文+salt)；
		 * @param salt 盐值
		 * @param password  原明文密码
		 * @return  saltpassword 加密后的密文密码
		 */
		@SuppressWarnings("unused")
		private String getMD5pwd(String salt, String password) {
			//1.7 对明文密码 进行加盐 获得加盐密码 
			String saltpassword =salt+password+salt;
			log.info("【】【】------------ ------------  加盐密码 saltpassword："+saltpassword);
			
			
			//
			//1.8 对 加盐密文 进行md5 加密获得 加密 密文
			String md5pwd = DigestUtils.md5DigestAsHex(saltpassword.getBytes());
			log.info("【】【】------------ ------------  加密 密文 md5pwd："+md5pwd);
			return saltpassword;
		}


		@Transactional(readOnly = true)
		@RequestLogP
		@Override
		public SysUserDeptVo findObjectById(Integer id) {
			//根据id，查询到一个完整页面信息，返回（和添加时的页面一样）
			//1.
			//1.校验参数
			if(id==null || id<=0)
				throw new IllegalArgumentException("用户上传参数不正确");
			
			SysUserDeptVo sysUserDeptVo =sysUserDao.findObjectById(id);
			//现在查询出来一个问题是 从数据库查询的是密文 需要对密文进行解密吗？不需要
			//这只是展示数据，不展示密码，也不展示salt
			
			
			//2.调用dao层方法
			//3.结果处理
			//4.返回结果 /null
			return sysUserDeptVo;
		}

		
		@RequiresPermissions("sys:user:update")
		 //修改数据后  更新所有 清除所有userCache缓存 更新
		@CacheEvict(value="userCache" ,allEntries = true) 
		@RequestLogP
		@Override
		public void updateObject(String modifiedUser, SysUser user, Integer[] roleIds) {
			//根据用户提交页面数据进行修改
			
			//1.校验参数
			//1.校验参数
			if(roleIds==null || roleIds.length ==0)
				throw new IllegalArgumentException("修改提交角色不能为空");
			
			if(user==null)
				throw new IllegalArgumentException("修改提交用户信息不能为空");
			
			if(StringUtils.isEmpty(user.getUsername()))
				throw new IllegalArgumentException("修改提交用户名不能为空");
			
			//1.5 准备数据
			user.setModifiedUser(modifiedUser);
			user.setModifiedTime(new Date());
			//2.调用dao层方法
			int row =sysUserDao.updateObject(user);
			
			if(row==0)
				throw new ServiceException("用户修改不成功！");
			
			//2.2  修改 用户-角色 关系数据
			Integer userId =user.getId();
			//2.3先删除 后增加
			sysRoleUserDao.deleteObjectsByRoleId(userId);
			//2.4 新增 用户-角色关系 信息
		int rowRole =sysRoleUserDao.insert(userId,roleIds);
		if(rowRole==0)
			throw new ServiceException("角色修改 不成功！");
			
			//4.返回结果 /null
			
		}




		@Override
		public void updatePassword(String pwd, String newPwd, String cfgPwd,String modifiedUser) {
			//1.校验参数
			if(pwd==null || StringUtils.isEmpty(pwd))
				throw new IllegalArgumentException("原密码不能为空");
			
			if(newPwd==null || StringUtils.isEmpty(newPwd))
				throw new IllegalArgumentException("新密码不能为空");
			
			if(cfgPwd==null || StringUtils.isEmpty(cfgPwd))
				throw new IllegalArgumentException("确认新密码不能为空");
			
			if(!cfgPwd.equals(newPwd))
				throw new IllegalArgumentException("新密码和确认密码不一致！");
			
			if(pwd.equals(newPwd))
				throw new IllegalArgumentException("新密码和原密码重复！");
			
			//2.确认原密码是否正确
			//2.1 根据id 查询数据库中的密文 md5PassWord 和 盐值 salt 获得
			// 2.1.1 获取登录用户  从内存中获取  登录的时候从数据库查询出来的 
			//修改密码后需要  ！！！重新登录 再次登录shrio 中的盐值 才会更新！！！！！！
			//连续该两次密码 ？？？
			SysUser user=ShiroUtil.getUser();
			log.info("<<<----【】【】【】【】--------------------  user："+user.toString());
			//用户 内部获取校验
			
			
			// 2.1.2 获取需要参数
			Integer id=user.getId();
			String salt = user.getSalt();
			//获取真实 密文
			String md5password = user.getPassword();
			log.info("<<<----【】【】【】【】--------------------真实 密文  md5password："+md5password);
			
			//2.1.3  获取上传原密码 密文(为了校验)
			SimpleHash sh=new SimpleHash("MD5",pwd, salt, 1);
			String MD5pwd =sh.toHex();

			//2.1.4真实密文 和上传原密码 密文比较  验证用户有效性
			if(!MD5pwd.equals(md5password))
				throw new IllegalArgumentException("原密码 不正确！");
			//通过继续下面
			
			//3.0获取新的盐值
			String newSalt = UUID.randomUUID().toString();
			// 3 利用新盐值 和新密码明文  获得新密码密文
			String MD5Newpwd=new SimpleHash("MD5",newPwd,newSalt, 1).toHex();
			
			
			
			//4 调用方法 根据用户id 使用新盐值 新密码 修改人 更新密码
			int row = sysUserDao.updateNewMd5Psd(id,MD5Newpwd,newSalt,modifiedUser);
			
			//6.结果处理
			if(row==0)
				throw new ServiceException("新密码更新失败！");
			//4.返回结果 /null
			
			//------------- 要配置文件SecurityUtils  ---------
			
		}




		

}
