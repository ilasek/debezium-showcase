package com.student.service.server.outbox.models;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OutboxEvent {

    private Integer aggregateId;

    private String eventType;

    private JsonNode payload;
}
