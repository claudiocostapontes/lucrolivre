package repository;

import entity.LancamentoEntity;
import java.util.List;

public interface LancamentoRepository {
    LancamentoEntity save(LancamentoEntity entity);
    List<LancamentoEntity> findAll();
}