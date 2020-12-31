package com.jmoe.petclinic.controllers;

import com.jmoe.petclinic.model.Vet;
import com.jmoe.petclinic.services.VetService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class VetController {

    private final VetService vetService;

    @GetMapping({"/vets", "/vets/", "/vets/index", "/index.html"})
    public String listVets(Model model) {
        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }

    @GetMapping("/api/vets")
    public @ResponseBody Set<Vet> getVets() {
        return vetService.findAll();
    }
}
