package com.example.medicmongo.service;

import com.example.medicmongo.dao.PatientRepository;
import com.example.medicmongo.dto.PatientEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientEntity getById(String id){
        return patientRepository.findById(id).orElse(null);
    }

    public Page<PatientEntity> getAll(int page) {
        return patientRepository.findAll(PageRequest.of(page, 10));
    }

    public List<PatientEntity> getAll() {
        return patientRepository.findAll();
    }

    public Page<PatientEntity> findByNameSurname(int page, String name, String surname) {
        return patientRepository.findAllByNameIsLikeAndSurnameIsLike(name, surname, PageRequest.of(page, 10));
    }

    public Page<PatientEntity> findByPESEL(int page, String pesel) {
        return patientRepository.findAllByPeselIsLike(pesel, PageRequest.of(page, 10));
    }

    public void add(PatientEntity patientEntity) {
        patientRepository.save(patientEntity);
    }

    public PatientEntity update(PatientEntity updated) {
        return patientRepository.save(updated);
    }

    public void delete(String id) {
        patientRepository.deleteById(id);
    }

    public PatientEntity getByEmail(String email) {
        return patientRepository.findFirstByEmail(email).orElse(null);
    }
}
