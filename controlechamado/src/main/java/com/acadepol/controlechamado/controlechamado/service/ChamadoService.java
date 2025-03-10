package com.acadepol.controlechamado.controlechamado.service;

import com.acadepol.controlechamado.controlechamado.entity.Chamado;
import com.acadepol.controlechamado.controlechamado.entity.Usuario;
import com.acadepol.controlechamado.controlechamado.enums.Status;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public interface ChamadoService {

    List<Chamado> findAllChamados();

    Chamado findById(Long id);

    void save(Usuario colaborador, String titulo, String descricao, Status status, Date dataCriacao); // Salvar
    void save1(Long chamadoId, Usuario colaborador, String titulo, String descricao, Status status, Date dataConclusao); // Editar
    void delete(Long id);

}
