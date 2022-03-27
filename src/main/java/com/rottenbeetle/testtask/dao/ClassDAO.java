package com.rottenbeetle.testtask.dao;

import com.rottenbeetle.testtask.entity.Class;

import java.util.List;

public interface ClassDAO {
    List<Class> getAllClasses();
    Class getClass(Long id);
    void saveClass(Class myClass);
    void deleteClass(Long id);
}
