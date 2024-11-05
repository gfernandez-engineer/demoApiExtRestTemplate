package com.demoApiRestTemplate.demo.repository;


import com.demoApiRestTemplate.demo.entity.PersonaJuridicaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaJuridicaRepository extends JpaRepository<PersonaJuridicaEntity,Long> {
}
