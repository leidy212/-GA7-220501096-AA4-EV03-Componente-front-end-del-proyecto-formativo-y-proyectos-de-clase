package com.ladyspa.controller;

import com.ladyspa.dto.ApiResponse;
import com.ladyspa.dto.LoginRequest;
import com.ladyspa.model.Usuario;
import com.ladyspa.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    /**
     * POST /api/auth/login
     * Iniciar sesión
     */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Usuario>> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Usuario usuario = usuarioService.autenticarUsuario(
                loginRequest.getEmail(), 
                loginRequest.getPassword()
            );
            
            return ResponseEntity.ok(
                ApiResponse.success("Login exitoso", usuario)
            );
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.error("Error de autenticación: " + e.getMessage()));
        }
    }
    
    /**
     * POST /api/auth/register
     * Registrar nuevo usuario
     */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Usuario>> register(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario nuevoUsuario = usuarioService.registrarUsuario(usuario);
            
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Usuario registrado exitosamente", nuevoUsuario));
                
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.error("Error en el registro: " + e.getMessage()));
        }
    }
    
    /**
     * GET /api/auth/verify-email/{email}
     * Verificar si un email ya existe
     */
    @GetMapping("/verify-email/{email}")
    public ResponseEntity<ApiResponse<Boolean>> verifyEmail(@PathVariable String email) {
        try {
            boolean existe = usuarioService.existeEmail(email);
            
            return ResponseEntity.ok(
                ApiResponse.success(
                    existe ? "Email ya existe" : "Email disponible", 
                    existe
                )
            );
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.error("Error al verificar email: " + e.getMessage()));
        }
    }
}
