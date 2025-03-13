package com.acadepol.controlechamado.controlechamado.domain.chamado;

import com.acadepol.controlechamado.controlechamado.domain.user.Usuario;
import com.acadepol.controlechamado.controlechamado.enums.Setor;
import com.acadepol.controlechamado.controlechamado.enums.Status;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "chamados")
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chamadoId;

    // Relacionamento com o usuário que criou o chamado (usuário solicitante)
    @ManyToOne
    @JoinColumn(name = "id_matricula", referencedColumnName = "id_matricula")
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    private Setor setor;

    private String descricaoServidor;

    private String descricaoTecnico;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ABERTO;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "data_conclusao")
    private Date dataConclusao;

    // Relacionamento com o técnico responsável pelo chamado
    @ManyToOne
    @JoinColumn(name = "tecnico_id", referencedColumnName = "id_matricula")
    private Usuario tecnico;

}
