package com.acadepol.controlechamado.controlechamado.repository;

import com.acadepol.controlechamado.controlechamado.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
}
