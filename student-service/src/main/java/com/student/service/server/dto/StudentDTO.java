package com.student.service.server.dto;

import lombok.Data;

@Data
public class StudentDTO {

    private Integer studentId;

    private String name;

    private String email;

    private String address;
}
