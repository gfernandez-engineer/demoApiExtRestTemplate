package com.demoApiRestTemplate.demo.service;

import com.demoApiRestTemplate.demo.entity.PersonaNaturalEntity;

import java.io.IOException;

public interface PersonaNaturalService {
        PersonaNaturalEntity guardar(String dni) throws IOException;

}
