package com.student.service.server.outbox;

import com.student.service.server.outbox.dao.OutBoxRepository;
import com.student.service.server.outbox.models.OutBoxEntity;
import com.student.service.server.outbox.models.OutboxEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class EventService {

    private final OutBoxRepository outBoxRepository;

    public EventService(OutBoxRepository outBoxRepository) {
        this.outBoxRepository = outBoxRepository;
    }

    @EventListener
    public void handleOutboxEvent(OutboxEvent event) {

        UUID uuid = UUID.randomUUID();
        OutBoxEntity entity = new OutBoxEntity(
                uuid,
                event.getAggregateId(),
                event.getEventType(),
                event.getPayload().toString(),
                new Date()
        );

        log.info("Handling event : {}.", entity);

        outBoxRepository.save(entity);

        outBoxRepository.delete(entity);
    }
}
