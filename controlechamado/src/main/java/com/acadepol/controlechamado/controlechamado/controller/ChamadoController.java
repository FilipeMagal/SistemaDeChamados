package com.acadepol.controlechamado.controlechamado.controller;

import com.acadepol.controlechamado.controlechamado.DTO.ChamadoDTO;
import com.acadepol.controlechamado.controlechamado.domain.chamado.Chamado;
import com.acadepol.controlechamado.controlechamado.domain.user.TipoUsuario;
import com.acadepol.controlechamado.controlechamado.domain.user.Usuario;
import com.acadepol.controlechamado.controlechamado.enums.Setor;
import com.acadepol.controlechamado.controlechamado.enums.Status;
import com.acadepol.controlechamado.controlechamado.repository.UsuarioRepository;
import com.acadepol.controlechamado.controlechamado.service.ChamadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping(value = "/cpf/{id}")
    public ResponseEntity <Chamado> findById(@PathVariable String cpf){
        Chamado obj = chamadoService.findByUsuarioCpf(cpf);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/usuario/{matricula}")
    public ResponseEntity<Usuario> findByMatricula(@PathVariable Long matricula) {
        Usuario usuario = chamadoService.findByMatricula(matricula);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registroChamado(@RequestBody ChamadoDTO chamadoDTO) {
        Usuario usuario = usuarioRepository.findById(chamadoDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));



        Chamado chamado = new Chamado();
        chamado.setUsuario(usuario);
        chamado.setSetor(chamadoDTO.getSetor());
        chamado.setDescricaoServidor(chamadoDTO.getDescricao());

        chamado.setStatus(Status.ABERTO);
        chamado.setDataCriacao(chamadoDTO.getDataCriacao());

        chamadoService.save(chamado.getChamadoId(), chamado.getSetor(), chamado.getDescricaoServidor(),
                chamado.getStatus(), chamado.getDataCriacao());

        return ResponseEntity.ok("Chamado registrado com sucesso!");
    }

    @PutMapping("/registro/{id}")
    public void alterarChamado(@RequestBody Chamado chamado, @PathVariable Long id) {
        chamadoService.save1(
                chamado.getChamadoId(),
                chamado.getUsuario(),
                chamado.getSetor(),
                chamado.getDescricaoServidor(),
                chamado.getStatus(),
                chamado.getDataConclusao());
    }

    @PutMapping("/descricao-tecnico/{id}")
    public ResponseEntity<Chamado> editarDescricaoTecnico(@PathVariable Long id,
                                                          @RequestBody String descricaoTecnico,
                                                          @AuthenticationPrincipal Usuario usuario) {
        Chamado chamado = chamadoService.findById(id);

        // Verifique se o usuário autenticado é um técnico
        if (!usuario.getTipoUsuario().equals(TipoUsuario.TECNICO) && !usuario.getTipoUsuario().equals(TipoUsuario.TECN)) {
            return ResponseEntity.status(403).body(null); // Forbidden
        } else if (!usuario.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR) && !usuario.getTipoUsuario().equals(TipoUsuario.ADMIN)) {
            return ResponseEntity.status(403).body(null);
        }

        chamado.setDescricaoTecnico(descricaoTecnico);
        chamadoService.save1(
                chamado.getChamadoId(),
                chamado.getUsuario(),
                chamado.getSetor(),
                chamado.getDescricaoServidor(),
                chamado.getStatus(),
                chamado.getDataConclusao());

        return ResponseEntity.ok(chamado);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Chamado> atualizarStatus(@PathVariable Long id,
                                                   @RequestBody String status,
                                                   @AuthenticationPrincipal Usuario usuario) {
        Chamado chamado = chamadoService.findById(id);

        // Verifique se o usuário autenticado é um técnico
        if (!usuario.getTipoUsuario().equals(TipoUsuario.TECNICO) && !usuario.getTipoUsuario().equals(TipoUsuario.TECN) && !usuario.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR) && !usuario.getTipoUsuario().equals(TipoUsuario.ADMIN)) {
            return ResponseEntity.status(403).body(null); // Forbidden
        }

        // Atualize o status do chamado
        chamadoService.save1(
                chamado.getChamadoId(),
                chamado.getUsuario(),
                chamado.getSetor(),
                chamado.getDescricaoServidor(),
                chamado.getStatus(),
                chamado.getDataConclusao());

        return ResponseEntity.ok(chamado);
    }


    @DeleteMapping("/deletar/{id}")
    public void deletarChamado(@PathVariable Long id){
        chamadoService.delete(id);
    }
}
