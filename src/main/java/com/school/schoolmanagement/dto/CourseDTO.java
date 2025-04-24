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
public class CourseDTO {
    private Long id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    @Size(min = 3, max = 10)
    private String code;

    @Size(max = 500)
    private String description;
}
