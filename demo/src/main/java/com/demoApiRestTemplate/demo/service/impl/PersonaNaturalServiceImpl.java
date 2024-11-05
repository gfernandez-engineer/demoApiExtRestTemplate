package com.demoApiRestTemplate.demo.service.impl;

import com.demoApiRestTemplate.demo.aggregates.response.ResponseReniec;
import com.demoApiRestTemplate.demo.entity.PersonaNaturalEntity;
import com.demoApiRestTemplate.demo.repository.PersonaNaturalRepository;
import com.demoApiRestTemplate.demo.service.PersonaNaturalService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class PersonaNaturalServiceImpl implements PersonaNaturalService {
    private final PersonaNaturalRepository personaNaturalRepository;

    private final RestTemplate restTemplate;

    public PersonaNaturalServiceImpl(PersonaNaturalRepository personaNaturalRepository, ClientReniec clientReniec, RestTemplate restTemplate) {
        this.personaNaturalRepository = personaNaturalRepository;
        //this.clientReniec = clientReniec;
        this.restTemplate = restTemplate;
    }

    @Override
    public PersonaNaturalEntity guardar(String dni) throws IOException {
        return null;
    }

    private ResponseReniec executeRestTemplate(String dni){
        String url = "http://api.apis.net.pe/v2/reniec/dni?numero="+dni;

        ResponseEntity<ResponseReniec> executeRestTemplate = restTemplate.exchange(
            url,//URL A LA CUAL SE VA A EJECUTAR
                HttpMethod.GET, //TIPO DE SOLICITUD AL QUE PERTENECE LA URL
                new HttpEntity<>(createHeader()), //CABECERA || HEADERS
                ResponseReniec.class //RESPONSE A CASTEAR
        )
    }

    protected HttpHeaders createHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer","");
    }

}
