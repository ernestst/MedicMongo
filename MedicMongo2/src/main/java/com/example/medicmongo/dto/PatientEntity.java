package com.example.medicmongo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Patient")
@Getter
@Setter
public class PatientEntity {
    @Id
    private String id;
    private String name;
    private String surname;
    private String pesel;
    private List<String> allergies;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthdate;
    private String sex;
    private String password;
    private String email;
    private List<String> chronicDiseases;
}
