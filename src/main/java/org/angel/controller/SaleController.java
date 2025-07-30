package org.angel.controller;

import io.javalin.http.Context;
import org.angel.model.Sale;
import org.angel.service.SaleService;

import java.sql.SQLException;
import java.util.List;

public class SaleController {
    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    public void create(Context ctx) {
        try {
            Sale sale = ctx.bodyAsClass(Sale.class);

            saleService.create(sale);
            ctx.status(201).result("Venta creado");
        } catch (Exception e) {
            ctx.status(400).result("Error al crear venta" + e.getMessage());
        }
    }

    public void getAll(Context ctx) {
        try {
            List<Sale> sales = saleService.getAll();
            ctx.json(sales);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ctx.status(500).result("Error al obtener ventas" + e.getMessage());
        }
    }
}
