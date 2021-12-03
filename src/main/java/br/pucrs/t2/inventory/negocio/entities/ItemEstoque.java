package br.pucrs.t2.inventory.negocio.entities;

import javax.persistence.*;

@Table(name = "item_estoques")
@Entity(name = "ItemEstoque")
public class ItemEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "cod_produto")
    private Produto produto;

    private int quantidade;

    public ItemEstoque() {}

    public ItemEstoque(Produto produto, int quantidade) {
        this.quantidade = quantidade;
        this.produto=produto;
    }

    public int getQuantidade(){return this.quantidade;}
    public long getId(){return this.id;}

    public long getCodigo() {
        return produto.getCodigo();
    }

    public void setQuantidade(int qtdProduto){this.quantidade=qtdProduto;}

    public Produto getProduto(){return this.produto;}

    public ItemEstoque orElseThrow(Object object) {
        return null;
    }
}

