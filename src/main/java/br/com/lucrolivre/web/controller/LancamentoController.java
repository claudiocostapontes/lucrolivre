package br.com.lucrolivre.web.controller;

import br.com.lucrolivre.application.dto.LancamentoRequestDTO;
import br.com.lucrolivre.web.dto.LancamentoResponseDTO;
import br.com.lucrolivre.application.usecase.SalvarLancamentoUseCase; // Não esqueça este import!
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {

    // 1. Declare a variável final (o Spring vai injetar isso para você)
    private final SalvarLancamentoUseCase salvarLancamentoUseCase;

    // 2. O Construtor: O Spring usa isso para "ligar" o UseCase ao Controller
    public LancamentoController(SalvarLancamentoUseCase salvarLancamentoUseCase) {
        this.salvarLancamentoUseCase = salvarLancamentoUseCase;
    }

    @PostMapping
    public LancamentoResponseDTO criar(@RequestBody LancamentoRequestDTO dto) {
        var entity = salvarLancamentoUseCase.executar(dto);
        
        BigDecimal lucro = entity.getValorBruto()
            .subtract(entity.getGastoCombustivel())
            .subtract(entity.getGastoManutencao());

        return new LancamentoResponseDTO(
            entity.getId().toString(),
            entity.getMotorista().getNome(),
            entity.getData(),
            entity.getOrigem().name(),
            entity.getValorBruto(),
            lucro
        );
    }
}