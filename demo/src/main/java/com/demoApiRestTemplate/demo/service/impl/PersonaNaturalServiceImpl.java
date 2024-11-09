package com.demoApiRestTemplate.demo.service.impl;

import com.demoApiRestTemplate.demo.aggregates.constants.Constants;
import com.demoApiRestTemplate.demo.aggregates.response.ResponseReniec;
import com.demoApiRestTemplate.demo.entity.PersonaNaturalEntity;
import com.demoApiRestTemplate.demo.redis.RedisService;
import com.demoApiRestTemplate.demo.repository.PersonaNaturalRepository;
import com.demoApiRestTemplate.demo.service.PersonaNaturalService;

import com.demoApiRestTemplate.demo.util.Util;
import lombok.extern.log4j.Log4j2;
import org.aspectj.weaver.ast.Call;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Objects;
@Service
@Log4j2
public class PersonaNaturalServiceImpl implements PersonaNaturalService {
    private final PersonaNaturalRepository personaNaturalRepository;
    /*private final ClientReniec clientReniec;*/
    private final RestTemplate restTemplate;

    public  final RedisService redisService;

    @Value("${token.api}")
    private String token;

    public PersonaNaturalServiceImpl(PersonaNaturalRepository personaNaturalRepository, RestTemplate restTemplate, RedisService redisService) {
        this.personaNaturalRepository = personaNaturalRepository;
        //this.clientReniec = clientReniec;
        this.restTemplate = restTemplate;
        this.redisService = redisService;
    }

    @Override
    public PersonaNaturalEntity guardar(String dni) throws IOException {
        PersonaNaturalEntity personaNatural = getEntityForRestTemplate(dni);  //RestTemplate
        if(Objects.nonNull(personaNatural)){
            return personaNaturalRepository.save(personaNatural);
        }else {
            return null;
        }
    }

    private PersonaNaturalEntity getEntityForRestTemplate(String dni) throws IOException {
        PersonaNaturalEntity personaNaturalEntity = new PersonaNaturalEntity();
        ResponseReniec datosReniec = executeRestTemplate(dni);

        //Recupero la info de redis
        String redisInfo = redisService.getDataFromRedis(dni);

        //Valido si existe la info
        if (Objects.nonNull(redisInfo))
        {
            datosReniec = Util.convertirDesdeString(redisInfo, ResponseReniec.class);
        }else{
            // Sino existe la data en redis me voy a Reniec api
            datosReniec = executeRestTemplate(dni);
            // Convertir a String para poder guardarlo en Redis
            String dataForRedis = Util.convertirAString(datosReniec);
            // Guardando en Redis la información
            redisService.saveInRedis(dni,dataForRedis,Constants.REDIS_TTL);
        }

        //Validar el resultado
        if(Objects.nonNull(datosReniec)){
            personaNaturalEntity.setNombres(datosReniec.getNombres());
            personaNaturalEntity.setApellidoPaterno(datosReniec.getApellidoPaterno());
            personaNaturalEntity.setApellidoMaterno(datosReniec.getApellidoMaterno());
            personaNaturalEntity.setNumeroDocumento(datosReniec.getNumeroDocumento());
            personaNaturalEntity.setTipoDocumento(datosReniec.getTipoDocumento());
            personaNaturalEntity.setDigitoVerificador(datosReniec.getDigitoVerificador());
            personaNaturalEntity.setEstado(Constants.ESTADO_ACTVO);
            personaNaturalEntity.setUserCreated(Constants.USER_CREATED);
            personaNaturalEntity.setDateCreated(new Timestamp(System.currentTimeMillis()));
        }
        return personaNaturalEntity;
    }


    private ResponseReniec executeRestTemplate(String dni){
        String url = "http://api.apis.net.pe/v2/reniec/dni?numero="+dni;

        ResponseEntity<ResponseReniec> executeRestTemplate = restTemplate.exchange(
            url,//URL A LA CUAL SE VA A EJECUTAR
                HttpMethod.GET, //TIPO DE SOLICITUD AL QUE PERTENECE LA URL
                new HttpEntity<>(createHeader()), //CABECERA || HEADERS
                ResponseReniec.class //RESPONSE A CASTEAR
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

        return  headers;
    }

}
