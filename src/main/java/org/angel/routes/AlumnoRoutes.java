package org.angel.routes;

import io.javalin.Javalin;
import org.angel.controller.AlumnoController;

public class AlumnoRoutes {

    private final AlumnoController alumnoController;
    public AlumnoRoutes(AlumnoController alumnoController) {
        this.alumnoController = alumnoController;
    }
    public void register(Javalin app) {
        app.get("/alumno", alumnoController::getAll);
        app.post("/alumno", alumnoController::create);
        //app.get("/products/{id}", productController::getById);
        // app.put("/products/:id", productController::update);
        // app.delete("/products/:id", productController::delete);
    }
}
