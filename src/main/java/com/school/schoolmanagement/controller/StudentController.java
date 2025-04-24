package com.school.schoolmanagement.controller;

import com.school.schoolmanagement.constant.AppConstantUtil;
import com.school.schoolmanagement.dto.StudentDTO;
import com.school.schoolmanagement.service.LoggingService;
import com.school.schoolmanagement.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Liaqat Ali Sanjrani
 *
 *         24-April-2025
 *
 */

@RestController
@RequestMapping(AppConstantUtil.STUDENT_BASE)
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;
    private final LoggingService loggingService;

    public StudentController(StudentService studentService,
                             LoggingService loggingService) {
        this.studentService = studentService;
        this.loggingService=loggingService;
    }

    // Endpoint to create a new student
    @PostMapping(value = AppConstantUtil.URL.STUDENT_CREATE)
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO) {

        // Create logger text with input information
        StringBuilder loggerText = new StringBuilder("StudentController=>createStudent::input::StudentDTO:" + studentDTO);
        logger.info(loggerText.toString());
        loggingService.info(logger, loggerText + ",beforeCreation", Thread.currentThread().getStackTrace()[1].getLineNumber());

        // Call the service to create the student
        StudentDTO createdStudent = studentService.createStudent(studentDTO);

        // Log the response
        loggerText = new StringBuilder("StudentController=>createStudent::output::CreatedStudentDTO:" + createdStudent);
        loggingService.info(logger, loggerText + ",afterCreation", Thread.currentThread().getStackTrace()[1].getLineNumber());

        return ResponseEntity.ok(createdStudent);
    }

    // Endpoint to get a student by ID
    @GetMapping(value = AppConstantUtil.URL.STUDENT_GET_BY_ID)
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id) {


        // Call the service to get the student details
        StudentDTO student = studentService.getStudentById(id);



        return ResponseEntity.ok(student);
    }

    // Endpoint to get all students with pagination
    @GetMapping(value = AppConstantUtil.URL.STUDENT_GET_ALL)
    public ResponseEntity<Page<StudentDTO>> getAllStudents(Pageable pageable) {
        Page<StudentDTO> students = studentService.getAllStudents(pageable);
        return ResponseEntity.ok(students);
    }

    // Endpoint to update a student's details by ID
    @PutMapping(value = AppConstantUtil.URL.STUDENT_UPDATE)
    public ResponseEntity<Map<String, String>> updateStudent(
            @PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO) {



        // Call the service to update the student
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);


        Map<String, String> response = new HashMap<>();
        response.put("message", "Student Updated successfully");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Endpoint to delete a student by ID
    @DeleteMapping(value = AppConstantUtil.URL.STUDENT_DELETE)
    public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable Long id) {

        // Call the service to delete the student
        studentService.deleteStudent(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Student deleted successfully By Id");

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
