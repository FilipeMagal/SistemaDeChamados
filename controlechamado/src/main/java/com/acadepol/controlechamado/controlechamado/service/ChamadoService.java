package com.acadepol.controlechamado.controlechamado.service;

import com.acadepol.controlechamado.controlechamado.domain.chamado.Chamado;
import com.acadepol.controlechamado.controlechamado.domain.user.Usuario;
import com.acadepol.controlechamado.controlechamado.enums.Setor;
import com.acadepol.controlechamado.controlechamado.enums.Status;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface ChamadoService {

    List<Chamado> findAllChamados();

    Chamado findById(Long id);
    Chamado findByUsuarioCpf(String id);

    Usuario findByMatricula(Long matricula);

    Chamado saveServ(Long chamadoId, String setor, String descricao); // Editar

    void save(Long chamadoId, Setor setor, String descricao, Status status, Date dataCriacao);

    Chamado saveTec(Usuario usuarioLogado, String descricao, Status status, Date dataConclusao); // Editar


    Chamado saveAdmin(Usuario usuarioLogado, String descricaoServidor, Setor setor, String descricaoTecnico, Status status, Date dataConclusao);

    void delete(Long id);
}
