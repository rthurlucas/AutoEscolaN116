package br.com.senain116.autoescolan116.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(
        @NotNull
        Long id,
        String login,
        boolean ativo,
        Perfil perfil) {
}