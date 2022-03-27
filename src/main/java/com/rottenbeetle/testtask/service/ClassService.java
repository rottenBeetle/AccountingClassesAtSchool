package com.rottenbeetle.testtask.service;

import com.rottenbeetle.testtask.entity.Class;

import java.util.List;

public interface ClassService {
    List<Class> getAllClasses();
    Class getClass(Long id);
    void saveClass(Class myClass);
    void deleteClass(Long id);
}
