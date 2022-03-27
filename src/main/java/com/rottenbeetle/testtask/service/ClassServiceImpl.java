package com.rottenbeetle.testtask.service;

import com.rottenbeetle.testtask.dao.ClassDAO;
import com.rottenbeetle.testtask.entity.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ClassServiceImpl implements ClassService{
    @Autowired
    private ClassDAO classDAO;

    @Override
    @Transactional
    public List<Class> getAllClasses() {
        return classDAO.getAllClasses();
    }

    @Override
    @Transactional
    public Class getClass(Long id) {
        return classDAO.getClass(id);
    }

    @Override
    @Transactional
    public void saveClass(Class myClass) {
        classDAO.saveClass(myClass);
    }

    @Override
    @Transactional
    public void deleteClass(Long id) {
        classDAO.deleteClass(id);
    }
}
