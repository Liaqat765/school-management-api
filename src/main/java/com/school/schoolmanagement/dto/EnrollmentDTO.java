package com.school.schoolmanagement.dto;

import jakarta.validation.constraints.*;
import lombok.*;

/**
 * @author Liaqat Ali Sanjrani
 *
 *         24-April-2025
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDTO {
    private Long id;

    @NotNull
    private Long studentId;

    @NotNull
    private Long courseId;

    @DecimalMin("0.0")
    @DecimalMax("100.0")
    private Double grade;
}
