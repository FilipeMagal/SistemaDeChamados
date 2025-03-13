package com.acadepol.controlechamado.controlechamado.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;


@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    ABERTO,
    EM_ANDAMENTO,
    CONCLUIDO ;


}
