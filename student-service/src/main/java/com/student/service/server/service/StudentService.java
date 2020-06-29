package com.student.service.server.service;

import com.student.service.server.dto.EmailChangeDTO;
import com.student.service.server.dto.EnrollStudentDTO;
import com.student.service.server.dto.StudentDTO;

public interface StudentService {

    StudentDTO getStudent(Integer studentId) throws Exception;

    StudentDTO createStudent(EnrollStudentDTO student) throws Exception;

    StudentDTO updateStudentEmail(Integer studentId, EmailChangeDTO studentEmail) throws Exception;
}
