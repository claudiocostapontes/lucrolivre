package controller;

import dto.LancamentoRequestDTO;
import dto.LancamentoResponseDTO;
import entity.LancamentoEntity;
import org.springframework.web.bind.annotation.*;
import repository.LancamentoRepository;

import java.util.List;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

    private final LancamentoRepository repository;

    public LancamentoController(LancamentoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public LancamentoResponseDTO create(@RequestBody LancamentoRequestDTO dto) {
        LancamentoEntity entity = new LancamentoEntity();
        entity.setMotoristaId(dto.motoristaId());
        entity.setData(dto.data());
        entity.setOrigem(dto.origem());
        entity.setValorBruto(dto.valorBruto());
        entity.setGastoCombustivel(dto.gastoCombustivel());
        entity.setGastoManutencao(dto.gastoManutencao());

        LancamentoEntity saved = repository.save(entity);

        return new LancamentoResponseDTO(
            saved.getId(),
            saved.getMotoristaId(),
            saved.getData(),
            saved.getOrigem().name(),
            saved.getValorBruto(),
            saved.getLucroLiquido()
        );
    }

    @GetMapping
    public List<LancamentoResponseDTO> getAll() {
        return repository.findAll().stream()
            .map(entity -> new LancamentoResponseDTO(
                entity.getId(),
                entity.getMotoristaId(),
                entity.getData(),
                entity.getOrigem().name(),
                entity.getValorBruto(),
                entity.getLucroLiquido()
            ))
            .toList();
    }
}