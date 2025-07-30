package org.angel.controller;

import io.javalin.http.Context;
import org.angel.model.Product;
import org.angel.service.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public void create(Context ctx) {
        try {
            Product product = ctx.bodyAsClass(Product.class);

            productService.create(product);
            ctx.status(201).result("Producto creado");
        } catch (Exception e) {
            ctx.status(400).result("Error al crear producto" + e.getMessage());
        }
    }

    public void getAll(Context ctx) {
        try {
            List<Product> products = productService.getAll();
            ctx.json(products);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ctx.status(500).result("Error al obtener productos");
        }
    }
}
