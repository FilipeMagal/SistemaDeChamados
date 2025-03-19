package com.acadepol.controlechamado.controlechamado.domain.user;

import java.util.Date;

public record CadastroDTO(Long matricula, String nome, String email, String senha, TipoUsuario tipoUsuario, String cpf) {
}
