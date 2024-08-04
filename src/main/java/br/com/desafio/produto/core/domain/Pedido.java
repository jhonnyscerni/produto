package br.com.desafio.produto.core.domain;

import br.com.desafio.produto.core.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Pedido {

    @Id
    private String id;

    @Field("carrinho")
    private Carrinho carrinho;

    @Field("data_criacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCriacao;

    @Field("status")
    private Status status;
}
