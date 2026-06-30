package com.restaurant.restaurantaplicacion.controller;

import com.restaurant.restaurantaplicacion.dto.AnaliticaDemandaDTO;
import com.restaurant.restaurantaplicacion.dto.HoraPuntaDTO;
import com.restaurant.restaurantaplicacion.dto.RankingMozoDTO;
import com.restaurant.restaurantaplicacion.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {
    @Autowired
    private com.restaurant.restaurantaplicacion.repository.PedidoRepository pedidoRepository;
    @Autowired
    private AnalyticsService analyticsService;

    // Endpoint: http://localhost:8080/api/analytics/prediccion
    @GetMapping("/prediccion")
    public ResponseEntity<List<AnaliticaDemandaDTO>> obtenerPrediccion() {
        // Llamamos al servicio para obtener la lista matemática ya calculada
        List<AnaliticaDemandaDTO> predicciones = analyticsService.generarPrediccionDemanda();
        
        // Retornamos un status 200 OK con el cuerpo en formato JSON
        return ResponseEntity.ok(predicciones);
    }

    @GetMapping("/ranking-mozos")
    public ResponseEntity<List<RankingMozoDTO>> obtenerRankingMozos() {
        return ResponseEntity.ok(pedidoRepository.obtenerRankingMozos());
    }
    
    @GetMapping("/horas-punta")
    public ResponseEntity<List<HoraPuntaDTO>> obtenerHorasPunta() {
        return ResponseEntity.ok(pedidoRepository.obtenerHorasPunta());
    }
}