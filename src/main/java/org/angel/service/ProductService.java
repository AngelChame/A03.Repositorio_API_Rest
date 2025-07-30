package org.angel.service;

import org.angel.model.Product;
import org.angel.repository.ProductRepository;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private final ProductRepository productRepo;
    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public void create(Product product) throws SQLException {
        // Podrías validar aquí si el email ya existe, etc.
        productRepo.save(product);
    }

    public List<Product> getAll() throws SQLException {
        return productRepo.findAll();
    }
}
