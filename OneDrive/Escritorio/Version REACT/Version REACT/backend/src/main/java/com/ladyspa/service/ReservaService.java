package com.ladyspa.service;

import com.ladyspa.model.Reserva;
import com.ladyspa.model.Usuario;
import com.ladyspa.repository.ReservaRepository;
import com.ladyspa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    
    @Autowired
    private ReservaRepository reservaRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    /**
     * Crear nueva reserva
     */
    public Reserva crearReserva(Reserva reserva) throws Exception {
        // Validar que el usuario existe
        Optional<Usuario> usuario = usuarioRepository.findById(reserva.getUsuarioId());
        if (usuario.isEmpty()) {
            throw new Exception("Usuario no encontrado");
        }
        
        // Validaciones de fecha y hora
        if (reserva.getFecha() == null) {
            throw new Exception("La fecha es obligatoria");
        }
        
        if (reserva.getHora() == null) {
            throw new Exception("La hora es obligatoria");
        }
        
        if (reserva.getFecha().isBefore(LocalDate.now())) {
            throw new Exception("No se pueden agendar citas en fechas pasadas");
        }
        
        // Validar disponibilidad de horario
        if (reservaRepository.existsByFechaAndHora(reserva.getFecha(), reserva.getHora())) {
            throw new Exception("El horario ya está ocupado. Por favor seleccione otro.");
        }
        
        // Validar servicio
        if (reserva.getServicio() == null || reserva.getServicio().trim().isEmpty()) {
            throw new Exception("El servicio es obligatorio");
        }
        
        // Validar horarios de trabajo (9:00 AM a 6:00 PM)
        if (reserva.getHora().isBefore(LocalTime.of(9, 0)) || 
            reserva.getHora().isAfter(LocalTime.of(18, 0))) {
            throw new Exception("El horario de atención es de 9:00 AM a 6:00 PM");
        }
        
        // Establecer fecha de creación
        reserva.setFechaCreacion(LocalDate.now());
        
        return reservaRepository.save(reserva);
    }
    
    /**
     * Obtener todas las reservas con información del usuario
     */
    public List<Reserva> obtenerTodasConUsuario() {
        return reservaRepository.findAllWithUsuario();
    }
    
    /**
     * Obtener todas las reservas ordenadas
     */
    public List<Reserva> obtenerTodas() {
        return reservaRepository.findAllByOrderByFechaDescHoraDesc();
    }
    
    /**
     * Obtener reservas por usuario ID
     */
    public List<Reserva> obtenerPorUsuario(Long usuarioId) throws Exception {
        // Validar que el usuario existe
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new Exception("Usuario no encontrado");
        }
        
        return reservaRepository.findByUsuarioIdOrderByFechaDescHoraDesc(usuarioId);
    }
    
    /**
     * Obtener reservas por fecha
     */
    public List<Reserva> obtenerPorFecha(LocalDate fecha) {
        return reservaRepository.findByFechaOrderByHora(fecha);
    }
    
    /**
     * Obtener reserva por ID
     */
    public Reserva obtenerPorId(Long id) throws Exception {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        
        if (reserva.isEmpty()) {
            throw new Exception("Reserva no encontrada");
        }
        
        return reserva.get();
    }
    
    /**
     * Verificar disponibilidad de horario
     */
    public boolean verificarDisponibilidad(LocalDate fecha, LocalTime hora) {
        return !reservaRepository.existsByFechaAndHora(fecha, hora);
    }
    
    /**
     * Obtener horarios disponibles para una fecha
     */
    public List<LocalTime> obtenerHorariosDisponibles(LocalDate fecha) {
        // Horarios de trabajo: 9:00 AM a 6:00 PM (cada hora)
        List<LocalTime> horariosDisponibles = List.of(
            LocalTime.of(9, 0),   // 09:00
            LocalTime.of(10, 0),  // 10:00
            LocalTime.of(11, 0),  // 11:00
            LocalTime.of(12, 0),  // 12:00
            LocalTime.of(14, 0),  // 14:00 (después del almuerzo)
            LocalTime.of(15, 0),  // 15:00
            LocalTime.of(16, 0),  // 16:00
            LocalTime.of(17, 0)   // 17:00
        );
        
        // Filtrar horarios ya ocupados
        return horariosDisponibles.stream()
            .filter(hora -> !reservaRepository.existsByFechaAndHora(fecha, hora))
            .collect(Collectors.toList());
    }
}
