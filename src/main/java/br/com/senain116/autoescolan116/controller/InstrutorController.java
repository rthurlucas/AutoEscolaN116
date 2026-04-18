package br.com.senain116.autoescolan116.controller;

import br.com.senain116.autoescolan116.domain.instrutor.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/instrutores")
public class InstrutorController {
    @Autowired
    private InstrutorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoInstrutor> cadastrarInstrutor(
            @RequestBody @Valid DadosCadastroInstrutor dados,
            UriComponentsBuilder uriBuilder) {
        Instrutor instrutor = new Instrutor(dados);
        repository.save(instrutor);
        URI uri = uriBuilder
                .path("/instrutores/{id}")
                .buildAndExpand(instrutor.getId())
                .toUri();
        DadosDetalhamentoInstrutor dto = new DadosDetalhamentoInstrutor(instrutor);
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemInstrutor>> listarInstrutores(
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        Page page = repository
                .findAllByAtivoTrue(paginacao)
                .map(DadosListagemInstrutor::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoInstrutor> detalharInstrutor(
            @PathVariable Long id) {
        Instrutor instrutor = repository.getReferenceById(id);
        DadosDetalhamentoInstrutor dto = new DadosDetalhamentoInstrutor(instrutor);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoInstrutor> atualizarInstrutor(
            @RequestBody @Valid DadosAtualizacaoInstrutor dados) {
        Instrutor instrutor = repository.getReferenceById(dados.id());
        instrutor.atualizarInformacoes(dados);
        repository.save(instrutor);
        DadosDetalhamentoInstrutor dto = new DadosDetalhamentoInstrutor(instrutor);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirInstrutor(@PathVariable Long id) {
        Instrutor instrutor = repository.getReferenceById(id);
        instrutor.excluir();
        repository.save(instrutor);
        return ResponseEntity.noContent().build();
    }
}