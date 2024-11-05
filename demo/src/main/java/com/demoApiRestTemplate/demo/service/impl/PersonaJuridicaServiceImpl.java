package com.demoApiRestTemplate.demo.service.impl;

import com.demoApiRestTemplate.demo.aggregates.constants.Constants;
import com.demoApiRestTemplate.demo.aggregates.response.ResponseSunat;
import com.demoApiRestTemplate.demo.entity.PersonaJuridicaEntity;
import com.demoApiRestTemplate.demo.repository.PersonaJuridicaRepository;
import com.demoApiRestTemplate.demo.service.PersonaJuridicaService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Objects;

@Service
public class PersonaJuridicaServiceImpl implements PersonaJuridicaService {
    private final PersonaJuridicaRepository personaJuridicaRepository;
    private final RestTemplate restTemplate;

    @Value("${token.api}")
    private String token;

    public PersonaJuridicaServiceImpl(PersonaJuridicaRepository personaJuridicaRepository, RestTemplate restTemplate, RestTemplate restTemplate1) {
        this.personaJuridicaRepository = personaJuridicaRepository;
        this.restTemplate = restTemplate1;
    }

    @Override
    public PersonaJuridicaEntity guardarPerJur(String ruc) throws IOException {
        PersonaJuridicaEntity personaJuridica = getEntityForRestTemplate(ruc);  //RestTemplate
        if (Objects.nonNull(personaJuridica)) {
            return personaJuridicaRepository.save(personaJuridica);
        } else {
            return null;
        }
    }

    private PersonaJuridicaEntity getEntityForRestTemplate(String ruc) throws IOException {
        PersonaJuridicaEntity personaJuridicaEntity = new PersonaJuridicaEntity();
        //Validar el resultado
        ResponseSunat datosSunat = executeRestTemplate(ruc);
        if(Objects.nonNull(datosSunat)){
            personaJuridicaEntity.setRazonSocial(datosSunat.getRazonSocial());
            personaJuridicaEntity.setTipoDocumento(datosSunat.getTipoDocumento());
            personaJuridicaEntity.setNumeroDocumento(datosSunat.getNumeroDocumento());
            personaJuridicaEntity.setCondicion(datosSunat.getCondicion());
            personaJuridicaEntity.setEstado(Constants.ESTADO_ACTVO);
            personaJuridicaEntity.setUserCreated(Constants.USER_CREATED);
            personaJuridicaEntity.setDateCreated(new Timestamp(System.currentTimeMillis()));
        }
        return personaJuridicaEntity;
    }


    private ResponseSunat executeRestTemplate(String ruc){
        String url = "http://api.apis.net.pe/v2/sunat/ruc/full?numero="+ruc;

        ResponseEntity<ResponseSunat> executeRestTemplate = restTemplate.exchange(
                url,//URL A LA CUAL SE VA A EJECUTAR
                HttpMethod.GET, //TIPO DE SOLICITUD AL QUE PERTENECE LA URL
                new HttpEntity<>(createHeader()), //CABECERA || HEADERS
                ResponseSunat.class //RESPONSE A CASTEAR
        );
        if (executeRestTemplate.getStatusCode().equals(HttpStatus.OK)){
            return executeRestTemplate.getBody();
        }else {
            return  null;
        }

    }

    private HttpHeaders createHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer" +token);
        return headers;
    }

}
