package br.com.desafio.produto.dataprovider.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(collection = "produtos")
public class ProdutoEntity {

    @Id
    private String id;

    @Field("nome")
    private String nome;

    @Field("descricao")
    private String descricao;

    @Field("quantidade")
    private int quantidade;

    @Field("valor")
    private BigDecimal valor;

    @Field("categoria")
    private CategoriaEntity categoria;
}
