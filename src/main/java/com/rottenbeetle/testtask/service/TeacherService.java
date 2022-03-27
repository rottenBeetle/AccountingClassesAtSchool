package com.rottenbeetle.testtask.service;

import com.rottenbeetle.testtask.entity.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    Teacher getTeacher(Long id);
    void saveTeacher(Teacher teacher);
    void deleteTeacher(Long id);
}
