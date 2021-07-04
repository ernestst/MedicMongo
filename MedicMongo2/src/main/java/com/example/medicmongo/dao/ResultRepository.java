package com.example.medicmongo.dao;

import com.example.medicmongo.dto.ResultEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ResultRepository extends MongoRepository<ResultEntity, String> {
    Page<ResultEntity> findAllByPatientId(String patientId, Pageable page);
    Page<ResultEntity> findAllByPatientIdAndDiseaseLikeIgnoreCase(String patientId, String disease, Pageable page);
    Page<ResultEntity> findAllByDoctorId(String doctorId, Pageable page);
}
