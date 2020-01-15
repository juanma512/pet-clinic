package com.jmoe.petclinic.bootstrap;

import com.jmoe.petclinic.model.Owner;
import com.jmoe.petclinic.model.Pet;
import com.jmoe.petclinic.model.PetType;
import com.jmoe.petclinic.model.Speciality;
import com.jmoe.petclinic.model.Vet;
import com.jmoe.petclinic.model.Visit;
import com.jmoe.petclinic.services.OwnerService;
import com.jmoe.petclinic.services.PetTypeService;
import com.jmoe.petclinic.services.SpecialityService;
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
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService,
        PetTypeService petTypeService,
        SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
    }

    private Owner createOwner(String firstName, String lastName, String address, String city,
        String telephone) {
        return Owner.builder()
            .firstName(firstName)
            .lastName(lastName)
            .address(address)
            .city(city)
            .telephone(telephone)
            .pets(new HashSet<>())
            .build();
    }

    private Vet createVet(String firstName, String lastName, Set<Speciality> specialities) {
        return Vet.builder()
            .firstName(firstName)
            .lastName(lastName)
            .specialities(specialities)
            .build();
    }

    private PetType createPetType(String name) {
        return PetType.builder()
            .name(name)
            .build();
    }

    private Pet createPet(String name, LocalDate birthDate, PetType petType) {
        return Pet.builder()
            .birthDate(birthDate)
            .petType(petType)
            .name(name).build();
    }

    private Speciality createSpecialty(String description) {
        return Speciality.builder()
            .description(description).build();
    }

    private Visit createVisit(Pet pet, String date, String description) {
        return Visit.builder()
            .pet(pet)
            .date(LocalDate.parse(date))
            .description(description)
            .build();
    }

    @Override
    public void run(String... args) {
        if (petTypeService.findAll().size() == 0) {
            PetType dog = petTypeService.save(createPetType("Dog"));
            PetType cat = petTypeService.save(createPetType("Cat"));
            System.out.println("Loaded pet types ...");

            Speciality surgery = specialityService.save(createSpecialty("Surgery"));
            Speciality rx = specialityService.save(createSpecialty("RX"));
            Speciality nutrition_and_food = specialityService
                .save(createSpecialty("Nutrition and Food"));
            System.out.println("Loaded specialties ...");

            Pet goomba = createPet("Goomba", LocalDate.parse("2015-07-24"), dog);
            Pet michu = createPet("Michu", LocalDate.parse("2019-01-03"), cat);

            Owner owner1 = createOwner("Juan Manuel", "Oviedo", "Calle Fuenlabrada 36",
                "Getafe", "676938322");
            owner1.addPet(goomba);
            ownerService.save(owner1);

            Owner owner2 = createOwner("Mireia", "Romero Moreno", "Calle Fuenlabrada 36",
                "Getafe", "695932729");
            owner2.addPet(michu);
            ownerService.save(owner2);

            System.out.println("Loaded owners & pets ...");

            visitService.save(createVisit(michu, "2020-01-15", "Sneezy kitty"));
            System.out.println("Loaded visits ...");

            vetService.save(createVet("Ramiro", "Moreno Romero",
                new HashSet<>(Collections.singletonList(surgery))));
            vetService.save(createVet("Alicia", "Garcia Ramirez",
                new HashSet<>(Arrays.asList(rx, nutrition_and_food))));
            System.out.println("Loaded vets ...");
        }
    }
}
