package org.angel.repository;

import org.angel.config.DatabaseConfig;
import org.angel.model.Sale;
import org.angel.model.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleRepository {
    public void save(Sale sale) throws SQLException {
        String query = "INSERT INTO sales (description, day) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, sale.getDescription());
            stmt.setString(2, sale.getDay());
            stmt.executeUpdate();
        }
    }

    public List<Sale> findAll() throws SQLException {
        List<Sale> sales = new ArrayList<>();
        String query = "SELECT * FROM sales";
        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Sale p = new Sale();
                p.setIdSales(rs.getInt("idSales"));
                p.setDescription(rs.getString("description"));
                p.setDay(rs.getString("day"));
                sales.add(p);
            }
        }
        return sales;
    }
}
