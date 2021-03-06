package com.saas.common;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * author  魏鹏
 * date    2017年9月1日
 * title   service 公共接口
 * describe  根据情况是否继承此 service 接口
 *
 */
public interface IBaseService<T> {
	

	/**
	 * Auth: dingpengfei
	 * Date: 2017/9/4 15:13
	 * 根据实体类新建一条数据
	 * @param entity 插入实体
	 * @return int 返回值
	 */
	int insert(T entity) throws Exception;
	

	/**
	 * Auth: dingpengfei
	 * Date: 2017/9/4 15:13
	 * 根据id删除一条数据
	 * @param id 主键ID
	 * @return 操作结果
	 */
	int deleteById(Serializable id) throws Exception;
	
	

	/**
	 * Auth: dingpengfei
	 * Date: 2017/9/4 15:13
	 * 根据实体类更新数据
	 * @param entity 更新实体
	 * @return 返回值
	 */
	int update(T entity) throws Exception;
	

	/**
	 * Auth: dingpengfei
	 * Date: 2017/9/4 15:13
	 * 获取所有数据
	 * @return 查询所有数据
	 */
	List<T> getAll() throws Exception;


	/**
	 * Auth: dingpengfei
	 * Date: 2017/9/4 15:13
	 * 根据id获取对象
	 * param id id
	 * return 实体
	 */
	T getById(Serializable id) throws Exception;

	/**
	 * Auth: dingpengfei
	 * Date: 2017/9/4 15:13
	 * Param: params 参数map
	 * Return: list 集合
	 **/
	List<T> getByParam(Map<String, Object> params) throws Exception;
	/**
	 * Auth: dingpengfei
	 * Date: 2017/9/4 15:13
	 * Param: params 参数map
	 * Return: page 分页信息
	 **/
	PageInfo<T> getPageByParam(Map<String, Object> params, Integer pageNum, Integer pageSize) throws Exception;
	/**
	 * Auth: dingpengfei
	 * Date: 2017/10/17 15:53
	 * Title: 更新
	 * Param: map 参数列表
	 * Return: void
	 **/
	int updateByMap(Map<String, Object> map) throws Exception;
}
