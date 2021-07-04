package com.example.medicmongo.controller;

import com.example.medicmongo.dto.MedicineEntity;
import com.example.medicmongo.dto.PatientEntity;
import com.example.medicmongo.dto.ResultEntity;
import com.example.medicmongo.service.MedicineService;
import com.example.medicmongo.service.PatientService;
import com.example.medicmongo.service.ResultService;
import java.security.Principal;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;
    private final ResultService resultService;
    private final PatientService patientService;

    @GetMapping
    public MedicineEntity getById(@RequestParam String id) {
        return medicineService.getById(id);
    }

    @GetMapping("/all")
    public Page<MedicineEntity> getAll(@RequestParam("page") int page) {
        return medicineService.getAll(page);
    }

    @GetMapping("/all/list")
    public List<MedicineEntity> getAllList() {
        return medicineService.getAll();
    }

    @GetMapping("/find")
    public Page<MedicineEntity> findByNameSurname(@RequestParam("page") int page,
                                                @RequestParam("n") String name) {
        return medicineService.findByName(page, name);
    }

    @PostMapping("/add")
    public void create(@RequestBody MedicineEntity medicineEntity) {
        medicineService.add(medicineEntity);
    }

    @GetMapping("/{result}")
    public Page<MedicineEntity> getResultMedicines(@RequestParam("page") int page,
                                                   @PathVariable("result") String resultId,
                                                   Principal principal) {
        ResultEntity result = resultService.getById(resultId);
        PatientEntity patient = patientService.getByEmail(principal.getName());

        if (result == null) {
            throw new RequestRejectedException("No result found");
        }

        if (patient == null) {
            throw new RequestRejectedException("No such patient");
        }

        if (!patient.getId().equals(result.getPatientId())) {
            throw new RequestRejectedException("Patient has no access to this result");
        }

        return medicineService.findByIds(page, result.getMedicines());
    }

    @PutMapping("/update")
    public MedicineEntity update(@RequestBody MedicineEntity medicineEntity) {
        return medicineService.update(medicineEntity);
    }

    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable("id") String id) {
        medicineService.delete(id);
    }
}
