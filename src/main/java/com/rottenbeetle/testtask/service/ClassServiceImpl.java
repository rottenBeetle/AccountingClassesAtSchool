package com.rottenbeetle.testtask.service;

import com.rottenbeetle.testtask.entity.Class;
import com.rottenbeetle.testtask.repo.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService{

    @Autowired
    private ClassRepository classRepository;

    @Override
    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    @Override
    public Class saveClass(Class aClass) {
        return classRepository.save(aClass);
    }

    @Override
    public Class getClassById(Long id) {
        Optional<Class> optional = classRepository.findById(id);
        Class aClass = null;
        if (optional.isPresent()){
            aClass = optional.get();
        }else {
            throw new RuntimeException("Class not found for id :: " + id);
        }
        return aClass;
    }

    @Override
    public void deleteClassById(Long id) {
        classRepository.deleteById(id);
    }

    @Override
    public Page<Class> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection,String keyword) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
           Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize,sort);
        if (keyword != null){
            return classRepository.findAll(keyword.toLowerCase(),pageable);
        }
        return classRepository.findAll(pageable);
    }
}
