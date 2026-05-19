package br.com.lucrolivre.infrastructure.persistence.repository;

import br.com.lucrolivre.infrastructure.persistence.entity.LancamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LancamentoRepository extends JpaRepository<LancamentoEntity, UUID> {
}