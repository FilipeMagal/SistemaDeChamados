package com.acadepol.controlechamado.controlechamado.service.impl;

import com.acadepol.controlechamado.controlechamado.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutorizationServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
        return usuarioRepository.findByMatricula(Long.valueOf(matricula));
    }


}
