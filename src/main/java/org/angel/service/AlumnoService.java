package org.angel.service;

import org.angel.model.Alumno;
import org.angel.model.Product;
import org.angel.repository.AlumnoRepository;

import java.sql.SQLException;
import java.util.List;

public class AlumnoService {

    private final AlumnoRepository alumnoRepo;
    public AlumnoService(AlumnoRepository alumnoRepo) {
        this.alumnoRepo = alumnoRepo;
    }

    public void create(Alumno alumno) throws SQLException {
        // Podrías validar aquí si el email ya existe, etc.
        alumnoRepo.save(alumno);
    }

    public List<Alumno> getAll() throws SQLException {
        return alumnoRepo.findAll();
    }
}
