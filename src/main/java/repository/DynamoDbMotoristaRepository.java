package repository;

import entity.MotoristaEntity;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
// IMPORT NECESSÁRIO PARA O DIAGNÓSTICO:
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.ListTablesResponse;

import java.util.Optional;
import java.util.UUID;

@Repository
public class DynamoDbMotoristaRepository implements MotoristaRepository {

    private final DynamoDbTable<MotoristaEntity> tabela;

    // AQUI ESTÁ A CORREÇÃO: Injetamos o DynamoDbClient padrão também para poder listar as tabelas
    public DynamoDbMotoristaRepository(DynamoDbEnhancedClient enhancedClient, DynamoDbClient standardClient) {

        // DIAGNÓSTICO DEFINITIVO
        try {
            System.out.println(">>> DIAGNÓSTICO AWS: Listando tabelas visíveis...");
            ListTablesResponse response = standardClient.listTables();
            response.tableNames().forEach(name -> System.out.println(">>> Tabela encontrada: " + name));
        } catch (Exception e) {
            System.err.println(">>> ERRO AO LISTAR: " + e.getMessage());
        }

        // Inicialização da tabela
        this.tabela = enhancedClient.table("LucroLivre", TableSchema.fromBean(MotoristaEntity.class));
    }

    @Override
    public MotoristaEntity save(MotoristaEntity motorista) {
        tabela.putItem(motorista);
        return motorista;
    }

    @Override
    public Optional<MotoristaEntity> findById(UUID id) {
        if (id == null) return Optional.empty();
        return findById(id.toString());
    }

    @Override
    public Optional<MotoristaEntity> findById(String id) {
        Key key = Key.builder()
                .partitionValue(id)
                .sortValue("METADATA")
                .build();

        return Optional.ofNullable(tabela.getItem(key));
    }
}