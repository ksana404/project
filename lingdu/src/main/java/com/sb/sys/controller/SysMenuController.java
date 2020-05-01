package com.sb.sys.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sb.sys.entity.SysMenu;
import com.sb.sys.entity.vo.JsonResult;
import com.sb.sys.entity.vo.Node;
import com.sb.sys.service.SysMenuService;

/**本类用于菜单模块 
 * 控制层实现 调用业务层
 * 用于请求数据处理   和响应数据处理
 * @author Administrator
 *
 */
@RequestMapping("/menu/")
@RestController 
//由于页面跳转已经有了专门的Controller处理，所以其他几乎都是数据返回
public class SysMenuController {
	
	
	@Autowired
	private SysMenuService sysMenuService;
	
	
	
	/**1.页面请求菜单数据，用于展示到页面
	 * url:http://localhost:8090/menu/doFindObjects
	 * 比数据库中多了一个上级菜单名字，我们可以使用Object封装
	 * 返回值  JsonResult  list<key,Object>
	 * */

	@RequestMapping("doFindObjects")
	public JsonResult doFindObjects() {
		//1.调用业务层查询数据
		List<Map<String,Object>> list = sysMenuService.findObjects();
		//2.返回数据
		return new JsonResult(list);
		
	}
	
	/*菜单管理删除 操作
	 * 根据用户提交的菜单id 删除菜单信息
	 * http://localhost:8090/menu/doDeleteObject
	 * 
	 * */

	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id) {
		sysMenuService.deleteObject(id);
		
		return new JsonResult("成功删除！");
	
	}
	
	/*查询获得树形菜单节点
	 * http://localhost:8090/menu/doFindZtreeMenuNodes
	 * */
	@RequestMapping("doFindZtreeMenuNodes")
	public JsonResult doFindZtreeMenuNodes(Integer id) {
	 List<Node> list =sysMenuService.findZtreeMenuNodes();
		
		return new JsonResult(list);
	}
	
	/**
	 * http://localhost:8090/menu/doUpdateObject
	 * 用户修改 菜单数据
	 * */
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysMenu entity) {
	
		sysMenuService.updateObject(entity);
		
		return new JsonResult("修改成功！");
	}
	
	/**http://localhost:8090/menu/doSaveObject
	 * 保存新添加的菜单信息
	 * 
	 * */
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysMenu entity) {
	
		sysMenuService.saveObject(entity);
		
		return new JsonResult("保存成功！");
	}
	
	
}
