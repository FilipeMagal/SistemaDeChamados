package com.acadepol.controlechamado.controlechamado.repository;

import com.acadepol.controlechamado.controlechamado.domain.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    UserDetails findByMatricula(Long matricula);

}
