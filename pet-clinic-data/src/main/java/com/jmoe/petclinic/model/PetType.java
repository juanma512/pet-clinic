package com.jmoe.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "types")
@Getter
@Setter
@ToString
public class PetType extends BaseEntity {

    @Column(name = "name")
    private String name;

}
