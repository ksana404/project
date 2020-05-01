package com.yg.pj.sys.controller;

import java.rmi.ServerException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yg.pj.sys.common.vo.JsonResult;
import com.yg.pj.sys.entity.SysMenu;
import com.yg.pj.sys.service.SysMenuService;

/**本模块通过业务层对象执行业务逻辑，
 * 再通过VO对象封装响应结果(主要对业务层数据添加状态信息)，
 * 最后将响应结果转换为JSON格式的字符串响应到客户端。
 * @author Administrator
 *
 */
@RequestMapping("/menu/")
@RestController
public class SysMenuController {
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@RequestMapping("doFindObjects")
	public JsonResult doFindObjects() throws ServerException {
		List<Map<String, Object>> listMap = sysMenuService.findObjects();
		return new JsonResult(listMap);
		
	}
	
	
	/***用于执行删除业务的方法**/
	/**http://localhost:9099/menu/doDeleteObject?id=48
	 * @param id
	 * @return
	 */
	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id){
		int rows = sysMenuService.deleteObject(id);
		
		return new JsonResult("成功删除"+rows+"行数据");
	}
	
	/**基于客户端请求,访问业务层对象方法,获取菜单节点对象,并封装返回
	 * 
	 * */
	@RequestMapping("doFindZtreeMenuNodes")
	 public JsonResult doFindZtreeMenuNodes(){
		 return new JsonResult(
		 sysMenuService.findZtreeMenuNodes());
	 }

	
	//http://localhost:9099/menu/doSaveObject
	/**
	 * @param entity
	 * @return
	 */
	@RequestMapping("doSaveObject")
	 public JsonResult doSaveObject(SysMenu entity){

		sysMenuService.saveObject(entity);
		
		return new JsonResult("成功保存");
	 }
	
	
	/**http://localhost:9099/menu/doUpdateObject
	 * 根据菜单id修改，菜单信息
	 * 
	 * */
	@RequestMapping("doUpdateObject")
	 public JsonResult doUpdateObject(SysMenu entity){

		sysMenuService.updateObject(entity);
		
		return new JsonResult("成功修改");
	 }
	
	
	
	
	
}
