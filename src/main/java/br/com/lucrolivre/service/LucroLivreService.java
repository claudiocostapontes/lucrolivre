package br.com.lucrolivre.service;

import br.com.lucrolivre.domain.LucroLivreItem;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Service
public class LucroLivreService {

    private final DynamoDbTable<LucroLivreItem> tabela;

    public LucroLivreService(DynamoDbEnhancedClient enhancedClient) {
        // Vincula a entidade LucroLivreItem à tabela 'LucroLivre' do DynamoDB
        this.tabela = enhancedClient.table("LucroLivre", TableSchema.fromBean(LucroLivreItem.class));
    }

    public void salvar(LucroLivreItem item) {
        tabela.putItem(item);
    }

    public void salvarLancamentoUseCase(LucroLivreItem item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'salvarLancamentoUseCase'");
    }
}