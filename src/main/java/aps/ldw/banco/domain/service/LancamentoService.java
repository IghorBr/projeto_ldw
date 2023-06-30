package aps.ldw.banco.domain.service;

import aps.ldw.banco.domain.model.Lancamento;

import java.util.List;

public interface LancamentoService {

    List<Lancamento> findAll();
    Lancamento findById(Long id);

    Lancamento save(Lancamento lancamento);

    void deleteById(Long id);
}
