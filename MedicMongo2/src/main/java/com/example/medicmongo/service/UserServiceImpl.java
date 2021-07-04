package com.example.medicmongo.service;

import com.example.medicmongo.dao.DoctorRepository;
import com.example.medicmongo.dao.PatientRepository;
import com.example.medicmongo.dto.DoctorEntity;
import com.example.medicmongo.dto.PatientEntity;
import java.security.Principal;
import java.util.Optional;

import com.example.medicmongo.dto.UserJson;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public UserJson login(Principal principal) {
        Optional<PatientEntity> patient = patientRepository.findFirstByEmail(principal.getName());
        Optional<DoctorEntity> doctor = doctorRepository.findFirstByEmail(principal.getName());

        if (!patient.isPresent() && !doctor.isPresent()) {
            throw new AuthenticationCredentialsNotFoundException(principal.getName());
        }

        UserJson userJson = UserJson.builder()
                .role(patient.map(entity -> "PATIENT").orElse("DOCTOR"))
                .id(patient.isPresent() ? patient.get().getId() : doctor.get().getId())
                .build();

        return userJson;
    }
}
