package com.acadepol.controlechamado.controlechamado.service.impl;

import com.acadepol.controlechamado.controlechamado.domain.user.Usuario;
import com.acadepol.controlechamado.controlechamado.repository.UsuarioRepository;
import com.acadepol.controlechamado.controlechamado.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void registrarUsuario(Usuario usuario) {
        // Verifica se o usuário já existe
        if (usuarioRepository.existsById(usuario.getMatricula())) {
            throw new RuntimeException("Usuário já cadastrado");
        }


        // Salva o novo usuário
        usuarioRepository.save(usuario);
    }
}