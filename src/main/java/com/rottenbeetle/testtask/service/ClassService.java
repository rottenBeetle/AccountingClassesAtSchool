package com.rottenbeetle.testtask.service;

import com.rottenbeetle.testtask.entity.Class;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClassService {
    List<Class> getAllClasses();
    Class saveClass(Class aClass);
    Class getClassById(Long id);
    void deleteClassById(Long id);
    Page<Class> findPaginated(int pageNo, int pageSize,String sortField,String sortDirection,String keyword);
}
