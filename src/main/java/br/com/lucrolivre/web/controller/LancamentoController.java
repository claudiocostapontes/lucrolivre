package br.com.lucrolivre.web.controller;

import br.com.lucrolivre.application.dto.LancamentoRequestDTO;
import br.com.lucrolivre.web.dto.LancamentoResponseDTO;
import br.com.lucrolivre.application.usecase.SalvarLancamentoUseCase;
import br.com.lucrolivre.application.usecase.ListarLancamentosUseCase;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/lancamentos")
public class LancamentoController {

    private final SalvarLancamentoUseCase salvarLancamentoUseCase;
    private final ListarLancamentosUseCase listarLancamentosUseCase;

    public LancamentoController(SalvarLancamentoUseCase salvarLancamentoUseCase, ListarLancamentosUseCase listarLancamentosUseCase) {
        this.salvarLancamentoUseCase = salvarLancamentoUseCase;
        this.listarLancamentosUseCase = listarLancamentosUseCase;
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

    @GetMapping
    public List<LancamentoResponseDTO> listar() {
        return listarLancamentosUseCase.executar();
    }
}