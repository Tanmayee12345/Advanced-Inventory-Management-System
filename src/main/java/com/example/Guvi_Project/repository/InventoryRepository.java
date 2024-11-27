package com.example.Guvi_Project.repository;

import com.example.Guvi_Project.mode.item;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InventoryRepository extends MongoRepository<item, String> {
    List<item> findByCategoryOrderByQuantityDesc(String category);
}
