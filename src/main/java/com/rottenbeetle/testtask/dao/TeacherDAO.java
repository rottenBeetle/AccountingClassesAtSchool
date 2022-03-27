package com.rottenbeetle.testtask.dao;

import com.rottenbeetle.testtask.entity.Teacher;
import java.util.List;

public interface TeacherDAO {
    List<Teacher> getAllTeachers();
    Teacher getTeacher(Long id);
    void saveTeacher(Teacher teacher);
    void deleteTeacher(Long id);
}
