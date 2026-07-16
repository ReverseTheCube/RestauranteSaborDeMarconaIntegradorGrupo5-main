package com.restaurant.restaurantaplicacion.controller;

import com.restaurant.restaurantaplicacion.dto.AnaliticaDemandaDTO;
import com.restaurant.restaurantaplicacion.dto.HoraPuntaDTO;
import com.restaurant.restaurantaplicacion.dto.RankingMozoDTO;
import com.restaurant.restaurantaplicacion.dto.KpiDTO;
import com.restaurant.restaurantaplicacion.repository.PedidoRepository;
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
    private PedidoRepository pedidoRepository;

    @Autowired
    private AnalyticsService analyticsService;

    // 1. ENDPOINT PREDICCIÓN DE DEMANDA (CORREGIDO)
    @GetMapping("/prediccion")
    public ResponseEntity<List<AnaliticaDemandaDTO>> obtenerPrediccion() {
        // Aquí estaba el error. Ahora llama correctamente a 'obtenerProyeccionDemanda'
        return ResponseEntity.ok(analyticsService.obtenerProyeccionDemanda());
    }

    // 2. ENDPOINT RANKING MOZOS
    @GetMapping("/ranking-mozos")
    public ResponseEntity<List<RankingMozoDTO>> obtenerRankingMozos() {
        return ResponseEntity.ok(pedidoRepository.obtenerRankingMozos());
    }

    // 3. ENDPOINT HORAS PUNTA
    @GetMapping("/horas-punta")
    public ResponseEntity<List<HoraPuntaDTO>> obtenerHorasPunta() {
        return ResponseEntity.ok(pedidoRepository.obtenerHorasPunta());
    }

    // 4. ENDPOINT KPIs VISUALES (Valores para el MVP)
    @GetMapping("/kpis-resumen")
    public ResponseEntity<KpiDTO> obtenerKpisResumen() {
        Double idt = 100.0; 
        Double ppm = 88.5; 
        Double tpc = 1.2; 
        return ResponseEntity.ok(new KpiDTO(idt, ppm, tpc));
    }
}