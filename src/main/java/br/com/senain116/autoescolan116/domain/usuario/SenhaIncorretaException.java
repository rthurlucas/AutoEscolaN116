package br.com.senain116.autoescolan116.domain.usuario;

public class SenhaIncorretaException extends RuntimeException {
    public SenhaIncorretaException(String message) {
        super(message);
    }
}
