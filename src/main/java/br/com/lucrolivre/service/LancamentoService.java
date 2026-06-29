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

    public LancamentoResponseDTO update(String id, LancamentoRequestDTO dto) {
        LancamentoEntity entity = new LancamentoEntity();
        entity.setId(id);
        return getLancamentoResponseDTO(dto, entity);
    }

    private LancamentoResponseDTO getLancamentoResponseDTO(LancamentoRequestDTO dto, LancamentoEntity entity) {
        entity.setMotoristaId(dto.motoristaId());
        entity.setData(dto.data());
        entity.setOrigem(dto.origem());
        entity.setValorBruto(dto.valorBruto());
        entity.setGastoCombustivel(dto.gastoCombustivel());
        entity.setGastoManutencao(dto.gastoManutencao());

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

    // NOVO MÉTODO: Busca rápida por motorista
    public List<LancamentoResponseDTO> getByMotoristaId(String motoristaId) {
        return repository.findByMotoristaId(motoristaId).stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public void delete(String id) {
        repository.delete(id);
    }

    private LancamentoResponseDTO toResponseDTO(LancamentoEntity entity) {
        return new LancamentoResponseDTO(
                entity.getId(),
                entity.getMotoristaId(),
                entity.getData(),
                entity.getOrigem() != null ? entity.getOrigem().name() : null,
                entity.getValorBruto(),
                entity.getLucroLiquido()
        );
    }
}