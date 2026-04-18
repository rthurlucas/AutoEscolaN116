package br.com.senain116.autoescolan116.infra.exception;

import br.com.senain116.autoescolan116.domain.aluno.AlunoNotFoundException;
import br.com.senain116.autoescolan116.domain.instrucao.DadosIncompletosException;
import br.com.senain116.autoescolan116.domain.instrucao.validacao.ValidationException;
import br.com.senain116.autoescolan116.domain.instrutor.InstrutorNotFoundException;
import br.com.senain116.autoescolan116.domain.usuario.SenhaIncorretaException;
import br.com.senain116.autoescolan116.domain.usuario.UsuarioNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorGlobalErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarBadRequest(MethodArgumentNotValidException ex) {
        List<FieldError> erros = ex.getFieldErrors();
        return ResponseEntity
                .badRequest()
                .body(erros.stream().map(DadosBadRequest::new));
    }



    @ExceptionHandler(SenhaIncorretaException.class)
    public ResponseEntity<String> tratarSenhaIncorreta(SenhaIncorretaException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<String> tratarUsuarioNotFound(UsuarioNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InstrutorNotFoundException.class)
    public ResponseEntity<String> tratarInstrutorNotFound(InstrutorNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(AlunoNotFoundException.class)
    public ResponseEntity<String> tratarAlunoNotFound(AlunoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DadosIncompletosException.class)
    public ResponseEntity<String> TratarDadosIncompletos(DadosIncompletosException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> TratarValidacao(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }

    private record DadosBadRequest(String field, String message) {
        public DadosBadRequest(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}