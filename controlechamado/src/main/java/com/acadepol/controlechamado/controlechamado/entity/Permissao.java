package com.acadepol.controlechamado.controlechamado.entity;

import com.acadepol.controlechamado.controlechamado.enums.PermissaoTipo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "permissoes")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissaoId;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private PermissaoTipo permissoesTipo;


}
