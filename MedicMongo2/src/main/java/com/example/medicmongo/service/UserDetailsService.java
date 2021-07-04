package com.example.medicmongo.service;

import com.example.medicmongo.dao.DoctorRepository;
import com.example.medicmongo.dao.PatientRepository;
import com.example.medicmongo.dto.DoctorEntity;
import com.example.medicmongo.dto.PatientEntity;
import java.util.Collections;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<PatientEntity> patient = patientRepository.findFirstByEmail(email);
        Optional<DoctorEntity> doctor = doctorRepository.findFirstByEmail(email);

        if (!patient.isPresent() && !doctor.isPresent()) {
            throw new UsernameNotFoundException(email);
        }

        return patient.map(patientEntity ->
                new User(patientEntity.getEmail(),
                        patientEntity.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("PATIENT"))))
                .orElseGet(() -> new User(doctor.get().getEmail(),
                        doctor.get().getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("DOCTOR")))
                );
    }
}
