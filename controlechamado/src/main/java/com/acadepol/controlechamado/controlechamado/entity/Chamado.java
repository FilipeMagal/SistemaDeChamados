package com.acadepol.controlechamado.controlechamado.entity;

import com.acadepol.controlechamado.controlechamado.enums.Status;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.annotation.processing.Generated;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "chamados")
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chamadoId;

    @ManyToOne
    @JoinColumn(name = "colaborador_id")
    private Usuario colaborador;

    private String titulo;

    private String descricao;

    //@Column(name = "status", columnDefinition = "ENUM('ABERTO', 'EM_ANDAMENTO', 'CONCLUIDO')")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    @Column(name = "data_conclusao")
    private Date dataConclusao;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Usuario tecnico;
}