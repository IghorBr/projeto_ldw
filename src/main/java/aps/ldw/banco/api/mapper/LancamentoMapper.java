package aps.ldw.banco.api.mapper;

import aps.ldw.banco.api.dto.LancamentoDTO;
import aps.ldw.banco.domain.model.Lancamento;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LancamentoMapper {

    public LancamentoDTO convertToDTO(Lancamento lancamento) {
        Objects.requireNonNull(lancamento);

        return LancamentoDTO.builder()
                .id(lancamento.getId())
                .criadoEm(lancamento.getCriadoEm())
                .valor(lancamento.getValor())
                .tipoLancamento(lancamento.getTipoLancamento().name())
                .descricao(lancamento.getDescricao())
                .build();
    }
}
