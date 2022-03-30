package com.rottenbeetle.testtask.repo;

import com.rottenbeetle.testtask.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query("SELECT s FROM Student s WHERE lower(CONCAT(s.firstName, ' ', s.lastName," +
            " ' ', s.patronymic, ' ', s.dateBirth, ' ', s.gender)) LIKE %?1%")
    Page<Student> findAll(String keyword, Pageable pageable);
}
