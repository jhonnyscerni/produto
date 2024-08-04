package br.com.desafio.produto.dataprovider.database.entity;

import br.com.desafio.produto.core.domain.Carrinho;
import br.com.desafio.produto.core.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Document(collection = "pedidos")
public class PedidoEntity {

    @Id
    private String id;

    @Field("carrinho")
    private CarrinhoEntity carrinho;

    @Field("data_criacao")
    private LocalDateTime dataCriacao;

    @Field("status")
    private Status status;
}