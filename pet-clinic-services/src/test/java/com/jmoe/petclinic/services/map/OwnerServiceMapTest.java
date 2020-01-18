package com.jmoe.petclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.jmoe.petclinic.model.Owner;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceMapTest {

    private static final Long OWNER_ID = 1L;
    private static final String OWNER_LAST_NAME = "Oviedo";
    private static final String OWNER_FIRST_NAME = "Mireia";

    @InjectMocks
    OwnerServiceMap ownerServiceMap;

    @BeforeEach
    void setUp() {
        ownerServiceMap.save(Owner.builder().lastName(OWNER_LAST_NAME).build());
    }

    @Test
    void findAll() {
        Set<Owner> all = ownerServiceMap.findAll();
        assertEquals(1, all.size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(OWNER_ID);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        Owner owner = ownerServiceMap.findById(OWNER_ID);
        ownerServiceMap.delete(owner);
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void save() {
        Owner owner = ownerServiceMap.save(Owner.builder().lastName(OWNER_FIRST_NAME).build());
        assertEquals(2, owner.getId());
        assertEquals(2, ownerServiceMap.findAll().size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(OWNER_ID);
        assertNotNull(owner);
        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerServiceMap.findByLastName(OWNER_LAST_NAME);
        assertNotNull(owner);
        assertEquals(OWNER_LAST_NAME, owner.getLastName());
    }
}