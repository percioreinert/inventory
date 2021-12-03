package br.pucrs.t2.inventory.negocio.repositories;

import br.pucrs.t2.inventory.negocio.entities.ItemEstoque;

import java.util.List;

public interface IEstoqueRepository {

    Iterable<ItemEstoque> findAll();
    int findQuantidadeByProduto(long codigo);
    ItemEstoque findByProdutoCodigo(long codigo);
    ItemEstoque save(ItemEstoque item);
    boolean saveAll(List<ItemEstoque> itens);
    void delete(ItemEstoque item);
}
