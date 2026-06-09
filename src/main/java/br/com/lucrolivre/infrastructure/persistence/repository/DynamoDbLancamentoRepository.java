package br.com.lucrolivre.infrastructure.persistence.repository;

import br.com.lucrolivre.domain.repository.LancamentoRepository;
import br.com.lucrolivre.infrastructure.persistence.entity.LancamentoEntity;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class DynamoDbLancamentoRepository implements LancamentoRepository {

    private final DynamoDbTable<LancamentoEntity> tabela;

    public DynamoDbLancamentoRepository(DynamoDbEnhancedClient enhancedClient) {
        this.tabela = enhancedClient.table("LucroLivre", TableSchema.fromBean(LancamentoEntity.class));
    }

    @Override
    public LancamentoEntity save(LancamentoEntity entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID().toString());
        }
        tabela.putItem(entity);
        return entity;
    }

    @Override
    public List<LancamentoEntity> findAll() {
        return tabela.scan().items().stream().collect(Collectors.toList());
    }
}