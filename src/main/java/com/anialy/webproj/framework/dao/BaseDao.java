package com.anialy.webproj.framework.dao;

import java.io.Serializable;
import java.util.List;



public interface BaseDao {

	<T extends Serializable> List<T> find(String dslStr, String[] names, Object[] values);

	<T extends Object> T query(String dslStr, Object... values);

	<T extends Object> T query(String dslStr, String[] names, Object[] values);

	int execute(String hql, Object... values);

	int execute(String hql, String[] names, Object[] values);

}