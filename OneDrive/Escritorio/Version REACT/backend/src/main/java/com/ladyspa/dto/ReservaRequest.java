package com.ladyspa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaRequest {
    
    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;
    
    @NotBlank(message = "El servicio es obligatorio")
    private String servicio;
    
    @NotNull(message = "La fecha es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    
    @NotNull(message = "La hora es obligatoria")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime hora;
    
    // Constructores
    public ReservaRequest() {}
    
    public ReservaRequest(Long usuarioId, String servicio, LocalDate fecha, LocalTime hora) {
        this.usuarioId = usuarioId;
        this.servicio = servicio;
        this.fecha = fecha;
        this.hora = hora;
    }
    
    // Getters y Setters
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    
    public String getServicio() { return servicio; }
    public void setServicio(String servicio) { this.servicio = servicio; }
    
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
}
