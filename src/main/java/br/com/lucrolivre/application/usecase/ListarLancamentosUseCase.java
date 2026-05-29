package br.com.lucrolivre.application.usecase;

import br.com.lucrolivre.infrastructure.persistence.repository.LancamentoRepository;
import br.com.lucrolivre.web.dto.LancamentoResponseDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarLancamentosUseCase {

    private final LancamentoRepository repository;

    public ListarLancamentosUseCase(LancamentoRepository repository) {
        this.repository = repository;
    }

    public List<LancamentoResponseDTO> executar() {
        return repository.findAll().stream().map(entity -> {
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
        }).collect(Collectors.toList());
    }
}