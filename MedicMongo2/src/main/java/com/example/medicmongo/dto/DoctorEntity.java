package com.example.medicmongo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Doctor")
@Getter
@Setter
public class DoctorEntity {
    @Id
    private String id;
    private String name;
    private String surname;
    private Date birthdate;
    private String sex;
    private String speciality;
    private String college;
    private String licenseNumber;
    private String email;
    private String password;
}
