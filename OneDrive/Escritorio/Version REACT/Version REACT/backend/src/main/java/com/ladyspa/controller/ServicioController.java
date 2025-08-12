package com.ladyspa.controller;

import com.ladyspa.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/servicios")
@CrossOrigin(origins = "http://localhost:3000")
public class ServicioController {
    
    /**
     * GET /api/servicios
     * Obtener lista de servicios disponibles
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Map<String, String>>>> obtenerServicios() {
        try {
            // Lista de servicios disponibles (mismos que en la versión JSP)
            List<Map<String, String>> servicios = List.of(
                Map.of(
                    "id", "cejas",
                    "nombre", "Laminado de Cejas",
                    "descripcion", "Cejas perfectas y definidas",
                    "duracion", "60 minutos",
                    "precio", "$80.000",
                    "imagen", "cejas.jpg"
                ),
                Map.of(
                    "id", "manicure",
                    "nombre", "Manicure y Pedicure",
                    "descripcion", "Cuidado y embellecimiento de tus manos y pies",
                    "duracion", "90 minutos",
                    "precio", "$60.000",
                    "imagen", "manicure-pedicure.jpg"
                ),
                Map.of(
                    "id", "pestanas",
                    "nombre", "Lifting de Pestañas",
                    "descripcion", "Luce una mirada perfecta",
                    "duracion", "75 minutos",
                    "precio", "$100.000",
                    "imagen", "pestanas.jpg"
                )
            );
            
            return ResponseEntity.ok(
                ApiResponse.success("Servicios obtenidos exitosamente", servicios)
            );
            
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body(ApiResponse.error("Error al obtener servicios: " + e.getMessage()));
        }
    }
    
    /**
     * GET /api/servicios/{id}
     * Obtener servicio específico por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Map<String, String>>> obtenerServicioPorId(@PathVariable String id) {
        try {
            Map<String, String> servicio = null;
            
            switch (id) {
                case "cejas":
                    servicio = Map.of(
                        "id", "cejas",
                        "nombre", "Laminado de Cejas",
                        "descripcion", "Cejas perfectas y definidas",
                        "duracion", "60 minutos",
                        "precio", "$80.000"
                    );
                    break;
                case "manicure":
                    servicio = Map.of(
                        "id", "manicure",
                        "nombre", "Manicure y Pedicure",
                        "descripcion", "Cuidado y embellecimiento de tus manos y pies",
                        "duracion", "90 minutos",
                        "precio", "$60.000"
                    );
                    break;
                case "pestanas":
                    servicio = Map.of(
                        "id", "pestanas",
                        "nombre", "Lifting de Pestañas",
                        "descripcion", "Luce una mirada perfecta",
                        "duracion", "75 minutos",
                        "precio", "$100.000"
                    );
                    break;
            }
            
            if (servicio == null) {
                return ResponseEntity.status(404)
                    .body(ApiResponse.error("Servicio no encontrado"));
            }
            
            return ResponseEntity.ok(
                ApiResponse.success("Servicio obtenido exitosamente", servicio)
            );
            
        } catch (Exception e) {
            return ResponseEntity.status(500)
                .body(ApiResponse.error("Error al obtener servicio: " + e.getMessage()));
        }
    }
}
