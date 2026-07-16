package com.restaurant.restaurantaplicacion.service;

import com.restaurant.restaurantaplicacion.dto.AnaliticaDemandaDTO;
import com.restaurant.restaurantaplicacion.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticsService {

    @Autowired
    private PedidoRepository pedidoRepository;

    private Long calcularPrediccionSMA(List<Integer> ventasUltimosDias) {
        if (ventasUltimosDias == null || ventasUltimosDias.isEmpty()) {
            return 0L;
        }

        double sumaVentas = 0;
        for (Integer venta : ventasUltimosDias) {
            sumaVentas += venta;
        }

        double promedioDiario = sumaVentas / ventasUltimosDias.size();
        return (long) Math.ceil(promedioDiario);
    }

    public List<AnaliticaDemandaDTO> obtenerProyeccionDemanda() {
        List<AnaliticaDemandaDTO> proyecciones = new ArrayList<>();
        List<String> platosVendidos = pedidoRepository.obtenerNombresPlatosVendidos();

        for (String nombrePlato : platosVendidos) {
            Long ventaTotal = pedidoRepository.obtenerVentaTotalHistorica(nombrePlato);
            if (ventaTotal == null) ventaTotal = 0L;

            List<Integer> historialDiario = pedidoRepository.obtenerVentasUltimos7Dias(nombrePlato);
            Long cantidadSugerida = calcularPrediccionSMA(historialDiario);

            // Usamos el constructor NUEVO (con 3 parámetros)
            proyecciones.add(new AnaliticaDemandaDTO(nombrePlato, ventaTotal, cantidadSugerida));
        }

        return proyecciones;
    }
}