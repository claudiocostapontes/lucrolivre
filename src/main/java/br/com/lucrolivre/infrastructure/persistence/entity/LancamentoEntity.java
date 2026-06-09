package br.com.lucrolivre.infrastructure.persistence.entity;

import br.com.lucrolivre.domain.model.Origem;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;

import java.math.BigDecimal;
import java.time.LocalDate;

@DynamoDbBean
public class LancamentoEntity {

    private String id;
    // Criamos o campo sk com um valor padrão para diferenciar Lançamentos de Motoristas na mesma tabela
    private String sk = "LANCAMENTO";
    private String motoristaId;
    private LocalDate data;
    private Origem origem;
    private BigDecimal valorBruto;
    private BigDecimal gastoCombustivel;
    private BigDecimal gastoManutencao;

    public LancamentoEntity(String id, String motoristaId, LocalDate data, Origem origem,
                            BigDecimal valorBruto, BigDecimal gastoCombustivel, BigDecimal gastoManutencao) {
        this.id = id;
        this.motoristaId = motoristaId;
        this.data = data;
        this.origem = origem;
        this.valorBruto = valorBruto;
        this.gastoCombustivel = gastoCombustivel;
        this.gastoManutencao = gastoManutencao;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("pk")
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @DynamoDbAttribute("motoristaId")
    public String getMotoristaId() { return motoristaId; }

    @DynamoDbAttribute("data")
    public LocalDate getData() { return data; }

    @DynamoDbAttribute("origem")
    public Origem getOrigem() { return origem; }

    @DynamoDbAttribute("valorBruto")
    public BigDecimal getValorBruto() { return valorBruto; }

    @DynamoDbAttribute("gastoCombustivel")
    public BigDecimal getGastoCombustivel() { return gastoCombustivel; }

    @DynamoDbAttribute("gastoManutencao")
    public BigDecimal getGastoManutencao() { return gastoManutencao; }
}