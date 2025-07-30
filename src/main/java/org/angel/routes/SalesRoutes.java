package org.angel.routes;

import io.javalin.Javalin;
import org.angel.controller.SaleController;

public class SalesRoutes {
    private final SaleController saleController;
    public SalesRoutes(SaleController userController) {
        this.saleController = userController;
    }
    public void register(Javalin app) {
        app.get("/sales", saleController::getAll);
        app.post("/sales", saleController::create);
    }
}
