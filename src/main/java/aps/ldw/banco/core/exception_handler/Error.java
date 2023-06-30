package aps.ldw.banco.core.exception_handler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class Error {

    private Integer status;
    private String mensagem;
}
