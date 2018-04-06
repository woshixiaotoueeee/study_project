package org.jxau.lctoh.tool.base.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 基础Dao层类，封装了一些基础抽象方法
 * @author qdt_PC
 */
@Repository("BaseDao")
public abstract class BaseDao {
	
	//@Autowired
	SqlSession sqlSession;
	
	/**
	 * 获取接口的代理类
	 * @param mapperClass mapper接口的class
	 * @return	对应mapper实现类
	 */
	protected <T> T getMapper(Class<T> mapperClass){
		return sqlSession.getMapper(mapperClass);
	}
	
	
	/**给对应mapper接口赋值*/
	public abstract void puttMapper();
}