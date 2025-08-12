package com.ladyspa.repository;

import com.ladyspa.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
    // Buscar reservas por usuario ID
    List<Reserva> findByUsuarioIdOrderByFechaDescHoraDesc(Long usuarioId);
    
    // Buscar reservas por fecha
    List<Reserva> findByFechaOrderByHora(LocalDate fecha);
    
    // Buscar todas las reservas ordenadas por fecha y hora
    List<Reserva> findAllByOrderByFechaDescHoraDesc();
    
    // Consulta personalizada para obtener reservas con información del usuario
    @Query("SELECT r FROM Reserva r JOIN FETCH r.usuario ORDER BY r.fecha DESC, r.hora DESC")
    List<Reserva> findAllWithUsuario();
    
    // Verificar disponibilidad de hora en una fecha específica
    boolean existsByFechaAndHora(LocalDate fecha, java.time.LocalTime hora);
}
