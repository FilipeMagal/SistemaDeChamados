package com.acadepol.controlechamado.controlechamado.controller;

import com.acadepol.controlechamado.controlechamado.domain.user.Usuario;
import com.acadepol.controlechamado.controlechamado.domain.user.CadastroDTO;
import com.acadepol.controlechamado.controlechamado.repository.UsuarioRepository;
import com.acadepol.controlechamado.controlechamado.service.UsuarioService;
import com.acadepol.controlechamado.controlechamado.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/acadepol/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody CadastroDTO cadastroDTO) {
        // Verifica se o CPF já está registrado
        if (usuarioRepository.existsById(cadastroDTO.matricula())) {
            return ResponseEntity.badRequest().body("Usuário já existe com a matrícula fornecida");
        }

        // Cria o objeto Usuario
        Usuario usuario = new Usuario();
        usuario.setMatricula(cadastroDTO.matricula());
        usuario.setNome(cadastroDTO.nome());
        usuario.setEmail(cadastroDTO.email());
        usuario.setSenha(cadastroDTO.senha());
        usuario.setTipoUsuario(cadastroDTO.tipoUsuario());
        usuario.setCpf(cadastroDTO.cpf());

        // Salva o usuário no banco
        usuarioService.registrarUsuario(usuario);

        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }
}