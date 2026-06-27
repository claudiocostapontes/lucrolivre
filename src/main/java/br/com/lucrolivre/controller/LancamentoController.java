package br.com.lucrolivre.controller;

import br.com.lucrolivre.dto.LancamentoRequestDTO;
import br.com.lucrolivre.dto.LancamentoResponseDTO;
import br.com.lucrolivre.service.LancamentoService;
import jakarta.validation.Valid;
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

    @GetMapping("/ping")
    public String ping() {
        return "Controller está vivo e limpo!";
    }

    @PostMapping
    public LancamentoResponseDTO create(@Valid @RequestBody LancamentoRequestDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<LancamentoResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoResponseDTO> getById(@PathVariable String id) {
        LancamentoResponseDTO response = service.getById(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public LancamentoResponseDTO update(@PathVariable String id, @Valid @RequestBody LancamentoRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}