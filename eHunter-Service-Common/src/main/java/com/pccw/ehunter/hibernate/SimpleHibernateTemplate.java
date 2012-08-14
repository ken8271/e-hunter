package com.pccw.ehunter.hibernate;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.pccw.ehunter.constant.TransactionIndicator;
import com.pccw.ehunter.domain.BaseEntity;

@SuppressWarnings("unchecked")
public class SimpleHibernateTemplate<T, PK extends Serializable> extends HibernateDaoSupport{

	protected Logger logger = LoggerFactory.getLogger( getClass() );

	protected SessionFactory sessionFactory;

	protected Class<T> entityClass;

	public SimpleHibernateTemplate (SessionFactory sessionFactory, Class<T> entityClass) {
		this.sessionFactory = sessionFactory;
		this.entityClass = entityClass;
		super.setSessionFactory(sessionFactory);
	}

	public void save (T entity) {
		Assert.notNull( entity );
		if(entity instanceof BaseEntity){
			((BaseEntity)entity).setLastTransactionIndicator(TransactionIndicator.INSERT);
		}
		getSession().save( entity );
		logger.debug( "save entity: {}", entity );
	}

	public void update (T entity) {
		Assert.notNull( entity );
		getSession().merge( entity );
		logger.debug( "update entity: {}", entity );
	}

	public void saveOrUpdate (T entity) {
		Assert.notNull( entity );
		if(entity instanceof BaseEntity){
			((BaseEntity)entity).setLastTransactionIndicator(TransactionIndicator.UPDATE);
		}
		getSession().saveOrUpdate( entity );
		logger.debug( "save or update entity: {}", entity );
	}

	public void delete (T entity) {
		Assert.notNull( entity );
		getSession().delete( entity );
		logger.debug( "delete entity: {}", entity );
	}
	
	public void markDeleted (T entity) {
		Assert.notNull( entity );
		if(entity instanceof BaseEntity){
			((BaseEntity)entity).setLastTransactionIndicator(TransactionIndicator.DELETE);
		}
		getSession().update( entity );
		logger.debug( "mark entity as deleted: {}", entity );
	}
	
	public void delete (PK id) {
		Assert.notNull( id );
		delete( get(id) );
	}

	public List<T> findAll ( ) {
		return findByCriteria( );
	}

	/**
	 * 按id获取对象.
	 */
	
	public T get (final PK id) {
		return (T) getSession().get( entityClass, id );
	}
	
	public T load (final PK id) {
		return (T) getSession().load( entityClass, id );
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param hql
	 *            hql语句
	 * @param values
	 *            数量可变的参数
	 */
	public List<T> find (String hql, Object... values) {
		return createQuery(hql, values).list( );
	}

	/**
	 * 按HQL查询唯一对象.
	 */
	public Object findUnique (String hql, Object... values) {
		return createQuery( hql, values ).uniqueResult( );
	}

	/**
	 * 按HQL查询Intger类形结果.
	 */
	public Integer findInt (String hql, Object... values) {
		return (Integer) findUnique( hql, values );
	}

	/**
	 * 按HQL查询Long类型结果.
	 */
	public Long findLong (String hql, Object... values) {
		return (Long) findUnique( hql, values );
	}
	
	public List<T> findAllUndeleted() {
		if( BaseEntity.class.isAssignableFrom(entityClass) ){
			Criterion criteria = Restrictions.ne("lastTransactionIndicator", "D");
			return createCriteria( criteria).list( );
		}else{
			return createCriteria().list( );
		}
	}

	/**
	 * 按Criterion查询对象列表.
	 * 
	 * @param criterion
	 *            数量可变的Criterion.
	 */
	public List<T> findByCriteria (Criterion... criterion) {
		return createCriteria( criterion ).list( );
	}
	
	public List<T> findByDetachedCriteria (DetachedCriteria detachedCriteria) {
		return detachedCriteria.getExecutableCriteria(this.getSession()).list( );
	}
	
	/**
	 * 按属性查找对象列表.
	 */
	public List<T> findByProperty (String propertyName, Object value) {
		Assert.hasText( propertyName );
		return createCriteria( Restrictions.eq( propertyName, value ) ).list( );
	}
	
	/**
	 * 按属性查找对象列表. 不查找被删除了的对象
	 */
	public List<T> findUndeletedByProperty (String propertyName, Object value) {
		Assert.hasText( propertyName );
		Criterion criteria1 = Restrictions.eq( propertyName, value ); 
		if( BaseEntity.class.isAssignableFrom(entityClass) ){
			Criterion criteria2 = Restrictions.ne("lastTransactionIndicator", "D");
			return createCriteria( criteria1, criteria2).list( );
		}else{
			return createCriteria( criteria1).list( );
		}
		
	}
	
	/**
	 * 按属性查找唯一对象.
	 */
	public T findUniqueByProperty (String propertyName, Object value) {
		Assert.hasText( propertyName );
		return (T) createCriteria( Restrictions.eq( propertyName, value ) )
				.uniqueResult( );
	}

	/**
	 * 根据查询函数与参数列表创建Query对象,后续可进行更多处理,辅助函数.
	 */
	public Query createQuery (String queryString, Object... values) {
		Assert.hasText( queryString );
		Query queryObject = getSession().createQuery( queryString );
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter( i, values[i] );
			}
		}
		return queryObject;
	}

	/**
	 * 根据Criterion条件创建Criteria,后续可进行更多处理,辅助函数.
	 */
	public Criteria createCriteria (Criterion... criterions) {
		Criteria criteria = getSession().createCriteria( entityClass );
		for (Criterion c : criterions) {
			criteria.add( c );
		}
		return criteria;
	}

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下,如果属性新修改的值(value)等于属性原值(orgValue)则不作比较.
	 * 传回orgValue的设计侧重于从页面上发出Ajax判断请求的场景. 否则需要SS2里那种以对象ID作为第3个参数的isUnique函数.
	 */
	public boolean isPropertyUnique (String propertyName, Object newValue, Object orgValue) {
		if (newValue == null || newValue.equals( orgValue )) {
			return true;
		}

		Object object = findUniqueByProperty( propertyName, newValue );
		return (object == null);
	}
	
}