package com.demoApiRestTemplate.demo.controller;



import com.demoApiRestTemplate.demo.entity.PersonaNaturalEntity;
import com.demoApiRestTemplate.demo.service.PersonaNaturalService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nonnull;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1/personaNatural")
@Tag(
        name = "API GESTION DE PERSONAS NATURALES",
        description = "Esta API contiene los EndPoints necesarios para la gestión de personas naturales " +
                "esto incluye desde crear hasta eliminar una persona",
        externalDocs = @ExternalDocumentation(
                description = "Documentacion adicional de ApiRestTemplate",
                url = "https://github.com/gfernandez-engineer/demoApiExtRestTemplate.git"
        )

)
public class PersonaNaturalController {

    private final PersonaNaturalService personaNaturalService;
    public PersonaNaturalController(PersonaNaturalService personaNaturalService) {
        this.personaNaturalService = personaNaturalService;
    }

    @PostMapping
    public ResponseEntity<PersonaNaturalEntity> guardarPersona(
            @RequestParam("dni") String dni) throws IOException {
        PersonaNaturalEntity personaNatural = personaNaturalService.guardar(dni);
        return new ResponseEntity<>(personaNatural, HttpStatus.CREATED);
    }
}