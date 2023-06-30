package aps.ldw.banco.domain.service.impl;

import aps.ldw.banco.domain.exception.EntidadeNaoEncontradaException;
import aps.ldw.banco.domain.model.Lancamento;
import aps.ldw.banco.domain.repository.LancamentoRepository;
import aps.ldw.banco.domain.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    private final LancamentoRepository lancamentoRepository;

    public LancamentoServiceImpl(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    @Override
    public List<Lancamento> findAll() {
        return lancamentoRepository.findAll();
    }

    @Override
    public Lancamento findById(Long id) {
        return lancamentoRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Lançamento não encontrado"));
    }

    @Override
    @Transactional
    public Lancamento save(Lancamento lancamento) {
        return lancamentoRepository.save(lancamento);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        lancamentoRepository.deleteById(id);
    }
}
