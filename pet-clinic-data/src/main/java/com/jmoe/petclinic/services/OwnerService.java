package com.jmoe.petclinic.services;

import com.jmoe.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

  Owner findByLastName(String lastName);

}
