package org.EdwarDa2.repository;
import org.EdwarDa2.DTO.comandas.ComandaRequestDTO;
import org.EdwarDa2.DTO.comandas.DetalleComandaDTO;
import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Comanda;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ComandaRepository {
    public List<ComandaRequestDTO> findAll() throws SQLException {
        List<ComandaRequestDTO> lista = new ArrayList<>();

        String comandaQuery ="SELECT * FROM comandas";
        String detalleQuery ="SELECT d.id_comanda, d.id_producto, d.cantidad, d.id_detallecomanda,d.comentario,p.nombre \n" +
                "\t FROM detalle_comandas d \n" +
                "\t JOIN productos p  ON d.id_producto = p.id_producto;";

        Map<Integer, ArrayList<DetalleComandaDTO>> productosPorComanda = new HashMap<>();

        try (Connection conn = DatabaseConfig.getDataSource().getConnection()) {


            try (PreparedStatement detalleStmt = conn.prepareStatement(detalleQuery);
                 ResultSet drs = detalleStmt.executeQuery()) {
                while (drs.next()) {
                    int idComanda = drs.getInt("id_comanda");
                    DetalleComandaDTO detalle = new DetalleComandaDTO();
                    detalle.setId_comanda(drs.getInt("id_comanda"));
                    detalle.setId_producto(drs.getInt("id_producto"));
                    detalle.setCantidad(drs.getInt("cantidad"));
                    detalle.setId_detalle(drs.getInt("id_detallecomanda"));
                    detalle.setNombreProducto(drs.getString("nombre"));
                    detalle.setComentario(drs.getString("comentario"));
                    productosPorComanda
                            .computeIfAbsent(idComanda, k -> new ArrayList<>())
                            .add(detalle);
                }
            }

            try (PreparedStatement comandaStmt = conn.prepareStatement(comandaQuery);
                 ResultSet rs = comandaStmt.executeQuery()) {

                while (rs.next()) {
                    int id_comanda = rs.getInt("id_comanda");
                    int id_mesa = rs.getInt("id_mesa");
                    Timestamp fecha = Timestamp.valueOf(rs.getTimestamp("fecha_hora").toLocalDateTime());

                    ArrayList<DetalleComandaDTO> productos = productosPorComanda.getOrDefault(id_comanda, new ArrayList<>());

                    ComandaRequestDTO dto = new ComandaRequestDTO(
                            id_comanda,
                            id_mesa,
                            fecha.toLocalDateTime(),
                            productos
                    );

                    lista.add(dto);
                }
            }
        }
        return lista;
    }



    public ComandaRequestDTO findById_comanda(int id_comanda) throws SQLException {
        ComandaRequestDTO dto = null;
        String comandaQuery = "SELECT * FROM comandas WHERE id_comanda = ?";
        String detalleQuery = "SELECT p.nombre AS nombreProducto, p.precio " +
                "FROM detalle_comandas dc " +
                "JOIN productos p ON dc.id_producto = p.id_producto " +
                "WHERE dc.id_comanda = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection()) {
            // Obtener datos de la comanda
            try (PreparedStatement stmt = conn.prepareStatement(comandaQuery)) {
                stmt.setInt(1, id_comanda);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        dto = new ComandaRequestDTO();
                        dto.setId_comanda(rs.getInt("id_comanda"));
                        dto.setId_mesa(rs.getInt("id_mesa"));
                        dto.setFecha_hora(rs.getTimestamp("fecha_hora").toLocalDateTime());
                        dto.setListaProductos(new ArrayList<>());
                    }
                }
            }

            // Obtener productos asociados
            if (dto != null) {
                try (PreparedStatement stmt2 = conn.prepareStatement(detalleQuery)) {
                    stmt2.setInt(1, id_comanda);
                    try (ResultSet rs2 = stmt2.executeQuery()) {
                        while (rs2.next()) {
                            DetalleComandaDTO prod = new DetalleComandaDTO();
                            prod.setNombreProducto(rs2.getString("nombreProducto"));
                            prod.setPrecio(rs2.getFloat("precio"));
                            dto.getListaProductos().add(prod);
                        }
                    }
                }
            }
        }

        return dto;
    }
    public int save(ComandaRequestDTO comanda) throws SQLException {
        String insertComanda = "INSERT INTO comandas (id_mesa, fecha_hora) VALUES (?, ?)";
        String insertDetalle = "INSERT INTO detalle_comandas (id_comanda, id_producto, cantidad, comentario) VALUES (?, ?, ?, ?)";
        int idGenerado;

        try (Connection conn = DatabaseConfig.getDataSource().getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement stmt = conn.prepareStatement(insertComanda, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, comanda.getId_mesa());
                LocalDateTime fecha = comanda.getFecha_hora() != null ? comanda.getFecha_hora() : LocalDateTime.now();
                stmt.setTimestamp(2, Timestamp.valueOf(fecha));
                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                } else {
                    conn.rollback();
                    throw new SQLException("No se generó ID de comanda.");
                }
            }

            try (PreparedStatement detalleStmt = conn.prepareStatement(insertDetalle)) {
                for (DetalleComandaDTO producto : comanda.getListaProductos()) {
                    detalleStmt.setInt(1, idGenerado);
                    detalleStmt.setInt(2, producto.getId_producto());
                    detalleStmt.setInt(3, producto.getCantidad());
                    detalleStmt.setString(4, producto.getComentario());
                    detalleStmt.addBatch();
                }
                detalleStmt.executeBatch();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return idGenerado;
    }


    public void delete(int id_comanda) throws SQLException {
        String deleteDetalle = "DELETE FROM detalle_comandas WHERE id_comanda = ?";
        String deleteComanda = "DELETE FROM comandas WHERE id_comanda = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción

            try (PreparedStatement stmtDetalle = conn.prepareStatement(deleteDetalle)) {
                stmtDetalle.setInt(1, id_comanda);
                stmtDetalle.executeUpdate();
            }

            try (PreparedStatement stmtComanda = conn.prepareStatement(deleteComanda)) {
                stmtComanda.setInt(1, id_comanda);
                stmtComanda.executeUpdate();
            }

            conn.commit(); // Confirmar cambios
        } catch (SQLException e) {
            e.printStackTrace(); // 👈 imprime el error en consola del backend
            throw e;
        }
    }
}











