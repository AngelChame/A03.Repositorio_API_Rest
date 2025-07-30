package org.angel.controller;

import io.javalin.http.Context;
import org.angel.model.Alumno;
import org.angel.service.AlumnoService;

import java.sql.SQLException;
import java.util.List;

public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    public void create(Context ctx) {
        try {
            Alumno alumno = ctx.bodyAsClass(Alumno.class);

            alumnoService.create(alumno);
            ctx.status(201).result("Producto creado");
        } catch (Exception e) {
            ctx.status(400).result("Error al crear producto" + e.getMessage());
        }
    }

    public void getAll(Context ctx) {
        try {
            List<Alumno> alumno = alumnoService.getAll();
            ctx.json(alumno);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ctx.status(500).result("Error al obtener productos");
        }
    }
}
