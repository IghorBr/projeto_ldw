package aps.ldw.banco.domain.exception;

public class EntidadeNaoEncontradaException extends BancoException {
    public EntidadeNaoEncontradaException(String message) {
        super(message);
    }

    public EntidadeNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }
}
