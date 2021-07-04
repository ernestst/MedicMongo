package com.example.medicmongo.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Medicine")
@Getter
@Setter
public class MedicineEntity {
    @Id
    private String id;
    private String name;
    private List<String> ingredients;
    private List<String> goodForDiseases;
    private float avgPrice;
}
