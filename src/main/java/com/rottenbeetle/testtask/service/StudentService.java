package com.rottenbeetle.testtask.service;

import com.rottenbeetle.testtask.entity.Student;
import com.rottenbeetle.testtask.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    void saveStudent(Student student);
    Student getStudentById(Long id);
    void deleteStudentById(Long id);
    Page<Student> findPaginated(int pageNo, int pageSize, String sortField,
                                String sortDirection, String keyword);
}
