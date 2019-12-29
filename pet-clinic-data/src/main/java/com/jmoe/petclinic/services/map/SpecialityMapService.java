package com.jmoe.petclinic.services.map;

import com.jmoe.petclinic.model.Speciality;
import com.jmoe.petclinic.services.SpecialityService;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements
    SpecialityService {

    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }

    @Override
    public Speciality save(Speciality object) {
        return super.save(object);
    }

    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }
}
