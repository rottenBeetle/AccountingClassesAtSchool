package com.rottenbeetle.testtask.dao;

import com.rottenbeetle.testtask.entity.Class;
import com.rottenbeetle.testtask.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StudentDAO {
    List<Student> getAllStudents();
    Student getStudent(Long id);
    void saveStudent(Student student);
    void deleteStudent(Long id);
}
