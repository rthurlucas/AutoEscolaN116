package br.com.senain116.autoescolan116.controller;

import br.com.senain116.autoescolan116.domain.instrucao.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/instrucao")
@RestController
public class InstrucaoController {

    @Autowired
    private AgendaDeInstrucoes agenda;

    @Autowired
    private InstrucaoRepository repository;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoAgendamento> agendarInstrucao(
            @RequestBody @Valid DadosAgendamento dados){
        DadosDetalhamentoAgendamento dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAgendamento(@PathVariable Long id){
        Instrucao instrucao = repository.getReferenceById(id);
        instrucao.excluir(instrucao);
        return ResponseEntity.ok().build();
    }
}
