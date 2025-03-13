package com.acadepol.controlechamado.controlechamado.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @Column(name = "id_matricula")
    private Long matricula;

    private String nome;

    private String cpf;

    private String email;

    private String senha;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Column(name = "data_criacao")
    private Date dataCriacao;

    public Usuario(Long matricula, String senha, TipoUsuario tipoUsuario){
        this.cpf = cpf;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.tipoUsuario == TipoUsuario.TECNICO && this.tipoUsuario == TipoUsuario.TECN) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_TECN"),
                    new SimpleGrantedAuthority("ROLE_SERV"));
        } else if (this.tipoUsuario == TipoUsuario.SERVIDOR && this.tipoUsuario == TipoUsuario.SERV) {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_SERV"));
        } else {
            return List.of(
                    new SimpleGrantedAuthority("ROLE_SERV"),
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_TECN"));
        }
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
