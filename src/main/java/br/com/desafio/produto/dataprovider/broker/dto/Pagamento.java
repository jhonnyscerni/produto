package br.com.desafio.produto.dataprovider.broker.dto;

import br.com.desafio.produto.core.domain.Produto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Pagamento {

    private String nomePagamento;
    private String status;
    private String pedidoId;
    private List<Produto> produtos;

}
