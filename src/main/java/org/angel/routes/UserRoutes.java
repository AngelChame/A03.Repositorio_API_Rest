package org.angel.routes;

import io.javalin.Javalin;
import org.angel.controller.UserController;

public class UserRoutes {
    private final UserController userController;
    public UserRoutes(UserController userController) {
        this.userController = userController;
    }
    public void register(Javalin app) {
        app.get("/users", userController::getAll);
        app.post("/users", userController::create);
        app.get("/users/{id}", userController::getById);
        // Ejemplo de más rutas:
        // app.put("/users/:id", userController::update);
        // app.delete("/users/:id", userController::delete);
    }
}
