package com.school.schoolmanagement.repository;

import com.school.schoolmanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Liaqat Ali Sanjrani
 *
 *         24-April-2025
 *
 */

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findAll(Pageable pageable);
    boolean existsByCode(String code);
}
