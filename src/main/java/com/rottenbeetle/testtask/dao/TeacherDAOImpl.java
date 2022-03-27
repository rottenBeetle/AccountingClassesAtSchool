package com.rottenbeetle.testtask.dao;

import com.rottenbeetle.testtask.entity.Class;
import com.rottenbeetle.testtask.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherDAOImpl implements TeacherDAO{

    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Teacher> getAllTeachers() {
        Session session = sessionFactory.getCurrentSession();
        List<Teacher> teachers = session.createQuery("from Teacher", Teacher.class).getResultList();
        return teachers;
    }

    @Override
    public Teacher getTeacher(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Teacher teacher = session.get(Teacher.class,id);
        return teacher;
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(teacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Teacher> query = session.createQuery("delete from Teacher where id =:teacherId");
        query.setParameter("teacherId",id);
        query.executeUpdate();
    }
}
