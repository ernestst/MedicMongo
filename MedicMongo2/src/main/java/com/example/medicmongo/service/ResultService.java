package com.example.medicmongo.service;

import com.example.medicmongo.dao.ResultRepository;
import com.example.medicmongo.dto.ResultEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResultService {

    private final ResultRepository resultRepository;

    public ResultEntity getById(String id) {
        return resultRepository.findById(id).orElse(null);
    }

    public Page<ResultEntity> getAll(int page) {
        return resultRepository.findAll(PageRequest.of(page, 10));
    }

    public void add(ResultEntity patientEntity) {
        resultRepository.save(patientEntity);
    }

    public Page<ResultEntity> getAllByPatient(int page, String id) {
        return resultRepository.findAllByPatientId(id, PageRequest.of(page, 10));
    }

    public Page<ResultEntity> getAllByPatientAndDisease(int page, String disease, String id) {
        return resultRepository.findAllByPatientIdAndDiseaseLikeIgnoreCase(id, disease, PageRequest.of(page, 10));
    }

    public Page<ResultEntity> getAllByDoctor(int page, String id) {
        return resultRepository.findAllByDoctorId(id, PageRequest.of(page, 10));
    }

    public ResultEntity update(ResultEntity resultEntity) {
        return resultRepository.save(resultEntity);
    }

    public void delete(String id) {
        resultRepository.deleteById(id);
    }
}
