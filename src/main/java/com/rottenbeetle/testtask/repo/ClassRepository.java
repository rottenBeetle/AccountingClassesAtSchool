package com.rottenbeetle.testtask.repo;

import com.rottenbeetle.testtask.entity.Class;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClassRepository extends JpaRepository<Class, Long> {
    @Query("SELECT c FROM Class c WHERE lower(CONCAT(c.yearOfStudy, ' ', c.mnemonicCode, ' ', c.teacher.firstName, ' ', c.teacher.lastName, ' ', c.teacher.patronymic)) LIKE %?1%")
     Page<Class> findAll(String keyword, Pageable pageable);
}
