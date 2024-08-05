package br.com.desafio.produto.core.domain;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {

    private String id;
    private String nome;
    private String descricao;
    private int quantidade;
    private BigDecimal valor;
    private Categoria categoria;
}