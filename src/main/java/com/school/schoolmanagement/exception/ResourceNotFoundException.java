package com.school.schoolmanagement.exception;

/**
 * @author Liaqat Ali Sanjrani
 *
 *         24-April-2025
 *
 */

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
