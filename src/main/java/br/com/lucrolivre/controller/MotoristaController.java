package br.com.lucrolivre.controller;

import br.com.lucrolivre.repository.MotoristaRepository;
import br.com.lucrolivre.entity.MotoristaEntity;
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