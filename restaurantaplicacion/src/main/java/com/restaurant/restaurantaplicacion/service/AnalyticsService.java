package com.restaurant.restaurantaplicacion.service;

import com.restaurant.restaurantaplicacion.dto.AnaliticaDemandaDTO;
import com.restaurant.restaurantaplicacion.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnalyticsService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<AnaliticaDemandaDTO> generarPrediccionDemanda() {
        // 1. Extraemos los datos históricos de los últimos 30 días (Tendencia reciente)
        LocalDateTime haceUnMes = LocalDateTime.now().minusDays(30);
        List<AnaliticaDemandaDTO> datosHistoricos = pedidoRepository.findPlatosMasVendidosDesde(haceUnMes);

        // 2. Algoritmo Predictivo MVP: 
        // Asumimos un factor de crecimiento esperado del 15% (1.15) 
        // impulsado por los nuevos convenios de pensiones B2B.
        double factorCrecimiento = 1.15;

        for (AnaliticaDemandaDTO dato : datosHistoricos) {
            Long historico = dato.getCantidadVendidaHistorica();
            
            // Calculamos la proyección matemática redondeando al plato entero más cercano
            Long proyectado = Math.round(historico * factorCrecimiento);
            
            // Seteamos el valor proyectado en nuestro DTO
            dato.setCantidadProyectada(proyectado);
        }

        // Retornamos la lista ya procesada e inteligente
        return datosHistoricos;
    }
}