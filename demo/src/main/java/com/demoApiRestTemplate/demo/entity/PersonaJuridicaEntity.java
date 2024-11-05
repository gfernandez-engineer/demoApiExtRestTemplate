package com.demoApiRestTemplate.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name="personaJuridica")
@Setter
@Getter
public class PersonaJuridicaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String razonSocial;
    public String tipoDocumento;
    public String numeroDocumento;
    public Integer estado;
    public String condicion;
    public String direccion;
    public String ubigeo;
    public String viaTipo;
    public String viaNombre;
    public String zonaCodigo;
    public String zonaTipo;
    public String numero;
    public String interior;
    public String lote;
    public String dpto;
    public String manzana;
    public String kilometro;
    public String distrito;
    public String provincia;
    public String departamento;
    public boolean esAgenteRetencion;
    public String tipo;
    public String actividadEconomica;
    public String numeroTrabajadores;
    public String tipoFacturacion;
    public String tipoContabilidad;
    public String comercioExterior;
    private String userCreated;
    private Timestamp dateCreated;
}
