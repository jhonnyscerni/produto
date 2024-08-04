package br.com.desafio.produto.dataprovider.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(collection = "categorias")
public class CategoriaEntity {

    @Id
    private String id;

    @Field("nome")
    private String nome;

    @Field("descricao")
    private String descricao;
}