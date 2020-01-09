package com.jmoe.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "specialities")
@Getter
@Setter
public class Specialty extends BaseEntity {

    @Column(name = "description")
    private String description;

}
