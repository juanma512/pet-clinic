package com.jmoe.petclinic.formatters;

import com.jmoe.petclinic.model.PetType;
import com.jmoe.petclinic.repositories.PetTypeRepository;
import java.text.ParseException;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
import lombok.RequiredArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeRepository petTypeRepository;

    @Override
    public PetType parse(String s, Locale locale) throws ParseException {
        AtomicReference<PetType> parsedPetType = new AtomicReference<>();
        petTypeRepository.findAll().forEach(
            petType -> {
                if (petType.getName().equals(s)){
                    parsedPetType.set(petType);
                }
            }
        );
        if (parsedPetType.get() != null) {
            return parsedPetType.get();
        } else {
            throw new ParseException("pet type not found" + s, 0);
        }
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
