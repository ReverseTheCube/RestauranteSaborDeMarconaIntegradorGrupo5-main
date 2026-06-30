package com.restaurant.restaurantaplicacion.dto;

public class HoraPuntaDTO {
    private Integer horaDelDia;
    private Long cantidadPedidos;

    public HoraPuntaDTO(Integer horaDelDia, Long cantidadPedidos) {
        this.horaDelDia = horaDelDia;
        this.cantidadPedidos = cantidadPedidos;
    }
    // Genera los Getters y Setters
    public Integer getHoraDelDia() { return horaDelDia; }
    public void setHoraDelDia(Integer horaDelDia) { this.horaDelDia = horaDelDia; }
    public Long getCantidadPedidos() { return cantidadPedidos; }
    public void setCantidadPedidos(Long cantidadPedidos) { this.cantidadPedidos = cantidadPedidos; }
}