package org.angel.repository;

import org.angel.config.DatabaseConfig;
import org.angel.model.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoRepository {

    public void save(Alumno alumno) throws SQLException {
        String query = "INSERT INTO alumnos (nombre, apellido, edad, matricula) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, alumno.getNombre());
            stmt.setString(2, alumno.getApellido());
            stmt.setInt(3, alumno.getEdad());
            stmt.setString(4, alumno.getMatricula());
            stmt.executeUpdate();
        }
    }

    public List<Alumno> findAll() throws SQLException {
        List<Alumno> alumno = new ArrayList<>();
        String query = "SELECT * FROM alumnos";
        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Alumno A = new Alumno();
                A.setId(rs.getInt("id_alumno"));
                A.setNombre(rs.getString("nombre"));
                A.setApellido(rs.getString("apellido"));
                A.setEdad(rs.getInt("edad"));
                A.setMatricula(rs.getString("matricula"));
                alumno.add(A);
            }
        }
        return alumno;
    }
}
