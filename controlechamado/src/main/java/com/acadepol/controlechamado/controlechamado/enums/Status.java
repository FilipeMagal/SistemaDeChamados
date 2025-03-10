package com.acadepol.controlechamado.controlechamado.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;


@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    ABERTO("A", "Aberto"),
    EM_ANDAMENTO("E", "Em andamento"),
    CONCLUIDO ("C", "Concluido");

    private String codigo;
    private String descricao;

    private Status (String codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @JsonCreator
    public static Status doValor (String codigo){
        if (codigo.equals("A")){
            return ABERTO;
        } else if (codigo.equals("E")) {
            return EM_ANDAMENTO;
        } else if (codigo.equals("C")) {
            return CONCLUIDO;
        }else{
            return null;
        }
    }
}
