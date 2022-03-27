package com.rottenbeetle.testtask.dao;

import com.rottenbeetle.testtask.entity.Student;
import com.rottenbeetle.testtask.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
public class StudentDAOImpl implements StudentDAO{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public List<Student> getAllStudents() {
        Session session = sessionFactory.getCurrentSession();
        List<Student> students = session.createQuery("from Student", Student.class).getResultList();
        return students;
    }

    @Override
    public Student getStudent(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.get(Student.class,id);
        return student;
    }

    @Override
    public void saveStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Student> query = session.createQuery("delete from Student where id =:studentId");
        query.setParameter("studentId",id);
        query.executeUpdate();
    }
}
