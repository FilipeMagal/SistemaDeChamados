package com.acadepol.controlechamado.controlechamado.repository;

import com.acadepol.controlechamado.controlechamado.entity.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository <Permissao, Long> {
}
