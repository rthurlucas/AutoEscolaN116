package br.com.senain116.autoescolan116.domain.aluno;

import br.com.senain116.autoescolan116.domain.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroAluno(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}")
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{9,11}")
        String cpf,

        @Valid
        DadosEndereco endereco) {
}