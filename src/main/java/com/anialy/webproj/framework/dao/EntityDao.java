package com.anialy.webproj.framework.dao;

import java.io.Serializable;

import com.anialy.webproj.framework.domain.Entity;


public interface EntityDao {

	/* persist method */
	<ID extends Serializable, T extends Entity<ID>> void save(T entity);
}
