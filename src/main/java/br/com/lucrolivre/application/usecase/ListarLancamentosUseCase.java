package br.com.lucrolivre.application.usecase;

import br.com.lucrolivre.domain.repository.LancamentoRepository;
import br.com.lucrolivre.infrastructure.persistence.repository.DynamoDbMotoristaRepository;
import br.com.lucrolivre.web.dto.LancamentoResponseDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.boot.web.server.Ssl.ClientAuth.map;

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
                    .map(motorista -> motorista.getNome())
                    .orElse("Motorista não encontrado");

            return new LancamentoResponseDTO(
                    entity.getId().toString(),
                    motoristaNome,
                    entity.getData(),
                    entity.getOrigem().name(),
                    entity.getValorBruto(),
                    lucro
            );
        }).collect(Collectors.toList());
    }
}