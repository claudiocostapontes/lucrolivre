package br.com.lucrolivre.domain.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Service
public class LucroLivreService {

    private final DynamoDbTable<br.com.lucrolivre.domain.LucroLivreItem> tabela;

    public LucroLivreService(DynamoDbEnhancedClient enhancedClient) {
        // Vincula a entidade LucroLivreItem à tabela 'LucroLivre' do DynamoDB
        this.tabela = enhancedClient.table("LucroLivre", TableSchema.fromBean(br.com.lucrolivre.domain.LucroLivreItem.class));
    }

    public void salvar(br.com.lucrolivre.domain.LucroLivreItem item) {
        tabela.putItem(item);
    }

    public void salvarLancamentoUseCase(br.com.lucrolivre.domain.LucroLivreItem item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvarLancamentoUseCase'");
    }
}