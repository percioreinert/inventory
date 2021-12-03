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

    public boolean confirmaVenda(ItemEstoque[] itens) {
        ArrayList<ItemVenda> listaItemVenda = new ArrayList<>();
        ArrayList<ItemEstoque> listaItemEstoque = new ArrayList<>();
        Venda venda = new Venda(LocalDateTime.now(), listaItemVenda);
        for (ItemEstoque item : itens) {

            if(item.getProduto() == null)
                return false;

            long codigoProduto = item.getCodigo();
            int quantidade = item.getQuantidade();

            if(quantidade <= 0)
                return false;

            ItemEstoque itemEstoque = listaItemEstoque.stream().filter(y -> y.getCodigo() == codigoProduto).findFirst().get().orElseThrow(null);

            if(itemEstoque == null || itemEstoque.getProduto() == null){
                itemEstoque = getItemEstoque(codigoProduto);

                if(itemEstoque == null || itemEstoque.getProduto() == null)
                    return false;

                listaItemEstoque.add(itemEstoque);
            }

            quantidade = itemEstoque.getQuantidade() - quantidade;

            if(quantidade >= 0)
                itemEstoque.setQuantidade(quantidade);
            else
                return false;

            ItemVenda itemLista = listaItemVenda.stream().filter(x -> x.getCodigo() == codigoProduto).findFirst().get().orElseThrow(null);

            if(itemLista != null)
                itemLista.setQtdProduto(quantidade);
            else{
                itemLista = new ItemVenda(quantidade, itemEstoque.getProduto().getPreco(), IMPOSTO, itemEstoque.getProduto(), venda);
                listaItemVenda.add(itemLista);
            }
        }
        // ver como faz a chamada para o microserviço de vendas
        //vendaRepository.save(venda);
        save(listaItemEstoque);
        return true;
    }

    public Integer[] calculaSubtotal(ItemEstoque[] itens) {
        int subtotal = 0;
        int imposto = 0;
        for (ItemEstoque it : itens) {
            validaItemEstoque(it, 0);
            int quantidade = it.getQuantidade();
            it = estoqueService.getItemEstoque(it.getCodigo());
            validaItemEstoque(it, quantidade);
            subtotal += (int) (it.getProduto().getPreco() * quantidade);

        }
        imposto = (int)(subtotal * (IMPOSTO / 100.0));
        final Integer[] resp = new Integer[3];
        resp[0] = subtotal;
        resp[1] = imposto;
        resp[2] = subtotal + imposto;
        return resp;
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
