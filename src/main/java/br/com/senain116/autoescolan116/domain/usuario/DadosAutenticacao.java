package br.com.senain116.autoescolan116.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(
        @NotBlank
        //@Email Não quero que seja email
        //@Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}") Não quero que seja cpf
        String login,

        @NotBlank
        //@Pattern(regexp = ".{8,}") Não quero que tenha esse padrão
        String senha) {
}