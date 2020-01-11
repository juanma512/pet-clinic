package com.jmoe.petclinic.services.jpa;

import com.jmoe.petclinic.model.PetType;
import com.jmoe.petclinic.repositories.PetTypeRepository;
import com.jmoe.petclinic.services.PetTypeService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
@Primary
public class PetTypeServiceJPA implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    public PetTypeServiceJPA(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> types = new HashSet<>();
        petTypeRepository.findAll().forEach(types::add);
        return types;
    }

    @Override
    public PetType findById(Long aLong) {
        return petTypeRepository.findById(aLong).orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petTypeRepository.findById(aLong);
    }
}
