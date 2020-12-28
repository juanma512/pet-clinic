package com.jmoe.petclinic.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.jmoe.petclinic.model.Owner;
import com.jmoe.petclinic.services.OwnerService;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
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
    void showOwner() throws Exception {
        // Given
        Owner owner = Owner.builder().id(OWNER_1).build();

        // When
        when(ownerService.findById(anyLong())).thenReturn(owner);

        // Then
        mockMvc.perform(get("/owners/1"))
            .andExpect(status().isOk())
            .andExpect(view().name("/owners/details"))
            .andExpect(model().attributeExists("owner"));
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
            .andExpect(status().isOk())
            .andExpect(view().name("/owners/findForm"))
            .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void processFindFormReturnMany() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString()))
            .thenReturn(Arrays.asList(Owner.builder().id(OWNER_1).build(),
                Owner.builder().id(OWNER_2).build()));

        mockMvc.perform(get("/owners"))
            .andExpect(status().isOk())
            .andExpect(view().name("/owners/index"))
            .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void processFindFormReturnOne() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString()))
            .thenReturn(Collections.singletonList(Owner.builder().id(OWNER_1).build()));

        mockMvc.perform(get("/owners"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void processFindFormEmptyReturnMany() throws Exception {
        when(ownerService.findAllByLastNameLike(anyString()))
            .thenReturn(Arrays.asList(Owner.builder().id(OWNER_1).build(),
                Owner.builder().id(OWNER_2).build()));

        mockMvc.perform(get("/owners")
            .param("lastName", ""))
            .andExpect(status().isOk())
            .andExpect(view().name("/owners/index"))
            .andExpect(model().attribute("owners", hasSize(2)));
    }

    private Set<Owner> buildOwners() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(OWNER_1).build());
        owners.add(Owner.builder().id(OWNER_2).build());
        return owners;
    }

}