package com.student.service.server.service.impl;

import com.student.service.server.dao.StudentRepository;
import com.student.service.server.dao.entities.StudentEntity;
import com.student.service.server.dto.EmailChangeDTO;
import com.student.service.server.dto.EnrollStudentDTO;
import com.student.service.server.dto.StudentDTO;
import com.student.service.server.dto.StudentMapper;
import com.student.service.server.outbox.EventPublisher;
import com.student.service.server.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final EventPublisher eventPublisher;

    public StudentServiceImpl(StudentRepository studentRepository, EventPublisher eventPublisher) {
        this.studentRepository = studentRepository;
        this.eventPublisher=eventPublisher;
    }

    @Override
    public StudentDTO getStudent(Integer studentId) throws Exception {
        log.info("Fetching Student details for StudentId: {}", studentId);

        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
        StudentDTO studentDTO = null;

        if (studentEntity.isPresent()) {
            studentDTO = StudentMapper.INSTANCE.studentEntityToDTO(studentEntity.get());
        } else {
            throw new Exception("Student not found");
        }

        return studentDTO;
    }

    @Override
    @Transactional
    public StudentDTO createStudent(EnrollStudentDTO student) throws Exception {
        log.info("Enroll Student details for StudentId: {}", student.getName());

        StudentEntity studentEntity = StudentMapper.INSTANCE.studentDTOToEntity(student);
        studentRepository.save(studentEntity);

        //Publish the event
        eventPublisher.fire(EventUtils.createEnrollEvent(studentEntity));

        return StudentMapper.INSTANCE.studentEntityToDTO(studentEntity);
    }

    @Override
    @Transactional
    public StudentDTO updateStudentEmail(Integer studentId, EmailChangeDTO studentEmail) throws Exception {
        log.info("Update Email to '{}' for StudentId: {}", studentEmail.getEmail(),  studentId);

        StudentEntity studentEntity = studentRepository.getOne(studentId);
        studentEntity.setEmail(studentEmail.getEmail());
        studentRepository.save(studentEntity);

        //Publish the event
        eventPublisher.fire(EventUtils.createUpdateEmailEvent(studentEntity));

        return StudentMapper.INSTANCE.studentEntityToDTO(studentEntity);
    }
}
