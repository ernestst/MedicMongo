package com.example.medicmongo.dao;

import com.example.medicmongo.dto.MedicineEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicineRepository extends MongoRepository<MedicineEntity, String> {
    Page<MedicineEntity> findAllByNameIsLike(String name, Pageable page);
    Page<MedicineEntity> findAllByIdIn(List<String> id, Pageable page);
}
