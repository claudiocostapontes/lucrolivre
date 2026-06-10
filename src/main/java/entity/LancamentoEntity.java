package entity;

import model.Origem;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;

import java.math.BigDecimal;
import java.time.LocalDate;

@DynamoDbBean
public class LancamentoEntity {

    private String id;
    private final String sk = "LANCAMENTO";
    private String motoristaId;
    private LocalDate data;
    private Origem origem;
    private BigDecimal valorBruto;
    private BigDecimal gastoCombustivel;
    private BigDecimal gastoManutencao;

    public LancamentoEntity() {}

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
    public void setMotoristaId(String motoristaId) { this.motoristaId = motoristaId; }

    @DynamoDbAttribute("data")
    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    @DynamoDbAttribute("origem")
    public Origem getOrigem() { return origem; }
    public void setOrigem(Origem origem) { this.origem = origem; }

    @DynamoDbAttribute("valorBruto")
    public BigDecimal getValorBruto() { return valorBruto; }
    public void setValorBruto(BigDecimal valorBruto) { this.valorBruto = valorBruto; }

    @DynamoDbAttribute("gastoCombustivel")
    public BigDecimal getGastoCombustivel() { return gastoCombustivel; }
    public void setGastoCombustivel(BigDecimal gastoCombustivel) { this.gastoCombustivel = gastoCombustivel; }

    @DynamoDbAttribute("gastoManutencao")
    public BigDecimal getGastoManutencao() { return gastoManutencao; }
    public void setGastoManutencao(BigDecimal gastoManutencao) { this.gastoManutencao = gastoManutencao; }

    public BigDecimal getLucroLiquido() {
        BigDecimal totalGastos = (gastoCombustivel != null ? gastoCombustivel : BigDecimal.ZERO)
                .add(gastoManutencao != null ? gastoManutencao : BigDecimal.ZERO);
        return (valorBruto != null ? valorBruto : BigDecimal.ZERO).subtract(totalGastos);
    }
}