package com.school.schoolmanagement.service;


import com.school.schoolmanagement.dto.EnrollmentDTO;
import com.school.schoolmanagement.entity.*;
import com.school.schoolmanagement.exception.ResourceNotFoundException;
import com.school.schoolmanagement.repository.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Liaqat Ali Sanjrani
 *
 *         24-April-2025
 *
 */

@Service
public class EnrollmentService {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentService.class);


    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository,
                             ModelMapper modelMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    // Method to enroll a student in a course (ensures the student is not already enrolled)
    public EnrollmentDTO enrollStudent(EnrollmentDTO enrollmentDTO) {
        Student student = studentRepository.findById(enrollmentDTO.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + enrollmentDTO.getStudentId()));

        Course course = courseRepository.findById(enrollmentDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + enrollmentDTO.getCourseId()));

        // Ensure the student is not already enrolled in the course
        if (enrollmentRepository.existsByStudentIdAndCourseId(student.getId(), course.getId())) {
            throw new IllegalArgumentException("Student is already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment(student, course);
        enrollment.setGrade(enrollmentDTO.getGrade());
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
        return modelMapper.map(savedEnrollment, EnrollmentDTO.class);
    }

    // Method to update the grade of a student for a particular course
    public EnrollmentDTO updateGrade(Long enrollmentId, Double grade) {
        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId));

        enrollment.setGrade(grade);
        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);
        return modelMapper.map(updatedEnrollment, EnrollmentDTO.class);
    }

    // Method to get all enrollments of a student
    public List<EnrollmentDTO> getStudentEnrollments(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found with id: " + studentId);
        }

        return enrollmentRepository.findByStudentId(studentId).stream()
                .map(enrollment -> modelMapper.map(enrollment, EnrollmentDTO.class))
                .collect(Collectors.toList());
    }

    // Method to get the grade of a student for a specific course
    public EnrollmentDTO getStudentCourseGrade(Long studentId, Long courseId) {
        Enrollment enrollment = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Enrollment not found for studentId: " + studentId + " and courseId: " + courseId));

        return modelMapper.map(enrollment, EnrollmentDTO.class);
    }

    // Method to unenroll a student from a course by enrollment ID
    public void unenrollStudent(Long enrollmentId) {
        if (!enrollmentRepository.existsById(enrollmentId)) {
            throw new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId);
        }
        enrollmentRepository.deleteById(enrollmentId);
    }
}
