package com.restaurant.restaurantaplicacion.dto;

public class RankingMozoDTO {
    private String nombreMozo;
    private Long cantidadPedidosAtendidos;
    private Double totalDineroGenerado;

    public RankingMozoDTO(String nombreMozo, Long cantidadPedidosAtendidos, Double totalDineroGenerado) {
        this.nombreMozo = nombreMozo;
        this.cantidadPedidosAtendidos = cantidadPedidosAtendidos;
        this.totalDineroGenerado = totalDineroGenerado;
    }
    // Genera los Getters y Setters
    public String getNombreMozo() { return nombreMozo; }
    public void setNombreMozo(String nombreMozo) { this.nombreMozo = nombreMozo; }
    public Long getCantidadPedidosAtendidos() { return cantidadPedidosAtendidos; }
    public void setCantidadPedidosAtendidos(Long cantidadPedidosAtendidos) { this.cantidadPedidosAtendidos = cantidadPedidosAtendidos; }
    public Double getTotalDineroGenerado() { return totalDineroGenerado; }
    public void setTotalDineroGenerado(Double totalDineroGenerado) { this.totalDineroGenerado = totalDineroGenerado; }
}