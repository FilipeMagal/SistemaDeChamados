package com.acadepol.controlechamado.controlechamado.enums;

public enum Setor {
    CAD("CAD"),
    CDE("CDE"),
    SECRETARIA("SECRETÁRIA"),
    ASTEC("ASTEC"),
    CEO("CEO"),
    COMPRAS("COMPRAS"),
    TITULACAO("TITULAÇÃO"),
    BIBLIOTECA("BIBLIOTECA"),
    APOIO_CARTORIO("APOIO/CARTÓRIO"),
    DIRETOR("DIRETOR"),
    DIRETOR_ADJ("DIRETOR ADJ"),
    RECEPCAO("RECEPÇÃO"),
    PLANTAO("PLANTÃO");

    private final String descricao;

    // Construtor para atribuir a descrição
    Setor(String descricao) {
        this.descricao = descricao;
    }

    // Getter para obter a descrição do setor
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    // Metodo que pode ser útil para buscar um Setor pelo nome
    public static Setor fromString(String descricao) {
        for (Setor setor : Setor.values()) {
            if (setor.getDescricao().equalsIgnoreCase(descricao)) {
                return setor;
            }
        }
        throw new IllegalArgumentException("Setor não encontrado: " + descricao);
    }
}