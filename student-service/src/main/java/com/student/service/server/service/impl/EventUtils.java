package com.student.service.server.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.student.service.server.dao.entities.StudentEntity;
import com.student.service.server.outbox.models.OutboxEvent;

public final class EventUtils {

    private EventUtils() {
        throw new AssertionError();
    }

    public static OutboxEvent createEnrollEvent(StudentEntity studentEntity) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.convertValue(studentEntity, JsonNode.class);

        return new OutboxEvent(
                studentEntity.getStudentId(),
                "STUDENT_ENROLLED",
                jsonNode
        );
    }

    public static OutboxEvent createUpdateEmailEvent(StudentEntity studentEntity) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonNode = mapper.createObjectNode()
                .put("studentId", studentEntity.getStudentId())
                .put("email",studentEntity.getEmail());

        return new OutboxEvent(
                studentEntity.getStudentId(),
                "STUDENT_EMAIL_CHANGED",
                jsonNode
        );
    }
}
