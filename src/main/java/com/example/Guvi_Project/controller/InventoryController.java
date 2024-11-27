package com.example.Guvi_Project.controller;



import com.example.Guvi_Project.mode.item;
import com.example.Guvi_Project.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public ResponseEntity<item> addItem(@RequestBody item item) {
        return ResponseEntity.ok(service.addItem(item));
    }

    @GetMapping("/{id}")
    public ResponseEntity<item> getItem(@PathVariable String id) {
        return service.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        service.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<item>> getItemsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(service.getItemsByCategory(category));
    }

    @GetMapping("/top/{k}")
    public ResponseEntity<List<item>> getTopKItems(@PathVariable int k) {
        return ResponseEntity.ok(service.getTopKItems(k));
    }
}

