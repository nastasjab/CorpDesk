package lv.javaguru.java3.core.database;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class CRUDOperationDAOImpl<E, K extends Serializable> implements CRUDOperationDAO<E, K> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Class daoType;


    public CRUDOperationDAOImpl() {
        daoType = (Class) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void create(E entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public E getById(K key) {
        return (E) getCurrentSession().get(daoType, key);
    }

    @Override
    public E getRequired(K key) {
        E entity = (E) getCurrentSession().get(daoType, key);
        if(entity == null) {
            throw new IllegalArgumentException("Object with id " + key + " doesn't exist!");
        }
        return entity;
    }

    @Override
    public void update(E entity) {
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(E entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public List<E> getAll() {
        return getCurrentSession().createCriteria(daoType).list();
    }

    @Override
    public List<E> findAllWithPagination(int page, int size) {
        Criteria criteria = getCurrentSession().createCriteria(daoType);
        criteria.setFirstResult((page - 1) * size);
        criteria.setMaxResults(size);
        return criteria.list();
    }

}