package org.angel.routes;

import io.javalin.Javalin;
import org.angel.controller.ProductController;

public class ProductsRoutes {
    private final ProductController productController;
    public ProductsRoutes(ProductController productController) {
        this.productController = productController;
    }
    public void register(Javalin app) {
        app.get("/producto", productController::getAll);
        app.post("/producto", productController::create);
        //app.get("/products/{id}", productController::getById);
        // app.put("/products/:id", productController::update);
        // app.delete("/products/:id", productController::delete);
    }
}
