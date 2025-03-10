package com.acadepol.controlechamado.controlechamado.controller;

import com.acadepol.controlechamado.controlechamado.DTO.ChamadoDTO;
import com.acadepol.controlechamado.controlechamado.entity.Chamado;
import com.acadepol.controlechamado.controlechamado.entity.Usuario;
import com.acadepol.controlechamado.controlechamado.enums.Status;
import com.acadepol.controlechamado.controlechamado.repository.UsuarioRepository;
import com.acadepol.controlechamado.controlechamado.service.ChamadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acadepol/chamado")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/chamados")
    public List<Chamado> findAllChamados(){
        return chamadoService.findAllChamados();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity <Chamado> findById(@PathVariable Long id){
        Chamado obj = chamadoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registroChamado(@RequestBody ChamadoDTO chamadoDTO) {
        // Encontrar o colaborador e o técnico usando os IDs
        Usuario colaborador = usuarioRepository.findById(chamadoDTO.getColaboradorId())
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));

        // Converter o código de status para o enum Status
        Status status = Status.doValor(chamadoDTO.getStatus());

        // Criar o chamado
        Chamado chamado = new Chamado();
        chamado.setColaborador(colaborador);
        chamado.setTitulo(chamadoDTO.getTitulo());
        chamado.setDescricao(chamadoDTO.getDescricao());
        chamado.setStatus(status);
        chamado.setDataCriacao(chamadoDTO.getDataCriacao());


        chamadoService.save(chamado.getColaborador(),
                chamado.getTitulo(),
                chamado.getDescricao(),
                chamado.getStatus(),
                chamado.getDataCriacao());

        return ResponseEntity.ok("Chamado registrado com sucesso!");
    }


    @PutMapping("/registro/{id}")
    public void alterarChamado(@RequestBody Chamado chamado, @PathVariable Long id){
        chamadoService.save1(
                chamado.getChamadoId(),
                chamado.getColaborador(),
                chamado.getTitulo(),
                chamado.getDescricao(),
                chamado.getStatus(),
                chamado.getDataConclusao()
        );
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarChamado(@PathVariable Long id){
        chamadoService.delete(id);
    }
}
