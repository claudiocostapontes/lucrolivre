package repository;

import entity.MotoristaEntity;

import java.util.Optional;
import java.util.UUID;

public interface MotoristaRepository {
    MotoristaEntity save(MotoristaEntity motorista);
    Optional<MotoristaEntity> findById(UUID id);

    Optional<MotoristaEntity> findById(String id);
}