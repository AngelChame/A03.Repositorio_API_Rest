package org.EdwarDa2.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.EdwarDa2.model.User;
import org.EdwarDa2.service.UserService;

import java.sql.SQLException;
import java.util.List;

//actualizado
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void getAll(Context ctx) {
        try {
            List<User> totals = userService.getAllUsuario();
            ctx.json(totals);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener el Usuario: " + e.getMessage());
        }
    }

    public void getById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id_user"));
            User user = userService.getByIdUser(id);
            if (user != null) {
                ctx.json(user);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("Total no encontrado");
            }
        } catch (Exception e) {
            ctx.status(404).result("Error al obtener totales: " + e.getMessage());
        }
    }

    public void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            User user = ctx.bodyAsClass(User.class);
            if (user.getId_usuario() != id) {
                ctx.status(400).result("ID en la URL no coincide con el ID en el cuerpo de la solicitud.");
                return;
            }
            userService.updateUser(user);
            ctx.status(200).result("Total actualizado exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de total inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al actualizar total: " + e.getMessage());
        }
    }
    public void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            userService.deleteUser(id);
            ctx.status(204).result("total eliminado exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de total inválido");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al eliminar total: " + e.getMessage());
        }
    }
}