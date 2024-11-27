package com.example.Guvi_Project.mode;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "items")
public class item {

    @Id
    private String id;
    private String name;
    private String category;
    private int quantity;
}

