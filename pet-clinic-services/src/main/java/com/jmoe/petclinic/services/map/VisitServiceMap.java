package com.jmoe.petclinic.services.map;

import com.jmoe.petclinic.model.Visit;
import com.jmoe.petclinic.services.VisitService;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class VisitServiceMap extends AbstractMapService<Visit, Long> implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public Visit save(Visit object) {
        return super.save(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
