package br.com.lucrolivre.application.usecase;

import br.com.lucrolivre.application.dto.LancamentoRequestDTO;
import br.com.lucrolivre.infrastructure.persistence.entity.LancamentoEntity;
import br.com.lucrolivre.infrastructure.persistence.entity.MotoristaEntity;
import br.com.lucrolivre.infrastructure.persistence.repository.LancamentoRepository;
import br.com.lucrolivre.infrastructure.persistence.repository.MotoristaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class SalvarLancamentoUseCase {

    private final LancamentoRepository lancamentoRepository;
    private final MotoristaRepository motoristaRepository;

    // Injetamos os dois repositórios via construtor
    public SalvarLancamentoUseCase(LancamentoRepository lancamentoRepository, MotoristaRepository motoristaRepository) {
        this.lancamentoRepository = lancamentoRepository;
        this.motoristaRepository = motoristaRepository;
    }

    @Transactional
    public LancamentoEntity executar(LancamentoRequestDTO dto) {
        // 1. Busca o motorista real no banco ou devolve erro se não existir
        MotoristaEntity motorista = motoristaRepository.findById(UUID.fromString(dto.usuarioId()))
                .orElseThrow(() -> new IllegalArgumentException("Motorista não encontrado com o ID informado."));

        // 2. Cria a entidade amarrando o objeto Motorista (e não mais uma String)
        LancamentoEntity entity = new LancamentoEntity(
                null, motorista, dto.data(), dto.origem(),
                dto.valorBruto(), dto.gastoCombustivel(), dto.gastoManutencao()
        );
        
        // 3. Salva no banco de dados
        return lancamentoRepository.save(entity);
    }
}