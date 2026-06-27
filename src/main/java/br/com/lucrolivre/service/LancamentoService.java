package br.com.lucrolivre.service;

import br.com.lucrolivre.dto.LancamentoRequestDTO;
import br.com.lucrolivre.dto.LancamentoResponseDTO;
import br.com.lucrolivre.entity.LancamentoEntity;
import br.com.lucrolivre.repository.LancamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoService {

    private final LancamentoRepository repository;

    public LancamentoService(LancamentoRepository repository) {
        this.repository = repository;
    }

    public LancamentoResponseDTO create(LancamentoRequestDTO dto) {
        LancamentoEntity entity = new LancamentoEntity();

        return getLancamentoResponseDTO(dto, entity);
    }

    private LancamentoResponseDTO getLancamentoResponseDTO(LancamentoRequestDTO dto, LancamentoEntity entity) {
        entity.setMotoristaId(dto.getMotoristaId());
        entity.setData(dto.getData());
        entity.setOrigem(dto.getOrigem());
        entity.setValorBruto(dto.getValorBruto());
        entity.setGastoCombustivel(dto.getGastoCombustivel());
        entity.setGastoManutencao(dto.getGastoManutencao());

        LancamentoEntity saved = repository.save(entity);
        return toResponseDTO(saved);
    }

    public List<LancamentoResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public LancamentoResponseDTO getById(String id) {
        LancamentoEntity entity = repository.findById(id);
        if (entity == null) {
            return null;
        }
        return toResponseDTO(entity);
    }

    public LancamentoResponseDTO update(String id, LancamentoRequestDTO dto) {
        LancamentoEntity entity = new LancamentoEntity();
        entity.setId(id);

        return getLancamentoResponseDTO(dto, entity);
    }

    public void delete(String id) {
        repository.delete(id);
    }

    private LancamentoResponseDTO toResponseDTO(LancamentoEntity entity) {
        return new LancamentoResponseDTO(
                entity.getId(),
                entity.getMotoristaId(),
                entity.getData(), // Retirado o .toString() que estava a causar o erro
                entity.getOrigem() != null ? entity.getOrigem().name() : null,
                entity.getValorBruto(),
                entity.getLucroLiquido()
        );
    }
}