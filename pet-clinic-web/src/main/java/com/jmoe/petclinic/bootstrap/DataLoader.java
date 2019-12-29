package com.jmoe.petclinic.bootstrap;

import com.jmoe.petclinic.model.Owner;
import com.jmoe.petclinic.model.PetType;
import com.jmoe.petclinic.model.Vet;
import com.jmoe.petclinic.services.OwnerService;
import com.jmoe.petclinic.services.PetTypeService;
import com.jmoe.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService,
        PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    private void createOwner(String firstName, String lastName) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);

        ownerService.save(owner);
    }

    private void createVet(String firstName, String lastName) {
        Vet vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);

        vetService.save(vet);
    }

    private PetType createPetType(String name) {
        PetType petType = new PetType();
        petType.setName(name);

        return petTypeService.save(petType);
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = createPetType("Dog");
        PetType cat = createPetType("Cat");

        createOwner("Juan Manuel", "Oviedo");
        createOwner("Mireia", "Romero Moreno");
        System.out.println("Loaded owners ...");

        createVet("Ramiro", "Moreno Romero");
        createVet("Alicia", "Garcia Ramirez");
        System.out.println("Loaded vets ...");
    }
}
