package br.com.senain116.autoescolan116.domain.instrucao;

import br.com.senain116.autoescolan116.domain.instrutor.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamento(
        @NotNull
        Long idAluno,
        Long idInstrutor,
        Especialidade especialidade,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
        LocalDateTime data
) {
}
