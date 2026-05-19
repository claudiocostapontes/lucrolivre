package br.com.lucrolivre.infrastructure.persistence.entity;

import br.com.lucrolivre.domain.model.Origem;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_lancamento")
public class LancamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Aqui está a mágica do relacionamento:
    @ManyToOne(optional = false)
    @JoinColumn(name = "motorista_id", nullable = false)
    private MotoristaEntity motorista;

    @Column(nullable = false)
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Origem origem;

    @Column(nullable = false)
    private BigDecimal valorBruto;

    private BigDecimal gastoCombustivel;
    private BigDecimal gastoManutencao;

    protected LancamentoEntity() {}

    public LancamentoEntity(UUID id, MotoristaEntity motorista, LocalDate data, Origem origem, BigDecimal valorBruto, BigDecimal gastoCombustivel, BigDecimal gastoManutencao) {
        this.id = id;
        this.motorista = motorista;
        this.data = data;
        this.origem = origem;
        this.valorBruto = valorBruto;
        this.gastoCombustivel = gastoCombustivel;
        this.gastoManutencao = gastoManutencao;
    }

    public UUID getId() { return id; }
    public MotoristaEntity getMotorista() { return motorista; }
    public LocalDate getData() { return data; }
    public Origem getOrigem() { return origem; }
    public BigDecimal getValorBruto() { return valorBruto; }
    public BigDecimal getGastoCombustivel() { return gastoCombustivel; }
    public BigDecimal getGastoManutencao() { return gastoManutencao; }
}