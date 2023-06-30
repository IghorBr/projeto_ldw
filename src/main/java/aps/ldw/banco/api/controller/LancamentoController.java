package aps.ldw.banco.api.controller;

import aps.ldw.banco.api.dto.LancamentoDTO;
import aps.ldw.banco.api.mapper.LancamentoMapper;
import aps.ldw.banco.domain.model.Lancamento;
import aps.ldw.banco.domain.service.LancamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    private final LancamentoService lancamentoService;
    private final LancamentoMapper mapper;

    public LancamentoController(LancamentoService lancamentoService, LancamentoMapper mapper) {
        this.lancamentoService = lancamentoService;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<LancamentoDTO>> findAll() {
        List<Lancamento> lancamentos = lancamentoService.findAll();

        List<LancamentoDTO> dtos = lancamentos.stream().map(l -> mapper.convertToDTO(l)).toList();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDTO> findById(@PathVariable("id") Long id) {
        Lancamento lancamento = lancamentoService.findById(id);
        LancamentoDTO dto = mapper.convertToDTO(lancamento);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Lancamento input) {
        Lancamento lancamento = lancamentoService.save(input);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(lancamento.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Lancamento input, @PathVariable("id") Long id) {
        input.setId(id);

        Lancamento lancamento = lancamentoService.save(input);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        lancamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
