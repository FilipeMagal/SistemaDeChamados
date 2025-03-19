package com.acadepol.controlechamado.controlechamado.controller;

import com.acadepol.controlechamado.controlechamado.domain.user.AuthenticationDTO;
import com.acadepol.controlechamado.controlechamado.domain.user.CadastroDTO;
import com.acadepol.controlechamado.controlechamado.domain.user.LoginResponseDTO;
import com.acadepol.controlechamado.controlechamado.domain.user.Usuario;
import com.acadepol.controlechamado.controlechamado.infra.security.TokenService;
import com.acadepol.controlechamado.controlechamado.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/acadepol/auth")
public class AuthenticationControler {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dados) {
       var loginSenha = new UsernamePasswordAuthenticationToken(dados.matricula(), dados.senha());
       var auth = this.authenticationManager.authenticate(loginSenha);

       var token = tokenService.generateToken((Usuario) auth.getPrincipal());

       return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastro(@RequestBody @Valid CadastroDTO dados){
        if (this.usuarioRepository.findByMatricula(dados.matricula()) != null) return ResponseEntity.badRequest().build();
        String senhaEncriptada = new BCryptPasswordEncoder().encode(dados.senha());
        Usuario novoUsuario = new Usuario(dados.matricula(), dados.nome(), dados.email(), senhaEncriptada,dados.tipoUsuario(), dados.cpf());

        this.usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok().build();
    }
}
