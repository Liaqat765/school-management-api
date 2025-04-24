package com.school.schoolmanagement.constant;

/**
 * @author Liaqat Ali Sanjrani
 *
 *         24-April-2025
 *
 */

public class AppConstantUtil {
    public static final String STUDENT_BASE = "/api/v1/students";
    public static final String COURSE_BASE = "/api/v1/courses";
    public static final String ENROLLMENT_BASE = "/api/v1/enrollments";



    public interface URL {

        String STUDENT_GET_BY_ID= "/{id}";
        String STUDENT_CREATE = "/create";
        String STUDENT_GET_ALL = "/all";
        String STUDENT_UPDATE = "/update/{id}";
        String STUDENT_DELETE = "/delete/{id}";

        String COURSE_GET_BY_ID = "/{id}";
        String COURSE_CREATE = "/create";
        String COURSE_GET_ALL = "/all";
        String COURSE_UPDATE = "/update/{id}";
        String COURSE_DELETE = "/delete/{id}";

        String ENROLLMENT_GET_BY_ID = "/student/{studentId}";
        String ENROLLMENT_CREATE = "/create";
        String ENROLLMENT_GET_GRADE = "/{studentId}/course/{courseId}";
        String ENROLLMENT_UPDATE = "/update/{enrollmentId}";
        String ENROLLMENT_DELETE = "/delete/{enrollmentId}";

    }
}
