package com.example.medicmongo.controller;

import com.example.medicmongo.dto.PatientEntity;
import com.example.medicmongo.dto.UserJson;
import com.example.medicmongo.service.PatientService;
import com.example.medicmongo.service.UserService;
import java.security.Principal;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final UserService userService;

    @GetMapping("/{id}")
    public PatientEntity getById(@PathVariable("id") String id) {
        return patientService.getById(id);
    }

    @GetMapping("/all")
    public Page<PatientEntity> getAll(@RequestParam("page") int page) {
        return patientService.getAll(page);
    }

    @GetMapping("/all/list")
    public List<PatientEntity> getAllList() {
        return patientService.getAll();
    }

    @GetMapping("/find")
    public Page<PatientEntity> findByPESEL(@RequestParam("page") int page,
                                                 @RequestParam("p") String pesel) {
        return patientService.findByPESEL(page, pesel);
    }

    @PostMapping("/add")
    public void create(@RequestBody PatientEntity patientEntity) {
        patientService.add(patientEntity);
    }

    @PutMapping("/update")
    public PatientEntity update(@RequestBody PatientEntity patientEntity) {
        return patientService.update(patientEntity);
    }

    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable("id") String id) {
        patientService.delete(id);
    }

    @GetMapping("/login")
    public UserJson login(Principal principal) {
        return userService.login(principal);
    }
}
