package br.com.lucrolivre.web.controller;

import br.com.lucrolivre.infrastructure.persistence.entity.MotoristaEntity;
import br.com.lucrolivre.infrastructure.persistence.repository.MotoristaRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/motoristas")
public class MotoristaController {

    private final MotoristaRepository repository;

    public MotoristaController(MotoristaRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public MotoristaEntity criar(@RequestBody MotoristaEntity motorista) {
        return repository.save(motorista);
    }
}