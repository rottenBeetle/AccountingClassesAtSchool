package com.rottenbeetle.testtask.service;

import com.rottenbeetle.testtask.entity.Class;
import com.rottenbeetle.testtask.entity.Teacher;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    void saveTeacher(Teacher teacher);
    Teacher getTeacherById(Long id);
    void deleteTeacherById(Long id);
    Page<Teacher> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String keyword);
}
