package br.com.lucrolivre.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "tb_motorista")
public class MotoristaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    // Construtor vazio exigido pelo Hibernate
    protected MotoristaEntity() {}

    public MotoristaEntity(UUID id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    public UUID getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
}