package com.jmoe.petclinic.bootstrap;

import com.jmoe.petclinic.model.Owner;
import com.jmoe.petclinic.model.Pet;
import com.jmoe.petclinic.model.PetType;
import com.jmoe.petclinic.model.Specialty;
import com.jmoe.petclinic.model.Vet;
import com.jmoe.petclinic.model.Visit;
import com.jmoe.petclinic.services.OwnerService;
import com.jmoe.petclinic.services.PetService;
import com.jmoe.petclinic.services.PetTypeService;
import com.jmoe.petclinic.services.SpecialtyService;
import com.jmoe.petclinic.services.VetService;
import com.jmoe.petclinic.services.VisitService;
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
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService,
        PetTypeService petTypeService, PetService petService,
        SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.petService = petService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    private Owner createOwner(String firstName, String lastName, String address, String city,
        String telephone, Set<Pet> pets) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setAddress(address);
        owner.setCity(city);
        owner.setTelephone(telephone);
        owner.setPets(pets);

        return ownerService.save(owner);
    }

    private Vet createVet(String firstName, String lastName, Set<Specialty> specialties) {
        Vet vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vet.setSpecialties(specialties);

        return vetService.save(vet);
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

    private Specialty createSpecialty(String description) {
        Specialty specialty = new Specialty();
        specialty.setDescription(description);

        return specialtyService.save(specialty);
    }

    private Visit createVisit(String date, String description, Pet pet) {
        Visit visit = new Visit();
        visit.setDate(LocalDate.parse(date));
        visit.setDescription(description);
        visit.setPet(pet);
        return visitService.save(visit);
    }

    @Override
    public void run(String... args) throws Exception {
        if (petTypeService.findAll().size() == 0) {
            PetType dog = createPetType("Dog");
            PetType cat = createPetType("Cat");
            System.out.println("Loaded pet types ...");

            Pet goomba = createPet(LocalDate.parse("2015-07-24"), dog);
            Pet michu = createPet(LocalDate.parse("2019-01-03"), cat);
            Set<Pet> pets = new HashSet<>(Arrays.asList(michu, goomba));
            System.out.println("Loaded pets ...");

            createOwner("Juan Manuel", "Oviedo", "Calle Fuenlabrada 36",
                "Getafe", "676938322", pets);
            createOwner("Mireia", "Romero Moreno", "Calle Fuenlabrada 36",
                "Getafe", "695932729", pets);
            System.out.println("Loaded owners ...");

            createVisit("2020-01-15", "Sneezy kitty", michu);
            System.out.println("Loaded visits ...");

            Specialty surgery = createSpecialty("Surgery");
            Specialty rx = createSpecialty("RX");
            Specialty nutrition_and_food = createSpecialty("Nutrition and Food");
            System.out.println("Loaded specialties ...");

            createVet("Ramiro", "Moreno Romero",
                new HashSet<>(Collections.singletonList(surgery)));
            createVet("Alicia", "Garcia Ramirez",
                new HashSet<>(Arrays.asList(rx, nutrition_and_food)));
            System.out.println("Loaded vets ...");
        }
    }
}
