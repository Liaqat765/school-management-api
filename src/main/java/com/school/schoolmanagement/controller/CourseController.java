package com.school.schoolmanagement.controller;

import com.school.schoolmanagement.constant.AppConstantUtil;
import com.school.schoolmanagement.dto.CourseDTO;
import com.school.schoolmanagement.service.CourseService;
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
@RequestMapping(AppConstantUtil.COURSE_BASE)
public class CourseController {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);


    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Endpoint to create a new course
    @PostMapping(value = AppConstantUtil.URL.COURSE_CREATE)
    public ResponseEntity<CourseDTO> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO createdCourse = courseService.createCourse(courseDTO);
        return ResponseEntity.ok(createdCourse);
    }

    // Endpoint to get the details of a specific course by its ID

    @GetMapping(value = AppConstantUtil.URL.COURSE_GET_BY_ID)
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Long id) {
        CourseDTO course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    // Endpoint to get a list of all courses with pagination support
    // Pageable parameters like page size and page number can be passed to the API
    @GetMapping(value = AppConstantUtil.URL.COURSE_GET_ALL)
    public ResponseEntity<Page<CourseDTO>> getAllCourses(Pageable pageable) {
        Page<CourseDTO> courses = courseService.getAllCourses(pageable);
        return ResponseEntity.ok(courses);
    }

    // Endpoint to update the details of an existing course
    // The updated course information is received as a CourseDTO in the request body
    @PutMapping(value = AppConstantUtil.URL.COURSE_UPDATE)
    public ResponseEntity<Map<String, String>> updateCourse(
            @PathVariable Long id, @Valid @RequestBody CourseDTO courseDTO) {
        CourseDTO updatedCourse = courseService.updateCourse(id, courseDTO);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Course Updated successfully");

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    // Endpoint to delete a course by its ID
    @DeleteMapping(value = AppConstantUtil.URL.COURSE_DELETE)
    public ResponseEntity<Map<String, String>> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Course Deleted successfully");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
