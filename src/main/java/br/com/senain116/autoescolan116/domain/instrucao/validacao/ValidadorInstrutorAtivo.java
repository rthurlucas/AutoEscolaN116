package br.com.senain116.autoescolan116.domain.instrucao.validacao;

import br.com.senain116.autoescolan116.domain.instrucao.DadosAgendamento;
import br.com.senain116.autoescolan116.domain.instrutor.InstrutorRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ValidadorInstrutorAtivo implements ValidadorAgendamento{
    private final InstrutorRepository instrutorRepository;

    public ValidadorInstrutorAtivo(InstrutorRepository instrutorRepository){
        this.instrutorRepository = instrutorRepository;
    }
    @Override
    public void validar(@NonNull DadosAgendamento dados) {
        boolean instrutorAtivo = instrutorRepository.existsByIdAndAtivoTrue(dados.idInstrutor());
        if (!instrutorAtivo){
            throw new ValidationException("Instrução nao pode ser agendada por instrutor inativo");
        }
    }
}
