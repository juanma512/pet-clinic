package com.jmoe.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "specialities")
@Getter
@Setter
@ToString
public class Specialty extends BaseEntity {

    @Column(name = "description")
    private String description;

}
