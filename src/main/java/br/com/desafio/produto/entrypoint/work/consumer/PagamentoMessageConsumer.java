package br.com.desafio.produto.entrypoint.work.consumer;

import br.com.desafio.produto.core.usecase.IncrementarQuantidadeProdutoUseCase;
import br.com.desafio.produto.dataprovider.broker.dto.Pagamento;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PagamentoMessageConsumer {

    private final IncrementarQuantidadeProdutoUseCase incrementarQuantidadeProdutoUseCase;

    @RabbitListener(queues = "pagamento.queue")
    public void receiveMessage(Pagamento pagamento) {

        if (pagamento.getStatus().equals("ERROR")) {
            incrementarQuantidadeProdutoUseCase.incrementarQuantidade(pagamento.getProdutos());
        }
    }
}
