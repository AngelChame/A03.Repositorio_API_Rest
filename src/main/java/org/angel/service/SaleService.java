package org.angel.service;

import org.angel.model.Sale;
import org.angel.repository.SaleRepository;


import java.sql.SQLException;
import java.util.List;

public class SaleService {
    private final SaleRepository saleRepo;
    public SaleService(SaleRepository saleRepo) {
        this.saleRepo = saleRepo;
    }

    public void create(Sale sale) throws SQLException {
        // Podrías validar aquí si el email ya existe, etc.
        saleRepo.save(sale);
    }

    public List<Sale> getAll() throws SQLException {
        return saleRepo.findAll();
    }
}
