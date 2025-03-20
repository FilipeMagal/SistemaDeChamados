package com.acadepol.controlechamado.controlechamado.DTO;

import com.acadepol.controlechamado.controlechamado.enums.Setor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class ChamadoDTO {

    private Long usuarioId;
    private Long tecnicoId;
    private Setor setor;
    private String descricao;
    private String status;  // Este é o código do Status, como "A", "E", "C"
    private Date dataCriacao;
    private String descricaoTecnico;

}
