package com.rottenbeetle.testtask.repo;

import com.rottenbeetle.testtask.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    @Query("SELECT t FROM Teacher t WHERE lower(CONCAT(t.firstName, ' ', t.lastName," +
            " ' ', t.patronymic, ' ', t.dateBirth, ' ', t.gender, ' ', t.mainSubject)) LIKE %?1%")
    Page<Teacher> findAll(String keyword, Pageable pageable);
}
