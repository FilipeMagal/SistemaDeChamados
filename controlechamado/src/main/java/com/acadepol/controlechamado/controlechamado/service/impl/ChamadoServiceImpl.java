package com.acadepol.controlechamado.controlechamado.service.impl;


import com.acadepol.controlechamado.controlechamado.domain.chamado.Chamado;
import com.acadepol.controlechamado.controlechamado.domain.user.Usuario;
import com.acadepol.controlechamado.controlechamado.enums.Status;
import com.acadepol.controlechamado.controlechamado.repository.ChamadoRepository;
import com.acadepol.controlechamado.controlechamado.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoServiceImpl implements ChamadoService {

    @Autowired
    ChamadoRepository chamadoRepository;

    public List <Chamado> findAllChamados(){
        return chamadoRepository.findAll();
    }
    public Chamado findById(Long id){
        Optional <Chamado> ch = chamadoRepository.findById(id);
        return ch.get();
    }

    //Metodo POST (Registrar dados)

    @Override
    public void save(Usuario usuario, String titulo, String descricao, Status status, Date dataCriacao) {
        Chamado chamado = new Chamado();

        chamado.setUsuario(usuario);
        chamado.setTitulo(titulo);
        chamado.setDescricao(descricao);
        chamado.setStatus(status);
        java.sql.Date sqlDate = new java.sql.Date(dataCriacao.getTime());




        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(chamado.getDataCriacao());

        chamadoRepository.save(chamado);
    }


    public void save1(Long chamadoId,Usuario usuario, String titulo, String descricao, Status status, Date dataConclusao) {
        Chamado chamado;

        if (chamadoId != null){
            chamado = chamadoRepository.findById(chamadoId).orElseThrow(() -> new RuntimeException("Chamado não encontrado com ID: " + chamadoId));
        }else {
            throw new RuntimeException("Chamado não encontrado com ID: null");
        }

        chamado.setUsuario(usuario);
        chamado.setTitulo(titulo);
        chamado.setDescricao(descricao);
        chamado.setStatus(status);


        // Define o fuso horário de Brasília
        ZoneId zonaBrasilia = ZoneId.of("America/Sao_Paulo");

        // Converte a dataConclusao para LocalDate considerando o fuso horário
        LocalDate localDateCompra = dataConclusao.toInstant()
                .atZone(zonaBrasilia) // Aplica o fuso horário
                .toLocalDate(); // Mantém a data sem tempo

        chamado.setDataConclusao((java.sql.Date) dataConclusao);

        chamadoRepository.save(chamado);
    }

    public void delete (Long id){
        Chamado chamado = new Chamado();
        chamadoRepository.deleteById(id);
    }
}
