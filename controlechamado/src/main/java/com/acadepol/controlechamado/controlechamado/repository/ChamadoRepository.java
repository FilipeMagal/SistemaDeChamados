package com.acadepol.controlechamado.controlechamado.repository;

import com.acadepol.controlechamado.controlechamado.domain.chamado.Chamado;
import com.acadepol.controlechamado.controlechamado.domain.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChamadoRepository extends JpaRepository <Chamado, Long> {
    Optional<Chamado> findByUsuarioCpf(String cpf);

}
