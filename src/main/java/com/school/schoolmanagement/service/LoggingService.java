package com.school.schoolmanagement.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author Liaqat Ali Sanjrani
 *
 *         24-April-2025
 *
 */

@Service
public class LoggingService {

    public void info(Logger logger, String message, int lineNumber) {
        logger.info("[INFO] {} | Line: {}", message, lineNumber);
    }

    public void warn(Logger logger, String message, int lineNumber) {
        logger.warn("[WARN] {} | Line: {}", message, lineNumber);
    }

    public void error(Logger logger, String message, int lineNumber) {
        logger.error("[ERROR] {} | Line: {}", message, lineNumber);
    }
}

