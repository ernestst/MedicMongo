package com.example.medicmongo.service;

import com.example.medicmongo.dao.DoctorRepository;
import com.example.medicmongo.dto.DoctorEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorEntity getById(String id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public Page<DoctorEntity> getAll(int page) {
        return doctorRepository.findAll(PageRequest.of(page, 10));
    }

    public Page<DoctorEntity> findByNameSurname(int page, String name, String surname) {
        return doctorRepository.findAllByNameIsLikeAndSurnameIsLike(name, surname, PageRequest.of(page, 10));
    }

    public void add(DoctorEntity doctorEntity) {
        doctorRepository.save(doctorEntity);
    }

    public DoctorEntity update(DoctorEntity updated) {
        return doctorRepository.save(updated);
    }

    public void delete(String id) {
        doctorRepository.deleteById(id);
    }

    public DoctorEntity getByEmail(String email) {
        return doctorRepository.findFirstByEmail(email).orElse(null);
    }
}
