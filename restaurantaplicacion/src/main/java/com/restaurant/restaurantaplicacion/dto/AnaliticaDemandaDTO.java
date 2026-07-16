package com.restaurant.restaurantaplicacion.dto;

public class AnaliticaDemandaDTO {
    
    private String nombrePlato;
    private Long cantidadVendidaHistorica;
    private Long cantidadProyectada; // Nuevo campo para la barra roja

    // 1. Constructor vacío
    public AnaliticaDemandaDTO() {
    }

    // 2. Constructor ANTIGUO (El que usa tu consulta findPlatosMasVendidosDesde)
    public AnaliticaDemandaDTO(String nombrePlato, Long cantidadVendidaHistorica) {
        this.nombrePlato = nombrePlato;
        this.cantidadVendidaHistorica = cantidadVendidaHistorica;
        this.cantidadProyectada = 0L; // Valor por defecto
    }

    // 3. Constructor NUEVO (El que usará nuestro AnalyticsService)
    public AnaliticaDemandaDTO(String nombrePlato, Long cantidadVendidaHistorica, Long cantidadProyectada) {
        this.nombrePlato = nombrePlato;
        this.cantidadVendidaHistorica = cantidadVendidaHistorica;
        this.cantidadProyectada = cantidadProyectada;
    }

    // Getters y Setters
    public String getNombrePlato() { return nombrePlato; }
    public void setNombrePlato(String nombrePlato) { this.nombrePlato = nombrePlato; }

    public Long getCantidadVendidaHistorica() { return cantidadVendidaHistorica; }
    public void setCantidadVendidaHistorica(Long cantidadVendidaHistorica) { this.cantidadVendidaHistorica = cantidadVendidaHistorica; }

    public Long getCantidadProyectada() { return cantidadProyectada; }
    public void setCantidadProyectada(Long cantidadProyectada) { this.cantidadProyectada = cantidadProyectada; }
}