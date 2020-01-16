package org.mountcloud.springcloud.mvc.common.service;

import org.mountcloud.springproject.common.entity.BaseEntity;

import java.util.List;


/**
 * 夫service
 * @author zhanghaishan
 * @version V1.0
 * org.mountcloud.mvc.common.service
 * TODO:
 * 2019年12月31日.
 */
public interface BaseService<E extends BaseEntity> {
	
	/**
	 * 根据一个实体进行查询集合，如user.name="123"，则查询name为123的集合
	 * @param bean
	 * @return
	 */
	List<E> findList(E bean);
	
	
	/**
	 * 根据实体查询一个，如user.name="123"，则查询name为123的集合
	 * @param bean
	 * @return
	 */
	E findOne(E bean);
	
	/**
	 * 保存实体
	 * @param bean
	 * @return
	 */
	E save(E bean);
	
	/**
	 * 更新一个实体
	 * @param bean
	 * @return
	 */
	E update(E bean);
	
	/**
	 * 删除一个实体
	 * @param bean
	 * @return
	 */
	boolean delete(E bean);
	
}
