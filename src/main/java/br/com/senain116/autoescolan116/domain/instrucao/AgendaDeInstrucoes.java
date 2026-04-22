package br.com.senain116.autoescolan116.domain.instrucao;

import br.com.senain116.autoescolan116.domain.aluno.Aluno;
import br.com.senain116.autoescolan116.domain.aluno.AlunoNotFoundException;
import br.com.senain116.autoescolan116.domain.aluno.AlunoRepository;
import br.com.senain116.autoescolan116.domain.instrucao.validacao.DadosMotivoCancelamento;
import br.com.senain116.autoescolan116.domain.instrucao.validacao.ValidadorAgendamento;
import br.com.senain116.autoescolan116.domain.instrutor.Especialidade;
import br.com.senain116.autoescolan116.domain.instrutor.Instrutor;
import br.com.senain116.autoescolan116.domain.instrutor.InstrutorNotFoundException;
import br.com.senain116.autoescolan116.domain.instrutor.InstrutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AgendaDeInstrucoes {
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private InstrutorRepository instrutorRepository;
    @Autowired
    private InstrucaoRepository repository;
    @Autowired
    private List<ValidadorAgendamento> validadoresAgendamento;

    @Transactional
    public DadosDetalhamentoAgendamento agendar(DadosAgendamento dados) {
        if (!alunoRepository.existsById(dados.idAluno())){
            throw new AlunoNotFoundException("Aluno nao encontrado");
        }
        if (dados.idInstrutor() != null && !instrutorRepository.existsById(dados.idInstrutor())){
            throw new InstrutorNotFoundException("Instrutor nao encontrado");
        }

        //Validação
        validadoresAgendamento.forEach(v -> v.validar(dados));

        Aluno aluno = alunoRepository.getReferenceById(dados.idAluno());
        Instrutor instrutor = escolherInstrutor(dados);
        Instrucao instrucao = new Instrucao(
                null,
                aluno,
                instrutor,
                dados.data()
        );
        repository.save(instrucao);
        return new DadosDetalhamentoAgendamento(instrucao);
    }

    private Instrutor escolherInstrutor(DadosAgendamento dados) {
        if (dados.idInstrutor() != null){
        return instrutorRepository.getReferenceById(dados.idInstrutor());
        }
        if (dados.especialidade() != null){
        throw new DadosIncompletosException("Especialidade é obrigatoria quando o Instrutor nao for informado");
        }
        return repository.escolherInstrutorAleatorioDisponivel(
                dados.especialidade(),
                dados.data()
        );
    }

    @Transactional
    public DadosCancelamentoAgendamento cancelarInstrucao(DadosCancelamentoAgendamento dados) {
        if (dados.motivoCancelamento() != null){
            throw new DadosMotivoCancelamento("Informe o motivo do cancelamento");
        }
        if (dados.idInstrucao() != null){
            throw new RuntimeException("Instrucao nao encontrada");
        }
        return null;
    }
}
