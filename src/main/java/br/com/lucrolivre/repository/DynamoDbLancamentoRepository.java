package br.com.lucrolivre.repository;

import br.com.lucrolivre.entity.LancamentoEntity;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;

@Repository
public class DynamoDbLancamentoRepository implements LancamentoRepository {

    private final DynamoDbTable<LancamentoEntity> lancamentoTable;

    public DynamoDbLancamentoRepository(DynamoDbEnhancedClient enhancedClient) {
        this.lancamentoTable = enhancedClient.table("LucroLivre", TableSchema.fromBean(LancamentoEntity.class));
    }

    @Override
    public LancamentoEntity save(LancamentoEntity entity) {
        lancamentoTable.putItem(entity);
        return entity;
    }

    @Override
    public List<LancamentoEntity> findAll() {
        return lancamentoTable.scan().items().stream().toList();
    }

    @Override
    public void delete(String id) {
        Key key = Key.builder()
                .partitionValue(id)
                .sortValue("LANCAMENTO")
                .build();

        lancamentoTable.deleteItem(key);
    }

    @Override
    public LancamentoEntity findById(String id) {
        Key key = Key.builder()
                .partitionValue(id)
                .sortValue("LANCAMENTO")
                .build();

        return lancamentoTable.getItem(key);
    }
}