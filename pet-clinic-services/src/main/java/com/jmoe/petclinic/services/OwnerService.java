package com.jmoe.petclinic.services;

import com.jmoe.petclinic.model.Owner;
import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);

}
