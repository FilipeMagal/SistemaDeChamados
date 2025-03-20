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

import java.util.Date;
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
    public ResponseEntity<String> registroChamado(@RequestBody ChamadoDTO chamadoDTO,
                                                  @AuthenticationPrincipal Usuario usuarioLogado) {

        Usuario usuario = usuarioRepository.findById(chamadoDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Servidor não encontrado"));

        Chamado chamado = new Chamado();
        // Verifica se o usuário autenticado é um admin ou servidor
         if (usuario.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)) {

             chamado.setUsuario(usuarioLogado);  // Associando o usuário logado ao chamado
             chamado.setSetor(chamadoDTO.getSetor());
             chamado.setDescricaoServidor(chamadoDTO.getDescricao());
             chamado.setStatus(Status.ABERTO);
             chamado.setDataCriacao(new Date());  // Definindo a data de criação automaticamente
             chamado.setDescricaoTecnico(chamadoDTO.getDescricaoTecnico());

             chamadoService.save(chamado.getChamadoId(), chamado.getSetor(), chamado.getDescricaoServidor(),
                     chamado.getStatus(), chamado.getDataCriacao());

             return ResponseEntity.ok("Chamado registrado com sucesso!");
        } else if (usuario.getTipoUsuario().equals(TipoUsuario.SERVIDOR)) {

             chamado.setUsuario(usuarioLogado);  // Associando o usuário logado ao chamado
             chamado.setSetor(chamadoDTO.getSetor());
             chamado.setDescricaoServidor(chamadoDTO.getDescricao());
             chamado.setStatus(Status.ABERTO);
             chamado.setDataCriacao(new Date());  // Definindo a data de criação automaticamente

             chamadoService.save(chamado.getChamadoId(), chamado.getSetor(), chamado.getDescricaoServidor(),
                     chamado.getStatus(), chamado.getDataCriacao());

             return ResponseEntity.ok("Chamado registrado com sucesso!");
         }
             return ResponseEntity.badRequest().body("Usuário não autorizado para registrar chamado.");
    }














    @PutMapping("/registro/{id}")
    public void alterarChamado(@RequestBody Chamado chamado, @PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) {

        // Verificando o tipo de usuário (técnico,administrador ou servidor)
        if (usuarioLogado.getTipoUsuario().equals(TipoUsuario.TECNICO)) {
            chamadoService.saveTec(
                    usuarioLogado,
                    chamado.getDescricaoTecnico(),
                    chamado.getStatus(),
                    chamado.getDataConclusao());
        } else if (usuarioLogado.getTipoUsuario().equals(TipoUsuario.ADMINISTRADOR)) {
            chamadoService.saveAdmin(
                    usuarioLogado,
                    chamado.getDescricaoServidor(),
                    chamado.getSetor(),
                    chamado.getDescricaoTecnico(),
                    chamado.getStatus(),
                    chamado.getDataConclusao());
        }
        chamadoService.saveServ(
                chamado.getChamadoId(),
                String.valueOf(chamado.getSetor()),
                chamado.getDescricaoServidor());
    }





    @DeleteMapping("/deletar/{id}")
    public void deletarChamado(@PathVariable Long id){
        chamadoService.delete(id);
    }
}
