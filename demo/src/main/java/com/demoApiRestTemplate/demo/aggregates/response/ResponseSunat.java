package com.demoApiRestTemplate.demo.aggregates.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class ResponseSunat {
    public String razonSocial;
    public String tipoDocumento;
    public String numeroDocumento;
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

    @Override
    public String toString() {
        return "ResponseReniec{" +
                "razonSocial='" + razonSocial + '\'' +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", numeroDocumento='" + numeroDocumento + '\'' +
                ", condicion='" + condicion + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ubigeo='" + ubigeo + '\'' +
                ", viaTipo='" + viaTipo + '\'' +
                ", viaNombre='" + viaNombre + '\'' +
                ", zonaCodigo='" + zonaCodigo + '\'' +
                '}';
    }
}
