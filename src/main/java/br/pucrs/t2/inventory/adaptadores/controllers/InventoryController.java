package br.pucrs.t2.inventory.adaptadores.controllers;

import br.pucrs.t2.inventory.negocio.entities.ItemEstoque;
import br.pucrs.t2.inventory.negocio.services.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class InventoryController {

    private InventoryService inventoryService;

    @GetMapping("/autorizacao")
    @CrossOrigin(origins = "*")
    public boolean podeVender(@RequestParam final long codProd,
                              @RequestParam final int qtdade) {
        return inventoryService.podeVender(codProd, qtdade);
    }

    @PostMapping("/confirmacao")
    @CrossOrigin(origins = "*")
    public boolean confirmaVenda(@RequestBody final ItemEstoque[] itens) {
        inventoryService.confirmaVenda(itens);
        return true;
    }

    @PostMapping("/subtotal")
    @CrossOrigin(origins = "*")
    public Integer[] calculaSubtotal(@RequestBody final ItemEstoque[] itens) {
        return inventoryService.calculaSubtotal(itens);
    }
}
