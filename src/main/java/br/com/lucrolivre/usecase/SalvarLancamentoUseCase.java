package br.com.lucrolivre.usecase;

import br.com.lucrolivre.dto.LancamentoRequestDTO;
import br.com.lucrolivre.repository.LancamentoRepository;
import br.com.lucrolivre.entity.LancamentoEntity;
import br.com.lucrolivre.repository.MotoristaRepository;
import org.springframework.stereotype.Service;

@Service
public class SalvarLancamentoUseCase {

    private final LancamentoRepository lancamentoRepository;
    private final MotoristaRepository motoristaRepository;

    public SalvarLancamentoUseCase(LancamentoRepository lancamentoRepository, MotoristaRepository motoristaRepository) {
        this.lancamentoRepository = lancamentoRepository;
        this.motoristaRepository = motoristaRepository;
    }
    public LancamentoEntity executar(LancamentoRequestDTO dto) {

        if (dto.motoristaId() == null || dto.motoristaId().isEmpty()) {
            throw new IllegalArgumentException("O campo 'motoristaId' é obrigatório.");
        }
        LancamentoEntity entity = new LancamentoEntity();

        entity.setMotoristaId(dto.motoristaId());
        entity.setData(dto.data());
        entity.setOrigem(dto.origem());
        entity.setValorBruto(dto.valorBruto());
        entity.setGastoCombustivel(dto.gastoCombustivel());
        entity.setGastoManutencao(dto.gastoManutencao());

        return lancamentoRepository.save(entity);
    }
}