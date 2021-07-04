package com.example.medicmongo.service;

import com.example.medicmongo.dao.MedicineRepository;
import com.example.medicmongo.dto.MedicineEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineEntity getById(String id){
        return medicineRepository.findById(id).orElse(null);
    }

    public Page<MedicineEntity> getAll(int page) {
        return medicineRepository.findAll(PageRequest.of(page, 10));
    }

    public List<MedicineEntity> getAll() {
        return medicineRepository.findAll();
    }

    public void add(MedicineEntity medicineEntity) {
        medicineRepository.save(medicineEntity);
    }

    public Page<MedicineEntity> findByName(int page, String name) {
        return medicineRepository.findAllByNameIsLike(name, PageRequest.of(page, 10));
    }

    public Page<MedicineEntity> findByIds(int page, List<String> medicines) {
        return medicineRepository.findAllByIdIn(medicines, PageRequest.of(page, 10));
    }

    public MedicineEntity update(MedicineEntity updated) {
        return medicineRepository.save(updated);
    }

    public void delete(String id) {
        medicineRepository.deleteById(id);
    }
}
