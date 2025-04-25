package com.school.schoolmanagement.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
/**
 * @author Liaqat Ali Sanjrani
 *
 *         24-April-2025
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "enrollments")
public class Enrollment extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @DecimalMin(value = "0.0", message = "Grade must be at least 0.0")
    @DecimalMax(value = "100.0", message = "Grade must be at most 100.0")
    private Double grade;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }
}
