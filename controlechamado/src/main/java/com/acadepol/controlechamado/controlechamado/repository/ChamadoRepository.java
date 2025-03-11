package com.acadepol.controlechamado.controlechamado.repository;

import com.acadepol.controlechamado.controlechamado.domain.chamado.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository <Chamado, Long> {
}
