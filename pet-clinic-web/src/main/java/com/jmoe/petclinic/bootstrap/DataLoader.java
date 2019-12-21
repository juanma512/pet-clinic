package com.jmoe.petclinic.bootstrap;

import com.jmoe.petclinic.model.Owner;
import com.jmoe.petclinic.model.Vet;
import com.jmoe.petclinic.services.OwnerService;
import com.jmoe.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setFirstName("Juan Manuel");
        owner1.setLastName("Oviedo");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Mireia");
        owner2.setLastName("Romero Moreno");

        ownerService.save(owner2);

        System.out.println("Loaded owners ...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Ramiro");
        vet1.setLastName("Moreno Romero");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Alicia");
        vet2.setLastName("Garcia Ramirez");

        vetService.save(vet2);

        System.out.println("Loaded vets ...");
    }
}
