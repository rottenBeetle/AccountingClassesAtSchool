package com.rottenbeetle.testtask.service;

import com.rottenbeetle.testtask.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudent(Long id);
    void saveStudent(Student student);
    void deleteStudent(Long id);
}
