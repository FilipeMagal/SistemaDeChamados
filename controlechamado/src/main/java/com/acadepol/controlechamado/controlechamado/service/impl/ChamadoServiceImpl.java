package com.acadepol.controlechamado.controlechamado.service.impl;


import com.acadepol.controlechamado.controlechamado.domain.chamado.Chamado;
import com.acadepol.controlechamado.controlechamado.domain.user.Usuario;
import com.acadepol.controlechamado.controlechamado.enums.Setor;
import com.acadepol.controlechamado.controlechamado.enums.Status;
import com.acadepol.controlechamado.controlechamado.repository.ChamadoRepository;
import com.acadepol.controlechamado.controlechamado.repository.UsuarioRepository;
import com.acadepol.controlechamado.controlechamado.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private UsuarioRepository usuarioRepository;

    //Listar todos

    public List <Chamado> findAllChamados(){
        return chamadoRepository.findAll();
    }

    //Mostrar com o ID

    public Chamado findById(Long id){
        Optional <Chamado> ch = chamadoRepository.findById(id);
        return ch.get();
    }


    // Mostrar com o CPF

    @Override
    public Chamado findByUsuarioCpf(String id) {
        Optional <Chamado> chamado = chamadoRepository.findByUsuarioCpf(id);
        return chamado.get();
    }

    // Mostrar com a matrícula

    @Override
    public Usuario findByMatricula(Long matricula) {
        // Aqui estamos buscando o UserDetails, e em seguida, fazemos o cast para Usuario
        UserDetails userDetails = usuarioRepository.findByMatricula(matricula);

        // Verificamos se o resultado encontrado é do tipo Usuario
        if (userDetails instanceof Usuario) {
            return (Usuario) userDetails; // Fazendo o cast para Usuario
        } else {
            throw new RuntimeException("Usuário não encontrado com matrícula: " + matricula);
        }
    }




    // Salvar o chamado

    @Override
    public void save(Long chamadoId, Setor setor, String descricao, Status status, Date dataCriacao) {
        Chamado chamado = new Chamado();

        chamado.setChamadoId(chamadoId);
        chamado.setSetor(setor);
        chamado.setDescricaoServidor(descricao);
        chamado.setStatus(status);

        if (dataCriacao == null) {
            dataCriacao = new Date();  // Data atual
        }
        chamado.setDataCriacao(dataCriacao);  // Definindo a data de criação

        chamadoRepository.save(chamado);
    }

    @Override
    public Chamado saveTec(Usuario usuario, String descricao, Status status, Date dataConclusao) {
        Chamado chamado = new Chamado();

        chamado.setUsuario(usuario);
        chamado.setDescricaoTecnico(descricao);
        chamado.setStatus(status);
        chamado.setDataConclusao(dataConclusao);
        return chamadoRepository.save(chamado);
    }

    @Override
    public Chamado saveAdmin(Usuario usuarioLogado, String descricaoServidor, Setor setor, String descricaoTecnico, Status status, Date dataConclusao) {
        return null;
    }





    //Metodo PUT (Alterar dados)

    public Chamado saveServ(Long chamadoId, String setor, String descricao) {
        Chamado chamado;

        if (chamadoId != null){
            chamado = chamadoRepository.findById(chamadoId).orElseThrow(() -> new RuntimeException("Chamado não encontrado com ID: " + chamadoId));
        }else {
            throw new RuntimeException("Chamado não encontrado com ID: null");
        }

        chamado.setDescricaoServidor(descricao);
        chamado.setSetor(Setor.valueOf(setor));
        return chamadoRepository.save(chamado);
    }

    public void delete (Long id){
        Chamado chamado = new Chamado();
        chamadoRepository.deleteById(id);
    }
}
