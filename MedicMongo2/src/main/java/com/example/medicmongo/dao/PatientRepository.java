package com.example.medicmongo.dao;

import com.example.medicmongo.dto.PatientEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PatientRepository extends MongoRepository<PatientEntity, String> {
    Optional<PatientEntity> findFirstByEmail(String email);
    Page<PatientEntity> findAllByNameIsLikeAndSurnameIsLike(String name, String surname, Pageable page);
    Page<PatientEntity> findAllByPeselIsLike(String pesel, Pageable page);
}
