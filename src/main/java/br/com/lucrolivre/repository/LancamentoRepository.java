package br.com.lucrolivre.repository;

import br.com.lucrolivre.entity.LancamentoEntity;
import java.util.List;

public interface LancamentoRepository {
    LancamentoEntity save(LancamentoEntity entity);
    List<LancamentoEntity> findAll();
}