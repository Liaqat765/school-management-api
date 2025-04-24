package com.school.schoolmanagement.repository;

import com.school.schoolmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Liaqat Ali Sanjrani
 *
 *         24-April-2025
 *
 */

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAll(Pageable pageable);
    boolean existsByEmail(String email);
}
