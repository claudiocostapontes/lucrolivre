package br.com.lucrolivre.web.controller;

import br.com.lucrolivre.application.dto.LancamentoRequestDTO;
import br.com.lucrolivre.application.usecase.SalvarLancamentoUseCase;
import br.com.lucrolivre.infrastructure.persistence.entity.LancamentoEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {

    private final SalvarLancamentoUseCase salvarLancamentoUseCase;

    public LancamentoController(SalvarLancamentoUseCase salvarLancamentoUseCase) {
        this.salvarLancamentoUseCase = salvarLancamentoUseCase;
    }

    @PostMapping
    public ResponseEntity<LancamentoEntity> criar(@RequestBody LancamentoRequestDTO dto) {
        LancamentoEntity lancamentoSalvo = salvarLancamentoUseCase.executar(dto);
        return ResponseEntity.ok(lancamentoSalvo);
    }
}