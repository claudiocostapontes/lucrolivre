package br.com.lucrolivre.application.usecase;

import br.com.lucrolivre.domain.repository.LancamentoRepository;
import br.com.lucrolivre.infrastructure.persistence.entity.MotoristaEntity;
import br.com.lucrolivre.infrastructure.persistence.repository.DynamoDbMotoristaRepository;
import br.com.lucrolivre.web.dto.LancamentoResponseDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ListarLancamentosUseCase {

    private final LancamentoRepository repository;
    private final DynamoDbMotoristaRepository motoristaRepository;

    public ListarLancamentosUseCase(LancamentoRepository lancamentoRepository, DynamoDbMotoristaRepository motoristaRepository) {
        this.repository = lancamentoRepository;
        this.motoristaRepository = motoristaRepository;
    }

    public List<LancamentoResponseDTO> executar() {
        return repository.findAll().stream().map(entity -> {
            BigDecimal lucro = entity.getValorBruto()
                    .subtract(entity.getGastoCombustivel())
                    .subtract(entity.getGastoManutencao());

            String motoristaNome = motoristaRepository.findById(UUID.fromString(entity.getMotoristaId()))
                    .map(MotoristaEntity::getNome)
                    .orElse("Motorista não encontrado");

            return new LancamentoResponseDTO(
                    entity.getId(),
                    motoristaNome,
                    entity.getData(),
                    entity.getOrigem().name(),
                    entity.getValorBruto(),
                    lucro
            );
        }).collect(Collectors.toList());
    }
}