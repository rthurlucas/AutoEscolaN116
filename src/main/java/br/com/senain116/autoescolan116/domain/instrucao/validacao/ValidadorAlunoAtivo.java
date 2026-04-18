package br.com.senain116.autoescolan116.domain.instrucao.validacao;

import br.com.senain116.autoescolan116.domain.aluno.AlunoRepository;
import br.com.senain116.autoescolan116.domain.instrucao.DadosAgendamento;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAlunoAtivo implements ValidadorAgendamento{
    private final AlunoRepository alunoRepository;

    public ValidadorAlunoAtivo(AlunoRepository alunoRepository){
        this.alunoRepository = alunoRepository;
    }

    @Override
    public void validar(DadosAgendamento dados) {
    boolean alunoAtivo = alunoRepository.existsByIdAndAtivoTrue(dados.idAluno());
        if (!alunoAtivo) {
            throw new ValidationException("Instrução não pode ser agendada para aluno inativo");
        }
    }
}
