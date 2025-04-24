package com.school.schoolmanagement.controller;

import com.school.schoolmanagement.constant.AppConstantUtil;
import com.school.schoolmanagement.dto.EnrollmentDTO;
import com.school.schoolmanagement.service.EnrollmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Liaqat Ali Sanjrani
 *
 *         24-April-2025
 *
 */

@RestController
@RequestMapping(AppConstantUtil.ENROLLMENT_BASE)
public class EnrollmentController {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentController.class);


    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // Endpoint to enroll a student in a course
    @PostMapping(value = AppConstantUtil.URL.ENROLLMENT_CREATE)
    public ResponseEntity<EnrollmentDTO> enrollStudent(@Valid @RequestBody EnrollmentDTO enrollmentDTO) {
        EnrollmentDTO enrollment = enrollmentService.enrollStudent(enrollmentDTO);
        return ResponseEntity.ok(enrollment);
    }

    // Endpoint to get all enrollments of a student
    @GetMapping(value = AppConstantUtil.URL.ENROLLMENT_GET_BY_ID)
    public ResponseEntity<List<EnrollmentDTO>> getStudentEnrollments(@PathVariable("studentId") Long studentId) {
        List<EnrollmentDTO> enrollments = enrollmentService.getStudentEnrollments(studentId);
        return ResponseEntity.ok(enrollments);
    }

    // Endpoint to get the grade of a student in a specific course
    @GetMapping(value = AppConstantUtil.URL.ENROLLMENT_GET_GRADE)
    public ResponseEntity<EnrollmentDTO> getStudentCourseGrade(
            @PathVariable Long studentId, @PathVariable Long courseId) {
        EnrollmentDTO enrollment = enrollmentService.getStudentCourseGrade(studentId, courseId);
        return ResponseEntity.ok(enrollment);
    }

    // Endpoint to update the grade of a student for a specific course
    @PutMapping(value = AppConstantUtil.URL.ENROLLMENT_UPDATE)
    public ResponseEntity<Map<String, String>> updateGrade(
            @PathVariable Long enrollmentId, @RequestBody Map<String, Double> requestBody) {
        Double grade = requestBody.get("grade");
        EnrollmentDTO enrollment = enrollmentService.updateGrade(enrollmentId, grade);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Update Grade successfully of a student for a specific course");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Endpoint to unenroll a student from a course
    @DeleteMapping(value = AppConstantUtil.URL.ENROLLMENT_DELETE)
    public ResponseEntity<Map<String, String>> unenrollStudent(@PathVariable Long enrollmentId) {
        enrollmentService.unenrollStudent(enrollmentId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "DELETED  successfully of a student from a course");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
