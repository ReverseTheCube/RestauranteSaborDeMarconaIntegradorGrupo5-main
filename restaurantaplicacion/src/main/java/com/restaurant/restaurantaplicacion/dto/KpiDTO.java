package com.restaurant.restaurantaplicacion.dto;

public class KpiDTO {
    private Double idt; // Índice de Digitalización
    private Double ppm; // Precisión de Proyección
    private Double tpc; // Tiempo de Procesamiento

    public KpiDTO(Double idt, Double ppm, Double tpc) {
        this.idt = idt;
        this.ppm = ppm;
        this.tpc = tpc;
    }

    // Getters y Setters
    public Double getIdt() { return idt; }
    public void setIdt(Double idt) { this.idt = idt; }
    public Double getPpm() { return ppm; }
    public void setPpm(Double ppm) { this.ppm = ppm; }
    public Double getTpc() { return tpc; }
    public void setTpc(Double tpc) { this.tpc = tpc; }
}