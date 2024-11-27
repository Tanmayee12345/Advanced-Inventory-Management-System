package com.example.Guvi_Project.service;



import com.example.Guvi_Project.mode.item;
import com.example.Guvi_Project.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public item addItem(item item) {
        return repository.save(item);
    }

    public Optional<item> getItemById(String id) {
        return repository.findById(id);
    }

    public void deleteItem(String id) {
        repository.deleteById(id);
    }

    public List<item> getItemsByCategory(String category) {
        return repository.findByCategoryOrderByQuantityDesc(category);
    }

    public List<item> getTopKItems(int k) {
        return repository.findAll().stream()
                .sorted((a, b) -> Integer.compare(b.getQuantity(), a.getQuantity()))
                .limit(k)
                .collect(Collectors.toList());
    }

    public void restockCheck(item item, int threshold) {
        if (item.getQuantity() < threshold) {
            System.out.println("Restock Alert: Item ID " + item.getId() + " is below the threshold.");
        }
    }

    public void mergeInventories(List<item> inventoryA, List<item> inventoryB) {
        Map<String, item> merged = new HashMap<>();
        for (item item : inventoryA) {
            merged.put(item.getId(), item);
        }
        for (item item : inventoryB) {
            merged.merge(item.getId(), item, (existing, newItem) ->
                    existing.getQuantity() > newItem.getQuantity() ? existing : newItem);
        }
        repository.saveAll(merged.values());
    }
}

