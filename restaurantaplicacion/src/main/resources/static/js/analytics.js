document.addEventListener("DOMContentLoaded", () => {
    // Configuración global de Chart.js para que combine con el "Dark Theme"
    Chart.defaults.color = '#e0e6ea'; // Color del texto general
    Chart.defaults.borderColor = '#2a4a5f'; // Color de las líneas divisorias

    // Llamamos a los 3 endpoints de innovación al cargar la página
    cargarPrediccionDemanda();
    cargarHorasPunta();
    cargarRankingMozos();
});

// ---------------------------------------------------------
// 1. INNOVACIÓN: PREDICCIÓN DE DEMANDA (Mapeo Inverso)
// ---------------------------------------------------------
async function cargarPrediccionDemanda() {
    try {
        const response = await fetch('/api/analytics/prediccion');
        const datos = await response.json();
        
        if (datos.length > 0) {
            const etiquetasPlatos = datos.map(dato => dato.nombrePlato);
            const valoresHistoricos = datos.map(dato => dato.cantidadVendidaHistorica);
            const valoresProyectados = datos.map(dato => dato.cantidadProyectada);

            const ctx = document.getElementById('graficoPrediccion').getContext('2d');
            new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: etiquetasPlatos,
                    datasets: [
                        {
                            label: 'Ventas Reales (Histórico)',
                            data: valoresHistoricos,
                            backgroundColor: 'rgba(52, 152, 219, 0.7)', // Azul
                            borderColor: '#3498db',
                            borderWidth: 1
                        },
                        {
                            label: 'Sugerencia de Producción (Proyectado)',
                            data: valoresProyectados,
                            backgroundColor: 'rgba(231, 76, 60, 0.8)', // Rojo
                            borderColor: '#e74c3c',
                            borderWidth: 1
                        }
                    ]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: { beginAtZero: true, ticks: { stepSize: 1 } }
                    }
                }
            });
        }
    } catch (error) {
        console.error("Error al cargar la Predicción de Demanda", error);
    }
}

// ---------------------------------------------------------
// 2. INNOVACIÓN OPERATIVA: MAPA DE HORAS PUNTA
// ---------------------------------------------------------
async function cargarHorasPunta() {
    try {
        const response = await fetch('/api/analytics/horas-punta');
        const datos = await response.json();
        
        if (datos.length > 0) {
            const etiquetasHoras = datos.map(dato => dato.horaDelDia + ':00');
            const valoresPedidos = datos.map(dato => dato.cantidadPedidos);

            const ctx = document.getElementById('graficoHorasPunta').getContext('2d');
            new Chart(ctx, {
                type: 'line', 
                data: {
                    labels: etiquetasHoras,
                    datasets: [{
                        label: 'Flujo de Pedidos Atendidos',
                        data: valoresPedidos,
                        backgroundColor: 'rgba(255, 155, 26, 0.2)', // Naranja transparente del tema
                        borderColor: '#ff9b1a', // Naranja del logo
                        borderWidth: 2,
                        fill: true,
                        tension: 0.4 // Hace la curva suave
                    }]
                },
                options: {
                    responsive: true,
                    scales: {
                        y: { beginAtZero: true, ticks: { stepSize: 1 } }
                    }
                }
            });
        }
    } catch (error) {
        console.error("Error al cargar Horas Punta", error);
    }
}

// ---------------------------------------------------------
// 3. INNOVACIÓN RRHH: RANKING DE MOZOS (Gamificación)
// ---------------------------------------------------------
async function cargarRankingMozos() {
    try {
        const response = await fetch('/api/analytics/ranking-mozos');
        const datos = await response.json();
        
        const tbody = document.getElementById('cuerpoTablaMozos');
        let html = '';
        
        if (datos.length > 0) {
            datos.forEach((mozo, index) => {
                // Asignación de medallas a los 3 primeros lugares
                let medalla = (index === 0) ? '🥇' : (index === 1) ? '🥈' : (index === 2) ? '🥉' : '🎖️';
                
                html += `
                    <tr>
                        <td style="text-align: center; font-size: 1.4em;">${medalla}</td>
                        <td style="font-weight: bold; color: #ff9b1a;">${mozo.nombreMozo}</td>
                        <td>${mozo.cantidadPedidosAtendidos} mesas</td>
                        <td style="color: #2ecc71; font-weight: bold;">S/ ${mozo.totalDineroGenerado.toFixed(2)}</td>
                    </tr>
                `;
            });
        } else {
            html = `<tr><td colspan="4" style="text-align:center;">Aún no hay ventas registradas</td></tr>`;
        }
        
        tbody.innerHTML = html;
    } catch (error) {
        console.error("Error al cargar Ranking de Mozos", error);
    }
}