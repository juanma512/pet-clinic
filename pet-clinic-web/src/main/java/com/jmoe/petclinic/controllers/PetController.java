package com.jmoe.petclinic.controllers;

import com.jmoe.petclinic.model.Owner;
import com.jmoe.petclinic.model.Pet;
import com.jmoe.petclinic.model.PetType;
import com.jmoe.petclinic.services.OwnerService;
import com.jmoe.petclinic.services.PetService;
import com.jmoe.petclinic.services.PetTypeService;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PetController {

    private static final String VIEWS_PET_FORM = "/pets/newForm";

    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    @ModelAttribute("types")
    public Collection<PetType> getPetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text));
            }
        });
    }

    @GetMapping("/owners/{ownerId}/pets/new")
    public String initCreationForm(Owner owner, ModelMap model) {
        Pet pet = Pet.builder().build();
        owner.addPet(pet);
        model.put("pet", pet);
        return VIEWS_PET_FORM;
    }

    @PostMapping("/owners/{ownerId}/pets/new")
    public String processCreationForm(Owner owner, Pet pet, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null) {
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.addPet(pet);
        if (result.hasErrors()) {
            model.put("pet", pet);
            return VIEWS_PET_FORM;
        } else {
            petService.save(pet);
            return "redirect:/owners/{ownerId}";
        }
    }

    @GetMapping("/owners/{ownerId}/pets/{petId}/edit")
    public String initEditForm(@PathVariable Long petId, ModelMap model) {
        model.put("pet", petService.findById(petId));
        return VIEWS_PET_FORM;
    }

    @PostMapping("/owners/{ownerId}/pets/*/edit")
    public String processEditForm(Pet pet, Owner owner, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.put("pet", pet);
            return VIEWS_PET_FORM;
        } else {
            owner.addPet(pet);
            petService.save(pet);
            return "redirect:/owners/{ownerId}";
        }
    }

}
