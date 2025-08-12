package com.ladyspa.controller;

import com.ladyspa.dto.ApiResponse;
import com.ladyspa.model.Usuario;
import com.ladyspa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    /**
     * GET /api/usuarios
     * Obtener todos los usuarios
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<Usuario>>> obtenerTodos() {
        try {
            List<Usuario> usuarios = usuarioService.listarTodos();
            
            return ResponseEntity.ok(
                ApiResponse.success("Usuarios obtenidos exitosamente", usuarios)
            );
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Error al obtener usuarios: " + e.getMessage()));
        }
    }
    
    /**
     * GET /api/usuarios/{id}
     * Obtener usuario por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Usuario>> obtenerPorId(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.obtenerPorId(id);
            
            return ResponseEntity.ok(
                ApiResponse.success("Usuario obtenido exitosamente", usuario)
            );
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Error al obtener usuario: " + e.getMessage()));
        }
    }
    
    /**
     * GET /api/usuarios/email/{email}
     * Obtener usuario por email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<Usuario>> obtenerPorEmail(@PathVariable String email) {
        try {
            Usuario usuario = usuarioService.buscarPorEmail(email);
            
            return ResponseEntity.ok(
                ApiResponse.success("Usuario obtenido exitosamente", usuario)
            );
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.error("Error al obtener usuario: " + e.getMessage()));
        }
    }
}
