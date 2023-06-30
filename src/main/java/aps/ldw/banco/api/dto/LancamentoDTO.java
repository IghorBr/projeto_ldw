package aps.ldw.banco.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter @Setter
public class LancamentoDTO {

    private Long id;
    private BigDecimal valor;
    private String descricao;
    private String tipoLancamento;
    private LocalDate criadoEm;
}
