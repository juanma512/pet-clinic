package com.jmoe.petclinic.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.jmoe.petclinic.model.Owner;
import com.jmoe.petclinic.services.OwnerService;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final Long OWNER_1 = 1L;
    private static final Long OWNER_2 = 2L;

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private OwnerController ownerController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void listOwners() throws Exception {
        // Given
        Set<Owner> owners = buildOwners();

        // When
        when(ownerService.findAll()).thenReturn(owners);

        // Then
        mockMvc.perform(get("/owners"))
            .andExpect(status().isOk())
            .andExpect(view().name("/owners/index"))
            .andExpect(MockMvcResultMatchers.model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwners() throws Exception {
        // Then
        mockMvc.perform(get("/owners/find"))
            .andExpect(status().isOk())
            .andExpect(view().name("error"));
    }

    @Test
    void showOwner() throws Exception {
        // Given
        Owner owner = Owner.builder().id(OWNER_1).build();

        // When
        when(ownerService.findById(anyLong())).thenReturn(owner);

        // Then
        mockMvc.perform(get("/owners/1"))
            .andExpect(status().isOk())
            .andExpect(view().name("/owners/details"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("owner"));
    }

    private Set<Owner> buildOwners() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(OWNER_1).build());
        owners.add(Owner.builder().id(OWNER_2).build());
        return owners;
    }

}