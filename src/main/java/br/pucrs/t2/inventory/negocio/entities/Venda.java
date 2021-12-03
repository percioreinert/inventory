package br.pucrs.t2.inventory.negocio.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class Venda {

    private long numero;
    private LocalDateTime dateTime;
    private List<ItemVenda> itemVendas;

    public Venda(LocalDateTime dateTime, List<ItemVenda> itemVendas){
        this.dateTime = dateTime;
        this.itemVendas = itemVendas;
    }

    public long getNumero(){return numero;}
    public LocalDateTime getDateTime(){return dateTime;}
    public List<ItemVenda> getItemVendas(){return itemVendas;}
    public void setItemVendas(List<ItemVenda> itemVendas){ this.itemVendas = itemVendas;}

}

