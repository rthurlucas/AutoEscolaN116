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
    private AgendaDeInstrucoes cancelar;

    @Autowired
    private InstrucaoRepository repository;

    @PostMapping
    public ResponseEntity<DadosDetalhamentoAgendamento> agendarInstrucao(
            @RequestBody @Valid DadosAgendamento dados){
        DadosDetalhamentoAgendamento dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DadosCancelamentoAgendamento> excluirAgendamento(
            @PathVariable Long id, DadosCancelamentoAgendamento dados){
        DadosCancelamentoAgendamento dto = cancelar.cancelarInstrucao(dados);
        return ResponseEntity.noContent().build();
    }
}
