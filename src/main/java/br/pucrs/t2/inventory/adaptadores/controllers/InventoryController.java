package br.pucrs.t2.inventory.adaptadores.controllers;

import br.pucrs.t2.inventory.negocio.entities.ItemEstoque;
import br.pucrs.t2.inventory.negocio.services.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class InventoryController {

    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PutMapping("/salvar")
    @CrossOrigin(origins = "*")
    public void save(@RequestBody ItemEstoque itemEstoque) {
        inventoryService.save(itemEstoque);
    }

    @GetMapping("/{codProd}")
    @CrossOrigin(origins = "*")
    public ItemEstoque podeVender(@PathVariable final long codProd) {
        return inventoryService.getItemEstoque(codProd);
    }
}
