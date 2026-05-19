package br.com.lucrolivre.infrastructure.persistence.repository;

import br.com.lucrolivre.infrastructure.persistence.entity.MotoristaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MotoristaRepository extends JpaRepository<MotoristaEntity, UUID> {
}