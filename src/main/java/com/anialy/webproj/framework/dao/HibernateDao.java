//package com.anialy.webproj.framework.dao;
//
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.List;
//
//import org.apache.commons.lang3.ArrayUtils;
//import org.apache.commons.lang3.Validate;
//import org.hibernate.Criteria;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.criterion.CriteriaHelper;
//import org.hibernate.criterion.QueryUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//
//public abstract class HibernateDao implements BaseDao {
//
//	private static final Logger logger = LoggerFactory.getLogger(HibernateDao.class);
//
//	protected abstract Session getSession();
//
//	/*********************************************************************************************************/
//
//	@Override
//	public <T extends Serializable> List<T> find(String hql, String[] names, Object[] values) {
//		return find(hql, names, values, false);
//	}
//
//	public <T extends Serializable> List<T> find(CriteriaHelper criteriaHelper) {
//		return find(criteriaHelper, false);
//	}
//
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public <T extends Object> T query(String hql, Object... values) {
//		if (logger.isDebugEnabled()) {
//			logger.debug("execute hql:[{}] the params is:[{}]", hql, ArrayUtils.toString(values));
//		}
//		return (T) QueryUtils.create(hql, this.getSession(), values).uniqueResult();
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public <T extends Object> T query(String hql, String[] names, Object[] values) {
//		if (logger.isDebugEnabled()) {
//			logger.debug("execute hql:[{}] the params is:[{}] and values is:[{}]",
//				hql,
//				ArrayUtils.toString(names),
//				ArrayUtils.toString(values));
//		}
//		return (T) QueryUtils.create(hql, this.getSession(), names, values).uniqueResult();
//	}
//
//	@Override
//	public int execute(String hql, Object... values) {
//		if (logger.isDebugEnabled()) {
//			logger.debug("execute hql:[{}] the params is:[{}]", hql, ArrayUtils.toString(values));
//		}
//
//		return QueryUtils.create(hql, this.getSession(), values).executeUpdate();
//	}
//
//	@Override
//	public int execute(String hql, String[] names, Object[] values) {
//		if (logger.isDebugEnabled()) {
//			logger.debug("execute hql:[{}] the params is:[{}] and values is:[{}]",
//				hql,
//				ArrayUtils.toString(names),
//				ArrayUtils.toString(values));
//		}
//		return QueryUtils.create(hql, this.getSession(), names, values).executeUpdate();
//	}
//
//	private static final String DEFAULT_CACHE_REGION = "query";
//
//	/*********************************************************************************************************/
//
//	@Deprecated
//	public <ID extends Serializable, T extends Entity<ID>> void update(Class<T> clazz,
//			String keyName,
//			Object[] keyValues,
//			String[] argumentNames,
//			Object[] argumentValues) {
//		Validate.notNull(clazz, "clazz can not be null!");
//		Validate.notNull(keyName, "keyName can not be null!");
//		Validate.noNullElements(keyValues, "keyValues can not be null!");
//		Validate.noNullElements(argumentNames, "argumentNames and elements can not be null!");
//		Validate.notEmpty(argumentValues, "argumentValues can not be null!");
//
//		String entityName = clazz.getName();
//		StringBuilder hql = new StringBuilder();
//		String[] names = ArrayUtils.EMPTY_STRING_ARRAY;
//		Object[] values = ArrayUtils.EMPTY_OBJECT_ARRAY;
//		hql.append("update ").append(entityName).append(" set ");
//		for (int i = 0; i < argumentValues.length; i++) {
//			if (argumentValues[i] == null) {
//				hql.append(argumentNames[i]).append(" = null,");
//			} else {
//				hql.append(argumentNames[i]).append(" = ").append(":").append(argumentNames[i]).append(",");
//				names = (String[]) ArrayUtils.add(names, argumentNames[i]);
//				values = ArrayUtils.add(values, argumentValues[i]);
//			}
//		}
//		hql.deleteCharAt(hql.length() - 1);
//
//		String[] _names = ArrayUtils.EMPTY_STRING_ARRAY;
//		;
//		Object[] _values = ArrayUtils.EMPTY_OBJECT_ARRAY;
//		if (keyValues.length == 1) {
//			hql.append(" where ").append(keyName).append(" = :id");
//
//			_names = (String[]) ArrayUtils.add(names, "id");
//			_values = ArrayUtils.add(values, keyValues[0]);
//		} else {
//			hql.append(" where ").append(keyName).append(" in (:ids)");
//			_names = (String[]) ArrayUtils.add(names, "ids");
//			_values = ArrayUtils.add(values, keyValues);
//		}
//
//		this.execute(hql.toString(), _names, _values);
//	}
//
//	@Deprecated
//	public <ID extends Serializable, T extends Entity<ID>> void update(Class<T> clazz,
//			String[] keyNames,
//			Object[] keyValues,
//			String[] argumentNames,
//			Object[] argumentValues) {
//		Validate.notNull(clazz, "clazz can not be null!");
//		Validate.noNullElements(keyNames, "keyNames can not be null!");
//		Validate.noNullElements(keyValues, "keyValues can not be null!");
//		Validate.noNullElements(argumentNames, "argumentNames and elements can not be null!");
//		Validate.notEmpty(argumentValues, "argumentValues can not be null!");
//
//		String entityName = clazz.getName();
//		StringBuilder hql = new StringBuilder();
//		String[] names = ArrayUtils.EMPTY_STRING_ARRAY;
//		Object[] values = ArrayUtils.EMPTY_OBJECT_ARRAY;
//		hql.append("update ").append(entityName).append(" entity set ");
//		for (int i = 0; i < argumentNames.length; i++) {
//			if (argumentValues[i] == null) {
//				hql.append("entity.").append(argumentNames[i]).append(" = null,");
//			} else {
//				hql.append(argumentNames[i]).append(" = ").append(":argument").append(i).append(",");
//				names = (String[]) ArrayUtils.add(names, "argument" + i);
//				values = ArrayUtils.add(values, argumentValues[i]);
//			}
//		}
//		hql.deleteCharAt(hql.length() - 1);
//		hql.append(" where ");
//		for (int i = 0; i < keyNames.length; i++) {
//			if (keyValues[i].getClass().isArray() || keyValues[i] instanceof Collection<?>) {
//				hql.append(" entity.").append(keyNames[i]).append("in (:keyName").append(i).append(")").append(" and ");
//			} else {
//				hql.append(" entity.").append(keyNames[i]).append("= :keyName").append(i).append(" and ");
//			}
//			names = (String[]) ArrayUtils.add(names, "keyName" + i);
//			values = ArrayUtils.add(values, keyValues[i]);
//		}
//		hql.append(" (1=1) ");
//
//		this.execute(hql.toString(), names, values);
//	}
//
//	/*********************************************************************************************************/
//
//	/** 以下method将可见性设为protected **/
//	/** 避免不规范的调用将hibernate的特性传播到dao层以外 **/
//	/*********************************************************************************************************/
//
//	@SuppressWarnings("unchecked")
//	protected <T extends Serializable> List<T> find(String hql, String[] names, Object[] values, boolean cacheable) {
//		Query query = this.getSession().createQuery(hql);
//		query.setCacheable(cacheable).setCacheRegion(DEFAULT_CACHE_REGION);
//		QueryUtils.setParameter(query, names, values);
//		return (List<T>) query.list();
//	}
//
//	@SuppressWarnings("unchecked")
//	protected <T extends Serializable> List<T> find(CriteriaHelper criteriaHelper, boolean cacheable) {
//		return (List<T>) criteriaHelper.listCriteria()
//			.getExecutableCriteria(getSession())
//			.setCacheable(cacheable)
//			.setCacheRegion(DEFAULT_CACHE_REGION)
//			.list();
//	}
//
//
//
//	protected void flush() {
//		this.getSession().flush();
//	}
//
//}
