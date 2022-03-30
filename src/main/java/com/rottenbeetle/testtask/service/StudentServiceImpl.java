package com.rottenbeetle.testtask.service;

import com.rottenbeetle.testtask.entity.Student;
import com.rottenbeetle.testtask.entity.Teacher;
import com.rottenbeetle.testtask.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        Optional<Student> optional = studentRepository.findById(id);
        Student student = null;
        if (optional.isPresent()){
            student = optional.get();
        }else {
            throw new RuntimeException("Student not found for id :: " + id);
        }
        return student;
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String keyword) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize,sort);
        if (keyword != null){
            return studentRepository.findAll(keyword.toLowerCase(),pageable);
        }
        return studentRepository.findAll(pageable);
    }
}
