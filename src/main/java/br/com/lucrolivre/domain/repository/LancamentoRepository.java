package br.com.lucrolivre.domain.repository;

import br.com.lucrolivre.infrastructure.persistence.entity.LancamentoEntity;
import java.util.List;

public interface LancamentoRepository {
    LancamentoEntity save(LancamentoEntity entity);
    List<LancamentoEntity> findAll();
}