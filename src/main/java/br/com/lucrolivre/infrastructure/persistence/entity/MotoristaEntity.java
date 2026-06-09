package br.com.lucrolivre.infrastructure.persistence.entity;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;

@DynamoDbBean
public class MotoristaEntity {

    private String id;
    private String sk; // Adicione este campo
    private String nome;
    private String cpf;

    public MotoristaEntity(String id, String sk, String nome, String cpf) {
        this.id = id;
        this.sk = sk;
        this.nome = nome;
        this.cpf = cpf;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("pk")
    public String getId() { return id; }

    @DynamoDbAttribute("nome")
    public String getNome() { return nome; }

}