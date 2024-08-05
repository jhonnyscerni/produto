package br.com.desafio.produto.dataprovider.broker.gateway.impl;

import br.com.desafio.produto.core.domain.Pedido;
import br.com.desafio.produto.core.gateway.PedidoMessageGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PedidoMessageGatewayImpl implements PedidoMessageGateway {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(Pedido pedido) {
        rabbitTemplate.convertAndSend("pedido.queue", pedido);
    }
}
