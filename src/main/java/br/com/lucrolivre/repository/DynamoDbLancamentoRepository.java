package br.com.lucrolivre.repository;

import br.com.lucrolivre.entity.LancamentoEntity;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbIndex;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

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
        // Nota arquitetural: O scan() varre a tabela inteira.
        // Mantido para suportar o getAll(), mas em produção para grandes volumes deve ser paginado.
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

    @Override
    public List<LancamentoEntity> findByMotoristaId(String motoristaId) {
        // Aponta diretamente para o GSI criado na AWS
        DynamoDbIndex<LancamentoEntity> index = lancamentoTable.index("motoristaId-index");

        // Prepara a chave de busca exata (Onde motoristaId == valor fornecido)
        QueryConditional queryConditional = QueryConditional
                .keyEqualTo(Key.builder().partitionValue(motoristaId).build());

        // Executa a query de alta performance e converte os resultados para lista
        return index.query(queryConditional)
                .stream()
                .flatMap(page -> page.items().stream())
                .toList();
    }
}