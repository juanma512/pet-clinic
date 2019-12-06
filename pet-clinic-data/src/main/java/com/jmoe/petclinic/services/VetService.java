package com.jmoe.petclinic.services;

import com.jmoe.petclinic.model.Vet;
import java.util.Set;

public interface VetService {

  Vet findById(Long id);

  Vet save(Vet vet);

  Set<Vet> findAll();

}
