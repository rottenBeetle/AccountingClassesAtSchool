package com.rottenbeetle.testtask.repo;

import com.rottenbeetle.testtask.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
