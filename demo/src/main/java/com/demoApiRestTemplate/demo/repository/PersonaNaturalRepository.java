package com.demoApiRestTemplate.demo.repository;


import com.demoApiRestTemplate.demo.entity.PersonaNaturalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaNaturalRepository extends JpaRepository<PersonaNaturalEntity, Long> {
}