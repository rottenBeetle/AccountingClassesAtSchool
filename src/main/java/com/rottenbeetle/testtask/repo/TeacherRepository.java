package com.rottenbeetle.testtask.repo;

import com.rottenbeetle.testtask.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
