package br.com.senain116.autoescolan116.domain.instrucao;
import br.com.senain116.autoescolan116.domain.instrutor.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosDetalhamentoAgendamento(
        Long id,
        String aluno,
        String instrutor,
        Especialidade especialidade,

        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
        LocalDateTime data) {
    public DadosDetalhamentoAgendamento(Instrucao instrucao){
        this(
                instrucao.getId(),
                instrucao.getAluno().getNome(),
                instrucao.getInstrutor().getNome(),
                instrucao.getInstrutor().getEspecialidade(),
                instrucao.getDatahora()
        );
    }
}
