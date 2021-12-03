package br.pucrs.t2.inventory.negocio.entities;

import javax.persistence.*;

public class ItemVenda {

    private Long id;
    private Venda venda;
    private int qtdProduto;
    private int precoUnit;
    private long imposto;
    private Produto produto;

    public ItemVenda(int qtdProduto, int precoUnit, long imposto, Produto produto, Venda venda) {
        this.qtdProduto = qtdProduto;
        this.precoUnit = precoUnit;
        this.imposto = imposto;
        this.produto = produto;
        this.venda = venda;
    }

    public ItemVenda(){}
    public long getImposto() {
        return imposto;
    }
    public void setImposto(long imposto) {
        this.imposto = imposto;
    }
    public int getPrecoUnit() {
        return precoUnit;
    }
    public void setPrecoUnit(int precoUnit) {
        this.precoUnit = precoUnit;
    }
    public int getQtdProduto(){return this.qtdProduto;}

    public long getCodigo() {
        return produto.getCodigo();
    }

    public Produto getProduto(){return this.produto;}
    public void setQtdProduto(int qtdProduto){this.qtdProduto = qtdProduto;}

    public ItemVenda orElseThrow(Object object) {
        return null;
    }
}
