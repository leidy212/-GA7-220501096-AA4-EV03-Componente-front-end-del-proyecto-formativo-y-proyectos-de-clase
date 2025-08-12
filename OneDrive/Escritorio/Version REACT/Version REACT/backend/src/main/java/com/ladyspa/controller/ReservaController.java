package com.ladyspa.controller;

import com.ladyspa.dto.ApiResponse;
import com.ladyspa.dto.ReservaRequest;
import com.ladyspa.model.Reserva;
import com.ladyspa.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "http://localhost:3000")
public class ReservaController {
    
    @Autowired
    private ReservaService reservaService;
    
    /**
     * GET /api/reservas
     * Obtener todas las reservas
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Reserva>>> obtenerTodas() {
        try {
            List<Reserva> reservas = reservaService.obtenerTodasConUsuario();
            
            return ResponseEntity.ok(
                ApiResponse.success("Reservas obtenidas exitosamente", reservas)
            );
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Error al obtener reservas: " + e.getMessage()));
        }
    }
    
    /**
     * POST /api/reservas
     * Crear nueva reserva
     */
    @PostMapping
    public ResponseEntity<ApiResponse<Reserva>> crearReserva(@Valid @RequestBody ReservaRequest reservaRequest) {
        try {
            // Convertir DTO a entidad
            Reserva reserva = new Reserva(
                reservaRequest.getUsuarioId(),
                reservaRequest.getServicio(),
                reservaRequest.getFecha(),
                reservaRequest.getHora()
            );
            
            Reserva nuevaReserva = reservaService.crearReserva(reserva);
            
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Reserva creada exitosamente", nuevaReserva));
                
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Error al crear reserva: " + e.getMessage()));
        }
    }
    
    /**
     * GET /api/reservas/usuario/{usuarioId}
     * Obtener reservas por usuario
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ApiResponse<List<Reserva>>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        try {
            List<Reserva> reservas = reservaService.obtenerPorUsuario(usuarioId);
            
            return ResponseEntity.ok(
                ApiResponse.success("Reservas del usuario obtenidas exitosamente", reservas)
            );
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Error al obtener reservas: " + e.getMessage()));
        }
    }
    
    /**
     * GET /api/reservas/{id}
     * Obtener reserva por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Reserva>> obtenerPorId(@PathVariable Long id) {
        try {
            Reserva reserva = reservaService.obtenerPorId(id);
            
            return ResponseEntity.ok(
                ApiResponse.success("Reserva obtenida exitosamente", reserva)
            );
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Error al obtener reserva: " + e.getMessage()));
        }
    }
    
    /**
     * GET /api/reservas/fecha/{fecha}
     * Obtener reservas por fecha
     */
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<ApiResponse<List<Reserva>>> obtenerPorFecha(@PathVariable String fecha) {
        try {
            LocalDate fechaReserva = LocalDate.parse(fecha);
            List<Reserva> reservas = reservaService.obtenerPorFecha(fechaReserva);
            
            return ResponseEntity.ok(
                ApiResponse.success("Reservas de la fecha obtenidas exitosamente", reservas)
            );
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Error al obtener reservas por fecha: " + e.getMessage()));
        }
    }
    
    /**
     * GET /api/reservas/disponibilidad/{fecha}
     * Obtener horarios disponibles para una fecha
     */
    @GetMapping("/disponibilidad/{fecha}")
    public ResponseEntity<ApiResponse<List<String>>> obtenerHorariosDisponibles(@PathVariable String fecha) {
        try {
            LocalDate fechaReserva = LocalDate.parse(fecha);
            List<LocalTime> horariosDisponibles = reservaService.obtenerHorariosDisponibles(fechaReserva);
            
            // Convertir LocalTime a String format HH:mm
            List<String> horariosFormateados = horariosDisponibles.stream()
                .map(hora -> hora.toString())
                .collect(java.util.stream.Collectors.toList());
            
            return ResponseEntity.ok(
                ApiResponse.success("Horarios disponibles obtenidos exitosamente", horariosFormateados)
            );
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Error al obtener horarios disponibles: " + e.getMessage()));
        }
    }
}
