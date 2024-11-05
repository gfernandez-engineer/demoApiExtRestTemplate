package com.demoApiRestTemplate.demo.service;

import com.demoApiRestTemplate.demo.entity.PersonaJuridicaEntity;

import java.io.IOException;

public interface PersonaJuridicaService {
    PersonaJuridicaEntity guardarPerJur(String dni) throws IOException;
}
