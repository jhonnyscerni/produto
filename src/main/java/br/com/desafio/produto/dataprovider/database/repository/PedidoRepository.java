package br.com.desafio.produto.dataprovider.database.repository;

import br.com.desafio.produto.dataprovider.database.entity.PedidoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends MongoRepository<PedidoEntity, String> {

    List<PedidoEntity> findByCarrinhoId(String carrinhoId);
}