package com.ladyspa.service;

import com.ladyspa.model.Usuario;
import com.ladyspa.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    /**
     * Registrar un nuevo usuario
     */
    public Usuario registrarUsuario(Usuario usuario) throws Exception {
        // Validar que el email no exista
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new Exception("El email ya está registrado");
        }
        
        // Validaciones adicionales
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre es obligatorio");
        }
        
        if (usuario.getPassword() == null || usuario.getPassword().length() < 6) {
            throw new Exception("La contraseña debe tener al menos 6 caracteres");
        }
        
        // Guardar usuario
        return usuarioRepository.save(usuario);
    }
    
    /**
     * Autenticar usuario (login)
     */
    public Usuario autenticarUsuario(String email, String password) throws Exception {
        if (email == null || email.trim().isEmpty()) {
            throw new Exception("El email es obligatorio");
        }
        
        if (password == null || password.trim().isEmpty()) {
            throw new Exception("La contraseña es obligatoria");
        }
        
        Optional<Usuario> usuario = usuarioRepository.findByEmailAndPassword(email, password);
        
        if (usuario.isEmpty()) {
            throw new Exception("Email o contraseña incorrectos");
        }
        
        return usuario.get();
    }
    
    /**
     * Buscar usuario por email
     */
    public Usuario buscarPorEmail(String email) throws Exception {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        
        if (usuario.isEmpty()) {
            throw new Exception("Usuario no encontrado");
        }
        
        return usuario.get();
    }
    
    /**
     * Obtener usuario por ID
     */
    public Usuario obtenerPorId(Long id) throws Exception {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        
        if (usuario.isEmpty()) {
            throw new Exception("Usuario no encontrado");
        }
        
        return usuario.get();
    }
    
    /**
     * Listar todos los usuarios
     */
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
    
    /**
     * Verificar si existe un email
     */
    public boolean existeEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}
