package com.restaurant.restaurantaplicacion.dto;

public class AnaliticaDemandaDTO {
    
    private String nombrePlato;
    private Long cantidadVendidaHistorica;
    private Long cantidadProyectada;

    // Constructor que usará Spring Data JPA para mapear la consulta
    public AnaliticaDemandaDTO(String nombrePlato, Long cantidadVendidaHistorica) {
        this.nombrePlato = nombrePlato;
        this.cantidadVendidaHistorica = cantidadVendidaHistorica;
        this.cantidadProyectada = 0L; // Se calculará después en el Service
    }

    // Getters y Setters
    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public Long getCantidadVendidaHistorica() {
        return cantidadVendidaHistorica;
    }

    public void setCantidadVendidaHistorica(Long cantidadVendidaHistorica) {
        this.cantidadVendidaHistorica = cantidadVendidaHistorica;
    }

    public Long getCantidadProyectada() {
        return cantidadProyectada;
    }

    public void setCantidadProyectada(Long cantidadProyectada) {
        this.cantidadProyectada = cantidadProyectada;
    }
}