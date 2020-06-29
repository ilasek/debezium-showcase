package com.student.service.server.controller;

import com.student.service.server.dto.EmailChangeDTO;
import com.student.service.server.dto.EnrollStudentDTO;
import com.student.service.server.dto.StudentDTO;
import com.student.service.server.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(value = "/{studentId}")
    public StudentDTO getStudent(@PathVariable Integer studentId) throws Exception {
        return studentService.getStudent(studentId);
    }

    @PostMapping
    public StudentDTO addStudent(@RequestBody EnrollStudentDTO student) throws Exception {
        return studentService.createStudent(student);
    }

    @PatchMapping(value = "/{studentId}/email")
    public StudentDTO updateStudentEmail(@PathVariable Integer studentId,
                                         @RequestBody EmailChangeDTO studentEmail) throws Exception {
        return studentService.updateStudentEmail(studentId, studentEmail);
    }
}
