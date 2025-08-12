package com.ladyspa.repository;

import com.ladyspa.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Buscar usuario por email
    Optional<Usuario> findByEmail(String email);
    
    // Verificar si existe un usuario con el email
    boolean existsByEmail(String email);
    
    // Buscar usuario por email y password (para login)
    Optional<Usuario> findByEmailAndPassword(String email, String password);
}
