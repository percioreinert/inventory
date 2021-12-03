package br.pucrs.t2.inventory.adaptadores.repositories;

import br.pucrs.t2.inventory.negocio.entities.ItemEstoque;
import br.pucrs.t2.inventory.negocio.repositories.IEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EstoqueRepository implements IEstoqueRepository {

    private IEstoqueRepositoryJPA repository;

    @Autowired
    public EstoqueRepository(IEstoqueRepositoryJPA repository) {
        this.repository = repository;
    }

    @Override
    public ItemEstoque findByProdutoCodigo(long codigo) {
        return repository.findByProdutoCodigo(codigo);
    }

    @Override
    public int findQuantidadeByProduto(long codigo) {
        return repository.findQuantidadeByProduto(codigo);
    }

    @Override
    public Iterable<ItemEstoque> findAll() {
        return repository.findAll();
    }

    @Override
    public ItemEstoque save(ItemEstoque item) {
        return repository.save(item);
    }

    @Override
    public boolean saveAll(List<ItemEstoque> itens) {
        return repository.saveAll(itens) != null;
    }

    @Override
    public void delete(ItemEstoque item) {
        repository.delete(item);
    }
}

