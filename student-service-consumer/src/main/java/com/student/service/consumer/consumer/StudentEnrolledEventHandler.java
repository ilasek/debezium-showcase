package com.student.service.consumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class StudentEnrolledEventHandler {

    private final Logger logger = LoggerFactory.getLogger(StudentEnrolledEventHandler.class);

    @KafkaListener(topics = "student_enrolled", groupId = "student_enrolled")
    public void handleStudentEnrolled(String message) {
        logger.info("Received {}", message);
    }

}
