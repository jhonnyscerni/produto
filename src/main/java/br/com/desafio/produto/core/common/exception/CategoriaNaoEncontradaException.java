package br.com.desafio.produto.core.common.exception;


public class CategoriaNaoEncontradaException extends RuntimeException {

    public CategoriaNaoEncontradaException(String categoriaId) {
        super("Categoria não encontrada com o ID: " + categoriaId);
    }
}