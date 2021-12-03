package br.pucrs.t2.inventory.adaptadores.repositories;

import br.pucrs.t2.inventory.negocio.entities.ItemEstoque;
import org.springframework.data.repository.CrudRepository;

public interface IEstoqueRepositoryJPA extends CrudRepository<ItemEstoque, Integer> {

    ItemEstoque findByProdutoCodigo(long codigo);
    int findQuantidadeByProduto(long codigo);
}
