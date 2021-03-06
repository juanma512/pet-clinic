package com.jmoe.petclinic.services.map;

import com.jmoe.petclinic.model.Owner;
import com.jmoe.petclinic.services.OwnerService;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements
    OwnerService {

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner save(Owner object) {
        if (object == null) {
            return null;
        } else {
            if (object.getPets() != null) {
                object.getPets().forEach(pet -> {
                    if (pet.getPetType() == null) {
                        throw new RuntimeException("Pet Type is required");
                    } else {
                        pet.setOwner(object);
                    }
                });
            }
            return super.save(object);
        }
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return map.values().stream().filter(owner -> owner.getLastName().equals(lastName))
            .findFirst().orElse(null);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return map.values().stream().filter(owner -> owner.getLastName().contains(lastName))
            .collect(Collectors.toList());
    }
}
