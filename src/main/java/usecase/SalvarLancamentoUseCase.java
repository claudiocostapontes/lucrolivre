package usecase;

import dto.LancamentoRequestDTO;
import repository.LancamentoRepository;
import entity.LancamentoEntity;
import entity.MotoristaEntity;
import repository.MotoristaRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class SalvarLancamentoUseCase {

    private final LancamentoRepository lancamentoRepository;
    private final MotoristaRepository motoristaRepository;

    public SalvarLancamentoUseCase(LancamentoRepository lancamentoRepository, MotoristaRepository motoristaRepository) {
        this.lancamentoRepository = lancamentoRepository;
        this.motoristaRepository = motoristaRepository;
    }

    public LancamentoEntity executar(LancamentoRequestDTO dto) {
        // Validação defensiva
        if (dto.motoristaId() == null || dto.motoristaId().isEmpty()) {
            throw new IllegalArgumentException("O campo 'motoristaId' é obrigatório.");
        }

        // Busca o motorista no banco. Agora, com o ajuste na entidade, ele encontrará a pk.
        MotoristaEntity motorista = motoristaRepository.findById(dto.motoristaId())
                .orElseThrow(() -> new IllegalArgumentException("Motorista não encontrado com o ID: " + dto.motoristaId()));

        // Cria o lançamento
        LancamentoEntity entity = new LancamentoEntity(
                UUID.randomUUID().toString(),
                motorista.getId(),
                dto.data(),
                dto.origem(),
                dto.valorBruto(),
                dto.gastoCombustivel(),
                dto.gastoManutencao()
        );

        return lancamentoRepository.save(entity);
    }
}