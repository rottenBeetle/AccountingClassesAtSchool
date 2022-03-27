package com.rottenbeetle.testtask.service;

import com.rottenbeetle.testtask.dao.TeacherDAO;
import com.rottenbeetle.testtask.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDAO teacherDAO;

    @Override
    @Transactional
    public List<Teacher> getAllTeachers() {
        return teacherDAO.getAllTeachers();
    }

    @Override
    @Transactional
    public Teacher getTeacher(Long id) {
        return teacherDAO.getTeacher(id);
    }

    @Override
    @Transactional
    public void saveTeacher(Teacher teacher) {
        teacherDAO.saveTeacher(teacher);
    }

    @Override
    @Transactional
    public void deleteTeacher(Long id) {
        teacherDAO.deleteTeacher(id);
    }
}
