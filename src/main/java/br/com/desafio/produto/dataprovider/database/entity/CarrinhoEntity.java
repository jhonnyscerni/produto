package br.com.desafio.produto.dataprovider.database.entity;

import br.com.desafio.produto.core.domain.Produto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(collection = "carrinhos")
public class CarrinhoEntity {

    @Id
    private String id;

    @Field("produtos")
    private List<ProdutoEntity> produtos;

    @Field("quantidade")
    private int quantidade;

    @Field("data_criacao")
    private LocalDateTime dataCriacao;

    @Field("data_alteracao")
    private LocalDateTime dataAlteracao;
}