package com.saas.common;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * author  魏鹏
 * date    2017年9月1日
 * title   serviceImpl 类
 * descri  根据情况是否继承此serviceImpl
 */
public class BaseServiceImpl<T> implements IBaseService<T> {
	@PostConstruct//在构造方法执行之后执行
	private void initBaseService() throws Exception{
		ParameterizedType type=(ParameterizedType) this.getClass().getGenericSuperclass();
		Class clazz=(Class) type.getActualTypeArguments()[0];
		String localField=clazz.getSimpleName().substring(0,1).toLowerCase()+
				clazz.getSimpleName().substring(1)+"Dao";
		Field field=this.getClass().getDeclaredField(localField);//例如，得到supplierdao属性
		field.setAccessible(true);
		Field baseField=this.getClass().getSuperclass().getDeclaredField("dao");
		baseField.setAccessible(true);
		baseField.set(this, field.get(this));//把basedao的引用，设置到去引用真正指向的堆内存
	}

	private IBaseDao<T> dao;

	/**
	 * 插入一条记录
	 * @param entity t
	 * @return int
	 */
	@Override
	@Transactional
	public int insert(T entity) {
		return dao.insert(entity);
	}

	/**
	 * 根据id删除
	 * @param id 主键
	 * @return 删除成功
	 */
	@Override
	@Transactional
	public int deleteById(Serializable id) {
		return dao.deleteById(id);
	}

	/**
	 * 更新
	 * @param entity 实体
	 * @return 更新成功
	 */
	@Override
	@Transactional
	public int update(T entity) {
		return dao.update(entity);
	}

	/**
	 * 查询所有
	 * @return list
	 */
	@Override
	public List<T> getAll() {
		return dao.getAll();
	}

	/**
	 * 根据id查询
	 * @param id id
	 * @return t
	 */
	@Override
	public T getById(Serializable id) {
		return dao.getById(id);
	}

	/**
	 * 根据条件查询
	 * @param params 参数map
	 * @return 返回list
	 */
	@Override
	public List<T> getByParam(Map<String, Object> params) {
		return dao.getByParam(params);
	}
	/**
	 * 根据条件查询分页
	 * @param params 参数map
	 * @return 返回分页信息
	 */
	@Override
	public PageInfo<T> getPageByParam(Map<String, Object> params,Integer pageNum,Integer pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		return new PageInfo<>(this.getByParam(params));
	}

	@Override
	public int updateByMap(Map<String, Object> map)  {
		return  dao.updateByMap(map);
	}


}
