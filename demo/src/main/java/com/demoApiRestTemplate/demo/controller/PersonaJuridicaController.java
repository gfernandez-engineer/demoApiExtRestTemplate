package com.demoApiRestTemplate.demo.controller;



import com.demoApiRestTemplate.demo.entity.PersonaJuridicaEntity;
import com.demoApiRestTemplate.demo.service.PersonaJuridicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("/v1/personaJuridica")
public class PersonaJuridicaController {
    private final PersonaJuridicaService personaJuridicaService;
    public PersonaJuridicaController(PersonaJuridicaService personaJuridicaService) {
        this.personaJuridicaService = personaJuridicaService;
    }

    @PostMapping
    public ResponseEntity<PersonaJuridicaEntity> guardarPersona(
            @RequestParam("ruc") String ruc) throws IOException {
        PersonaJuridicaEntity personaJuridica = personaJuridicaService.guardarPerJur(ruc);
        return new ResponseEntity<>(personaJuridica, HttpStatus.CREATED);
    }
}
