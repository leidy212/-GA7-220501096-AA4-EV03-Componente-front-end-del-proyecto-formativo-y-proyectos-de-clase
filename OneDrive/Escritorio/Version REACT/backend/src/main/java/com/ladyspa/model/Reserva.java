package com.ladyspa.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservas")
public class Reserva {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "El ID del usuario es obligatorio")
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;
    
    @NotBlank(message = "El servicio es obligatorio")
    @Column(nullable = false, length = 100)
    private String servicio;
    
    @NotNull(message = "La fecha es obligatoria")
    @Column(nullable = false)
    private LocalDate fecha;
    
    @NotNull(message = "La hora es obligatoria")
    @Column(nullable = false)
    private LocalTime hora;
    
    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;
    
    // Relación con Usuario (opcional para consultas)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    private Usuario usuario;
    
    // Constructores
    public Reserva() {
        this.fechaCreacion = LocalDate.now();
    }
    
    public Reserva(Long usuarioId, String servicio, LocalDate fecha, LocalTime hora) {
        this.usuarioId = usuarioId;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = hora;
        this.fechaCreacion = LocalDate.now();
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    
    public String getServicio() { return servicio; }
    public void setServicio(String servicio) { this.servicio = servicio; }
    
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
