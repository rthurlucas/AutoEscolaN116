package br.com.senain116.autoescolan116.controller;

import br.com.senain116.autoescolan116.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder enconder;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrarUsuario(
            @RequestBody @Valid DadosCadastroUsuario dados,
            UriComponentsBuilder uriBuilder) {
        String senhaCriptografada = enconder.encode(dados.senha());
        Usuario usuario = new Usuario(null, dados.login(), senhaCriptografada, true, dados.perfil());
        repository.save(usuario);
        URI uri = uriBuilder
                .path("/usuarios/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();
        DadosDetalhamentoUsuario dto = new DadosDetalhamentoUsuario(usuario);
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listarUsuarios(
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        Page page = repository
                .findAllByAtivoTrue(paginacao)
                .map(DadosListagemUsuario::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> detalharUsuario(
            @PathVariable Long id) {
        Usuario usuario = repository.getReferenceById(id);
        DadosDetalhamentoUsuario dto = new DadosDetalhamentoUsuario(usuario);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> atualizarUsuario(
            @RequestBody @Valid DadosAtualizacaoUsuario dados) {
        Usuario usuario = repository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);
        repository.save(usuario);
        DadosDetalhamentoUsuario dto = new DadosDetalhamentoUsuario(usuario);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        Usuario usuario = repository.getReferenceById(id);
        usuario.excluir();
        repository.save(usuario);
        return ResponseEntity.noContent().build();
    }
}