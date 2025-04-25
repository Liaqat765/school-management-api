package com.school.schoolmanagement.entity;



import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.*;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

/**
 * @author Liaqat Ali Sanjrani
 *
 *         25-April-2025
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@SQLRestriction("isActive = TRUE")
public class AbstractEntity {
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String  createdBy;
    private String  updatedBy;

    @PrePersist
    public void prePersist() {
        isActive = true;
        createdAt = LocalDateTime.now();


    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
