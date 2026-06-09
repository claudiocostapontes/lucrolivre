package br.com.lucrolivre.infrastructure.persistence.repository;

import br.com.lucrolivre.infrastructure.persistence.entity.MotoristaEntity;

import java.util.Optional;
import java.util.UUID;

public interface MotoristaRepository {
    MotoristaEntity save(MotoristaEntity motorista);
    Optional<MotoristaEntity> findById(UUID id);

    Optional<MotoristaEntity> findById(String id);
}