package com.jmoe.petclinic.services;

import com.jmoe.petclinic.model.Pet;
import java.util.Set;

public interface PetService {

  Pet findById(Long id);

  Pet save(Pet pet);

  Set<Pet> findAll();

}
