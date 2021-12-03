package br.pucrs.t2.inventory.negocio.services;

import br.pucrs.t2.inventory.adaptadores.repositories.EstoqueRepository;
import br.pucrs.t2.inventory.negocio.entities.ItemEstoque;
import br.pucrs.t2.inventory.negocio.entities.ItemVenda;
import br.pucrs.t2.inventory.negocio.entities.Venda;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

    private static long IMPOSTO = 10L;
    private EstoqueRepository estoqueRepository;

    public InventoryService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public boolean podeVender(Long codProd, Integer qtdade) {
        ItemEstoque item = estoqueRepository.findByProdutoCodigo(codProd);
        if(item == null) return false;
        int quantidade = item.getQuantidade();

        return quantidade >= qtdade;
    }
    public int getQuantidade(Long codProd){
        ItemEstoque item = estoqueRepository.findByProdutoCodigo(codProd);
        if(item == null) return -1;
        return item.getQuantidade();
    }
    public ItemEstoque getItemEstoque(Long codPro){
        return estoqueRepository.findByProdutoCodigo(codPro);
    }

    public ItemEstoque save(ItemEstoque item){
        return estoqueRepository.save(item);
    }

    public boolean save(List<ItemEstoque> itens){
        return estoqueRepository.saveAll(itens);
    }
    public List<ItemEstoque> findAll() {
        Iterable<ItemEstoque> estoque = estoqueRepository.findAll();
        List<ItemEstoque> itens = new ArrayList<>();

        for (ItemEstoque p : estoque) {
            itens.add(p);
        }
        return itens;
    }

}
