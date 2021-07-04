package com.example.medicmongo.controller;

import com.example.medicmongo.dto.DoctorEntity;
import com.example.medicmongo.dto.PatientEntity;
import com.example.medicmongo.dto.ResultEntity;
import com.example.medicmongo.service.DoctorService;
import com.example.medicmongo.service.PatientService;
import com.example.medicmongo.service.ResultService;
import java.security.Principal;
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
@RequestMapping("/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;
    private final PatientService patientService;
    private final DoctorService doctorService;

    @GetMapping("/{id}")
    public ResultEntity getById(@PathVariable("id") String id) {
        return resultService.getById(id);
    }

    @GetMapping("/all")
    public Page<ResultEntity> getAll(@RequestParam("page") int page) {
        return resultService.getAll(page);
    }

    @GetMapping("/all/patient")
    public Page<ResultEntity> getPatientResults(@RequestParam("page") int page,
                                                Principal principal) {
        PatientEntity patient = patientService.getByEmail(principal.getName());
        if (patient == null) {
            throw new RequestRejectedException("No such patient");
        }

        return resultService.getAllByPatient(page, patient.getId());
    }

    @GetMapping("/all/patient/name")
    public Page<ResultEntity> getPatientResultsWithDisease(@RequestParam("page") int page,
                                                @RequestParam("d") String d,
                                                Principal principal) {
        PatientEntity patient = patientService.getByEmail(principal.getName());
        if (patient == null) {
            throw new RequestRejectedException("No such patient");
        }

        return resultService.getAllByPatientAndDisease(page, d, patient.getId());
    }

    @GetMapping("/all/doctor")
    public Page<ResultEntity> getDoctorResults(@RequestParam("page") int page,
                                               Principal principal) {
        DoctorEntity doctor = doctorService.getByEmail(principal.getName());
        if (doctor == null) {
            throw new RequestRejectedException("No such doctor");
        }

        return resultService.getAllByDoctor(page, doctor.getId());
    }

    @PostMapping("/add")
    public void create(@RequestBody ResultEntity resultEntity) {
        resultService.add(resultEntity);
    }

    @PutMapping("/update")
    public ResultEntity update(@RequestBody ResultEntity resultEntity) {
        return resultService.update(resultEntity);
    }

    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable("id") String id) {
        resultService.delete(id);
    }
}
