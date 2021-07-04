package com.example.medicmongo.controller;

import com.example.medicmongo.dto.DoctorEntity;
import com.example.medicmongo.service.DoctorService;
import java.security.Principal;
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
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/{id}")
    public DoctorEntity getById(@PathVariable("id") String id) {
        return doctorService.getById(id);
    }

    @GetMapping("/me")
    public String getLoggedId(Principal principal) {
        return doctorService.getByEmail(principal.getName()).getId();
    }

    @GetMapping("/all")
    public Page<DoctorEntity> getAll(@RequestParam("page") int page) {
        return doctorService.getAll(page);
    }

    @GetMapping("/find")
    public Page<DoctorEntity> findByNameSurname(@RequestParam("page") int page,
                                                @RequestParam("s") String surname,
                                                @RequestParam("n") String name) {
        return doctorService.findByNameSurname(page, name, surname);
    }

    @PostMapping("/add")
    public void create(@RequestBody DoctorEntity doctorEntity) {
        doctorService.add(doctorEntity);
    }

    @PutMapping("/update")
    public DoctorEntity update(@RequestBody DoctorEntity doctorEntity) {
        return doctorService.update(doctorEntity);
    }

    @DeleteMapping("/remove/{id}")
    public void remove(@PathVariable("id") String id) {
        doctorService.delete(id);
    }
}
