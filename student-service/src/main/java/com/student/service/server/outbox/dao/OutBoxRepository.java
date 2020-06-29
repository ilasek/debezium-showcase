package com.student.service.server.outbox.dao;

import com.student.service.server.outbox.models.OutBoxEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OutBoxRepository extends JpaRepository<OutBoxEntity, Integer> {

}
