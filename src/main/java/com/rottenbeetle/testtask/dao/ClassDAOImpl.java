package com.rottenbeetle.testtask.dao;

import com.rottenbeetle.testtask.entity.Class;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ClassDAOImpl implements ClassDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Class> getAllClasses() {
        Session session = sessionFactory.getCurrentSession();
        List<Class> classes = session.createQuery("from Class", Class.class).getResultList();
        return classes;
    }

    @Override
    public Class getClass(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Class aClass = session.get(Class.class,id);
        return aClass;
    }

    @Override
    public void saveClass(Class myClass) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(myClass);
    }

    @Override
    public void deleteClass(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Class> query = session.createQuery("delete from Class where id =:classId");
        query.setParameter("classId",id);
        query.executeUpdate();
    }
}
