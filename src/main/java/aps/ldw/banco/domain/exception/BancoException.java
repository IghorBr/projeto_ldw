package aps.ldw.banco.domain.exception;

public class BancoException extends RuntimeException {

    public BancoException(String message) {
        super(message);
    }

    public BancoException(String message, Throwable cause) {
        super(message, cause);
    }
}
