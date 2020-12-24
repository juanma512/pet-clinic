package com.jmoe.petclinic.controllers;

import com.jmoe.petclinic.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping({"", "/", "/index", "/index.html"})
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "/owners/index";
    }

    @GetMapping("/find")
    public String findOwners() {
        return "error";
    }

    @GetMapping("/{id}")
    public ModelAndView showOwner(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/owners/details");
        modelAndView.addObject("owner", ownerService.findById(id));
        return modelAndView;
    }

}
