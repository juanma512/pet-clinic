package com.jmoe.petclinic.bootstrap;

import com.jmoe.petclinic.model.Owner;
import com.jmoe.petclinic.model.Pet;
import com.jmoe.petclinic.model.PetType;
import com.jmoe.petclinic.model.Speciality;
import com.jmoe.petclinic.model.Vet;
import com.jmoe.petclinic.services.OwnerService;
import com.jmoe.petclinic.services.PetService;
import com.jmoe.petclinic.services.PetTypeService;
import com.jmoe.petclinic.services.SpecialityService;
import com.jmoe.petclinic.services.VetService;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
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
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService,
        PetTypeService petTypeService, PetService petService,
        SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
        this.specialityService = specialityService;
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

    private void createVet(String firstName, String lastName, Set<Speciality> specialities) {
        Vet vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vet.setSpecialities(specialities);

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

    private Speciality createSpeciality(String description) {
        Speciality speciality = new Speciality();
        speciality.setDescription(description);

        return specialityService.save(speciality);
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

        Speciality surgery = createSpeciality("Surgery");
        Speciality rx = createSpeciality("RX");
        Speciality nutrition_and_food = createSpeciality("Nutrition and Food");

        createVet("Ramiro", "Moreno Romero", new HashSet<>(Collections.singletonList(surgery)));
        createVet("Alicia", "Garcia Ramirez", new HashSet<>(Arrays.asList(rx, nutrition_and_food)));
        System.out.println("Loaded vets ...");
    }
}
