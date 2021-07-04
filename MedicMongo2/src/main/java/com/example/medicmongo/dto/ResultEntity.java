package com.example.medicmongo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "Result")
@Getter
@Setter
public class ResultEntity {
    @Id
    private String id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date examinationDate;
    private String doctorId;
    private String patientId;
    private String disease;
    private boolean stayHome;
    private int treatmentLength;
    private List<String> medicines;
}
