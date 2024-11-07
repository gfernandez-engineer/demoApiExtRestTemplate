package com.demoApiRestTemplate.demo.controller;



import com.demoApiRestTemplate.demo.entity.PersonaNaturalEntity;
import com.demoApiRestTemplate.demo.service.PersonaNaturalService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
//PARA VER LA DOCU entrar a: http://localhost:8082/swagger-ui/index.html
public class PersonaNaturalController {

    private final PersonaNaturalService personaNaturalService;
    public PersonaNaturalController(PersonaNaturalService personaNaturalService) {
        this.personaNaturalService = personaNaturalService;
    }

    @PostMapping
    @Operation(
            summary = "Guardar una nueva persona natural",
            description = "Crea una nueva persona natural y la almacena en la BD",
            parameters = @Parameter(
                    name = "dni",
                    required = true,
                    example = "12345678"
            )
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Operacion Exitosa", content = {
                    @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonaNaturalEntity.class)),
                    @Content(mediaType = "application/xml",
                    schema = @Schema(implementation = PersonaNaturalEntity.class))
            }),
            @ApiResponse(responseCode = "500", description = "Error en el servidor"),
            @ApiResponse(responseCode = "503", description = "INTERNAL ERROR FATAL")

    }
    )
    public ResponseEntity<PersonaNaturalEntity> guardarPersona(
            @Parameter(description = "DNI de la persona a guardar", required = true)
            @RequestParam("dni") String dni) throws IOException {
        PersonaNaturalEntity personaNatural = personaNaturalService.guardar(dni);
        return new ResponseEntity<>(personaNatural, HttpStatus.CREATED);
    }
}