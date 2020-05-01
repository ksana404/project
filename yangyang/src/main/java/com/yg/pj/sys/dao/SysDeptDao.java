package com.yg.pj.sys.dao;
//com.yg.pj.sys.dao.SysDeptDao

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yg.pj.sys.common.vo.Node;
import com.yg.pj.sys.entity.SysDept;

@Mapper
public interface SysDeptDao {
	
	/**通过部门id 查询部门详情信息
	 * @param id
	 * @return
	 */
	SysDept findById(Integer id);

	/**查询部门上下级关系
	 * @return
	 */
	List<Node> findZtreeMenuNodes();

}
