package br.com.lucrolivre.infrastructure.persistence.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey; // Importante!
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;

@DynamoDbBean
public class MotoristaEntity {

    private String id;
    private String sk; // Adicione este campo
    private String nome;
    private String cpf;

    public MotoristaEntity() {}

    public MotoristaEntity(String id, String sk, String nome, String cpf) {
        this.id = id;
        this.sk = sk;
        this.nome = nome;
        this.cpf = cpf;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("pk")
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @DynamoDbSortKey // Mapeamento da Sort Key
    @DynamoDbAttribute("sk")
    public String getSk() { return sk; }
    public void setSk(String sk) { this.sk = sk; }

    @DynamoDbAttribute("nome")
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    @DynamoDbAttribute("cpf")
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
}