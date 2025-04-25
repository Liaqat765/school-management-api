package com.school.schoolmanagement.service;

import com.school.schoolmanagement.dto.StudentDTO;
import com.school.schoolmanagement.entity.Student;
import com.school.schoolmanagement.service.LoggingService;
import com.school.schoolmanagement.exception.ResourceNotFoundException;
import com.school.schoolmanagement.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author Liaqat Ali Sanjrani
 *
 *         24-April-2025
 *
 */

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);



    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private final LoggingService loggingService;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper, LoggingService loggingService) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
        this.loggingService = loggingService;
    }

    //// Method to create a new student from the provided DTO and save to the database
    public StudentDTO createStudent(StudentDTO studentDTO) {

        // Log the input student details
        logger.info("StudentService=>createStudent::input::StudentDTO:{}", studentDTO);

        // Convert the StudentDTO to a Student entity
        Student student = modelMapper.map(studentDTO, Student.class);

        // Save the student entity to the database
        Student savedStudent = studentRepository.save(student);

        // Log the saved student details
        logger.info("StudentService=>createStudent::output::SavedStudent:{}", savedStudent);

        // Convert the saved student entity back to DTO and return it
        return modelMapper.map(savedStudent, StudentDTO.class);
    }

    // Method to get a student by their ID, throwing an exception if not found
    public StudentDTO getStudentById(Long id) {
        logger.info("StudentService=>getStudentById::Searching for student with ID: {}", id);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("StudentService=>getStudentById::Student not found with ID: {}", id);
                   return new ResourceNotFoundException("Student not found with id: " + id);

                });
        return modelMapper.map(student, StudentDTO.class);
    }

    // Method to get all students with pagination
    public Page<StudentDTO> getAllStudents(Pageable pageable) {
        logger.info("StudentService=>getAllStudents::Fetching all students with pagination");
//        return studentRepository.findAll(pageable)
//                .map(student -> modelMapper.map(student, StudentDTO.class));

        Page<StudentDTO> result = studentRepository.findAll(pageable)
                .map(student -> modelMapper.map(student, StudentDTO.class));

        logger.info("StudentService=>getAllStudents::Total students fetched: {}", result.getTotalElements());

        return result;
    }

    // Method to update an existing students details
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        logger.info("StudentService=>updateStudent::Attempting to update student with ID: {}", id);

        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("StudentService=>updateStudent::Student not found with ID: {}", id);
                    return  new ResourceNotFoundException("Student not found with id: " + id);
                });
        logger.info("StudentService=>updateStudent::Before Update: {}", existingStudent);

        existingStudent.setFirstName(studentDTO.getFirstName());
        existingStudent.setLastName(studentDTO.getLastName());
        existingStudent.setEmail(studentDTO.getEmail());
        existingStudent.setPhone(studentDTO.getPhone());

        Student updatedStudent = studentRepository.save(existingStudent);
        logger.info("StudentService=>updateStudent::After Update: {}", updatedStudent);

        return modelMapper.map(updatedStudent, StudentDTO.class);
    }

    // Method to delete a student by ID
    public void deleteStudent(Long id) {

        logger.info("StudentService=>deleteStudent::Attempting to delete student with ID: {}", id);

        if (!studentRepository.existsById(id)) {
            logger.error("StudentService=>deleteStudent::Student not found with ID: {}", id);
            throw new ResourceNotFoundException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
        logger.info("StudentService=>deleteStudent::Deleted student with ID: {}", id);
    }
}
