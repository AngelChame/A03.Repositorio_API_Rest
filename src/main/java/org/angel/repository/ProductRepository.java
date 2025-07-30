package org.angel.repository;

import org.angel.config.DatabaseConfig;
import org.angel.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    public void save(Product product) throws SQLException {
        String query = "INSERT INTO producto (nombre, descripcion, stock, precio) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, product.getNombre());
            stmt.setString(2, product.getDescripcion());
            stmt.setInt(3, product.getStock());
            stmt.setFloat(4, product.getPrecio());
            stmt.executeUpdate();
        }
    }

    public List<Product> findAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM producto";
        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setStock(rs.getInt("stock"));
                p.setPrecio(rs.getFloat("precio"));
                products.add(p);
            }
        }
        return products;
    }
}
