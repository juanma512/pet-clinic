package com.jmoe.petclinic.bootstrap;

import com.jmoe.petclinic.model.Owner;
import com.jmoe.petclinic.model.Pet;
import com.jmoe.petclinic.model.PetType;
import com.jmoe.petclinic.model.Vet;
import com.jmoe.petclinic.services.OwnerService;
import com.jmoe.petclinic.services.PetService;
import com.jmoe.petclinic.services.PetTypeService;
import com.jmoe.petclinic.services.VetService;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService,
        PetTypeService petTypeService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    private void createOwner(String firstName, String lastName, String address, String city,
        String telephone, Set<Pet> pets) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setAddress(address);
        owner.setCity(city);
        owner.setTelephone(telephone);
        owner.setPets(pets);

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

    private Pet createPet(LocalDate birthDate, PetType petType) {
        Pet pet = new Pet();
        pet.setBirthDate(birthDate);
        pet.setPetType(petType);

        return petService.save(pet);
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = createPetType("Dog");
        PetType cat = createPetType("Cat");

        Pet goomba = createPet(LocalDate.parse("2015-07-24"), dog);
        Pet michu = createPet(LocalDate.parse("2019-01-03"), cat);

        Set<Pet> pets = new HashSet<>(Arrays.asList(michu, goomba));

        createOwner("Juan Manuel", "Oviedo", "Calle Fuenlabrada 36",
            "Getafe", "676938322", pets);
        createOwner("Mireia", "Romero Moreno", "Calle Fuenlabrada 36",
            "Getafe", "695932729", pets);
        System.out.println("Loaded owners ...");

        createVet("Ramiro", "Moreno Romero");
        createVet("Alicia", "Garcia Ramirez");
        System.out.println("Loaded vets ...");
    }
}
