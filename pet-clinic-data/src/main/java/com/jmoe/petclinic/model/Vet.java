package com.jmoe.petclinic.model;

import java.util.Set;

public class Vet extends Person {

    Set<Specialty> specialties;

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(Set<Specialty> specialties) {
        this.specialties = specialties;
    }

    @Override
    public String toString() {
        return "Vet{" +
            "firstName=" + getFirstName() +
            ", lastName=" + getLastName() +
            ", specialties=" + specialties +
            '}';
    }
}
