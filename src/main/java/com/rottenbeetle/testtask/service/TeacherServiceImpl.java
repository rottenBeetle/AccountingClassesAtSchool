package com.rottenbeetle.testtask.service;

import com.rottenbeetle.testtask.entity.Class;
import com.rottenbeetle.testtask.entity.Teacher;
import com.rottenbeetle.testtask.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TeacherServiceImpl implements TeacherService{

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        Optional<Teacher> optional = teacherRepository.findById(id);
        Teacher teacher = null;
        if (optional.isPresent()){
            teacher = optional.get();
        }else {
            throw new RuntimeException("Teacher not found for id :: " + id);
        }
        return teacher;
    }

    @Override
    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Page<Teacher> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String keyword) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize,sort);
        if (keyword != null){
            return teacherRepository.findAll(keyword.toLowerCase(),pageable);
        }
        return teacherRepository.findAll(pageable);
    }
}
