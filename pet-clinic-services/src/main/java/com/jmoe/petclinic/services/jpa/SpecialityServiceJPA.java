package com.jmoe.petclinic.services.jpa;

import com.jmoe.petclinic.model.Speciality;
import com.jmoe.petclinic.repositories.SpecialtyRepository;
import com.jmoe.petclinic.services.SpecialityService;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("springdatajpa")
@Primary
public class SpecialityServiceJPA implements SpecialityService {

    private final SpecialtyRepository specialtyRepository;

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> specialties = new HashSet<>();
        specialtyRepository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public Speciality findById(Long aLong) {
        return specialtyRepository.findById(aLong).orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {
        return specialtyRepository.save(object);
    }

    @Override
    public void delete(Speciality object) {
        specialtyRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specialtyRepository.deleteById(aLong);
    }
}
