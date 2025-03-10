package com.acadepol.controlechamado.controlechamado.entity;
import com.acadepol.controlechamado.controlechamado.enums.StatusNotificacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "notificacoes")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                      // ID da notificação (auto increment)

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;               // ID do usuário para quem a notificação foi enviada

    @Column(name = "tipo_notificacao", nullable = false)
    private String tipoNotificacao;       // Tipo da notificação (ex: Chamado Concluído)

    @Column(name = "mensagem", nullable = false)
    private String mensagem;              // Corpo da notificação (mensagem a ser enviada)

    @Column(name = "data_envio", nullable = false)
    private LocalDateTime dataEnvio;      // Data e hora que a notificação foi enviada

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusNotificacao status;     // Status da notificação (ENVIADA, FALHOU, PENDENTE)

    @Column(name = "token_dispositivo")
    private String tokenDispositivo;      // Token do dispositivo (se necessário para reenvio)

    @Column(name = "foi_lida", nullable = false)
    private Boolean foiLida = false;      // Se a notificação foi lida

    @Column(name = "data_leitura")
    private LocalDateTime dataLeitura;    // Quando a notificação foi lida

    @Column(name = "dados_adicionais")
    private String dadosAdicionais;       // Outros dados adicionais em formato JSON (opcional)


}
