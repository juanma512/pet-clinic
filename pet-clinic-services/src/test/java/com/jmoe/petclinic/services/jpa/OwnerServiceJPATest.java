package com.jmoe.petclinic.services.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.jmoe.petclinic.model.Owner;
import com.jmoe.petclinic.repositories.OwnerRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OwnerServiceJPATest {

    private static final Long OWNER_ID = 1L;
    private static final String OWNER_LAST_NAME = "Oviedo";
    private static final String OWNER_FIRST_NAME = "Mireia";

    @Mock
    OwnerRepository ownerRepository;
    @InjectMocks
    OwnerServiceJPA ownerServiceJPA;

    @Test
    void findByLastName() {
        //Given
        Owner owner = Owner.builder().lastName(OWNER_LAST_NAME).build();

        // When
        when(ownerRepository.findByLastName(anyString())).thenReturn(owner);

        // Then
        Owner actual = ownerServiceJPA.findByLastName(OWNER_LAST_NAME);
        assertNotNull(actual);
        assertEquals(OWNER_LAST_NAME, actual.getLastName());
        verify(ownerRepository, times(1)).findByLastName(anyString());
    }

    @Test
    void findAll() {
        // Given
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().lastName(OWNER_LAST_NAME).build());
        owners.add(Owner.builder().firstName(OWNER_FIRST_NAME).build());

        // WHen
        when(ownerRepository.findAll()).thenReturn(owners);

        // Then
        Set<Owner> actual = ownerServiceJPA.findAll();
        assertNotNull(actual);
        assertEquals(2, actual.size());
        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        //Given
        Optional<Owner> owner = Optional.of(Owner.builder().id(OWNER_ID).build());

        // WHen
        when(ownerRepository.findById(anyLong())).thenReturn(owner);

        // Then
        Owner actual = ownerServiceJPA.findById(OWNER_ID);
        assertNotNull(actual);
        assertEquals(OWNER_ID, actual.getId());
        verify(ownerRepository, times(1)).findById(anyLong());
    }

    @Test
    void save() {
        // Given
        Owner owner = Owner.builder().id(OWNER_ID).lastName(OWNER_LAST_NAME).build();

        // When
        when(ownerRepository.save(any(Owner.class))).thenReturn(owner);

        // Then
        Owner actual = ownerServiceJPA.save(owner);
        assertNotNull(actual);
        assertEquals(OWNER_ID, actual.getId());
        verify(ownerRepository, times(1)).save(any(Owner.class));
    }

    @Test
    void delete() {
        //Given
        Owner owner = Owner.builder().lastName(OWNER_LAST_NAME).build();

        // Then
        ownerServiceJPA.delete(owner);
        verify(ownerRepository, times(1)).delete(any(Owner.class));
    }

    @Test
    void deleteById() {
        // Then
        ownerServiceJPA.deleteById(OWNER_ID);
        verify(ownerRepository, times(1)).deleteById(anyLong());
    }
}