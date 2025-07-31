package org.EdwarDa2.routes;

import io.javalin.Javalin;
import org.EdwarDa2.controller.UserController;

public class UsuarioRoutes {
    private final UserController userController;
    public UsuarioRoutes(UserController userController) {this.userController = userController;}
    public void register(Javalin app) {
        app.get("/Usuarios", userController::getAll);
        app.get("/Usuarios/{id_Usuarios}", userController::getById);
        app.put("/Usuarios/{id}", userController::update);
        app.delete("/Usuarios/{id}", userController::delete);
    }
}
