package com.example.medicmongo.dao;

import com.example.medicmongo.dto.DoctorEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DoctorRepository extends MongoRepository<DoctorEntity, String> {
    Optional<DoctorEntity> findFirstByEmail(String email);
    Page<DoctorEntity> findAllByNameIsLikeAndSurnameIsLike(String name, String surname, Pageable page);
}
