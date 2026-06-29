package br.com.lucrolivre.controller;

import br.com.lucrolivre.dto.LancamentoRequestDTO;
import br.com.lucrolivre.dto.LancamentoResponseDTO;
import br.com.lucrolivre.service.LancamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {

    private final LancamentoService service;

    public LancamentoController(LancamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LancamentoResponseDTO> create(@Valid @RequestBody LancamentoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<LancamentoResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoResponseDTO> getById(@PathVariable String id) {
        LancamentoResponseDTO dto = service.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    // NOVO ENDPOINT: Busca rápida por motorista usando o GSI
    @GetMapping("/motorista/{motoristaId}")
    public ResponseEntity<List<LancamentoResponseDTO>> getByMotoristaId(@PathVariable String motoristaId) {
        return ResponseEntity.ok(service.getByMotoristaId(motoristaId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LancamentoResponseDTO> update(@PathVariable String id, @Valid @RequestBody LancamentoRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}