package com.restaurant.restaurantaplicacion.repository;

import com.restaurant.restaurantaplicacion.dto.AnaliticaDemandaDTO;
import com.restaurant.restaurantaplicacion.dto.HoraPuntaDTO;
import com.restaurant.restaurantaplicacion.dto.RankingMozoDTO;
import com.restaurant.restaurantaplicacion.model.EstadoPedido;
import com.restaurant.restaurantaplicacion.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findAllByFechaHoraBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    @Query("SELECT p.infoServicio, p.usuario.id, p.estado FROM Pedido p WHERE p.tipoServicio = 'LOCAL' AND p.estado IN ('PENDIENTE', 'POR_PAGAR')")
    List<Object[]> findMesasOcupadasConUsuario();
    
    Optional<Pedido> findByInfoServicioAndEstadoAndTipoServicio(String infoServicio, EstadoPedido estado, String tipoServicio);

    // --- CONSULTA MAESTRA PARA BÚSQUEDA ---
    @Query("SELECT p FROM Pedido p " +
           "LEFT JOIN p.cliente c " +
           "LEFT JOIN p.empresa e " +
           "WHERE " +
           "(:fechaInicio IS NULL OR p.fechaHora >= :fechaInicio) AND " +
           "(:fechaFin IS NULL OR p.fechaHora <= :fechaFin) AND " +
           "(:clienteId IS NULL OR (c.id IS NOT NULL AND c.id = :clienteId)) AND " +
           "(:rucEmpresa IS NULL OR (e.ruc IS NOT NULL AND e.ruc = :rucEmpresa)) AND " +
           "(:infoServicio IS NULL OR p.infoServicio = :infoServicio)")
    List<Pedido> buscarPedidosConFiltros(
            @Param("fechaInicio") LocalDateTime fechaInicio,
            @Param("fechaFin") LocalDateTime fechaFin,
            @Param("clienteId") Long clienteId,
            @Param("rucEmpresa") String rucEmpresa,
            @Param("infoServicio") String infoServicio
    );

    @Query("SELECT new com.restaurant.restaurantaplicacion.dto.AnaliticaDemandaDTO(pp.plato.nombre, SUM(pp.cantidad)) " +
           "FROM PedidoPlato pp " +
           "WHERE pp.pedido.estado = 'PAGADO' AND pp.pedido.fechaHora >= :fechaInicio " +
           "GROUP BY pp.plato.nombre " +
           "ORDER BY SUM(pp.cantidad) DESC")
    List<AnaliticaDemandaDTO> findPlatosMasVendidosDesde(@Param("fechaInicio") LocalDateTime fechaInicio);

    //Ranking de Mejores Mozos
    @Query("SELECT new com.restaurant.restaurantaplicacion.dto.RankingMozoDTO(p.usuario.usuario, COUNT(p), SUM(p.total)) " +
           "FROM Pedido p " +
           "WHERE p.estado = 'PAGADO' " +
           "GROUP BY p.usuario.usuario " +
           "ORDER BY COUNT(p) DESC")
    List<RankingMozoDTO> obtenerRankingMozos();

    //Mapa de Calor de Horas Punta
    @Query("SELECT new com.restaurant.restaurantaplicacion.dto.HoraPuntaDTO(HOUR(p.fechaHora), COUNT(p)) " +
           "FROM Pedido p " +
           "WHERE p.estado = 'PAGADO' " +
           "GROUP BY HOUR(p.fechaHora) " +
           "ORDER BY HOUR(p.fechaHora) ASC")
    List<HoraPuntaDTO> obtenerHorasPunta();

    // =========================================================================
    // CONSULTAS PREDICTIVAS (Algoritmo SMA)
    // =========================================================================

    @Query("SELECT DISTINCT pl.nombre FROM PedidoPlato pp JOIN pp.plato pl JOIN pp.pedido p WHERE p.estado = 'PAGADO'")
    List<String> obtenerNombresPlatosVendidos();

    @Query("SELECT SUM(pp.cantidad) FROM PedidoPlato pp JOIN pp.plato pl JOIN pp.pedido p WHERE p.estado = 'PAGADO' AND pl.nombre = :nombrePlato")
    Long obtenerVentaTotalHistorica(@Param("nombrePlato") String nombrePlato);

    // AHORA SÍ CON LAS TABLAS EXACTAS DE MYSQL: pedido_platos, pedidos, platos
    @Query(value = "SELECT COALESCE(SUM(pp.cantidad), 0) " +
                   "FROM pedido_platos pp " +
                   "JOIN pedidos p ON pp.pedido_id = p.id " +
                   "JOIN platos pl ON pp.plato_id = pl.id " +
                   "WHERE pl.nombre = :nombrePlato AND p.estado = 'PAGADO' " +
                   "GROUP BY DATE(p.fecha_hora) " +
                   "ORDER BY DATE(p.fecha_hora) DESC LIMIT 7", 
           nativeQuery = true)
    List<Integer> obtenerVentasUltimos7Dias(@Param("nombrePlato") String nombrePlato);
}