package br.com.senain116.autoescolan116.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(
        @NotBlank
        String login,

        @NotBlank
        String senha,

        @NotNull
        Perfil perfil
) {
}