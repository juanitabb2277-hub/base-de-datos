package org.example.dao;
import org.example.model.Registroslavado;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
public class RegistrosLavadoDAOimpl implements RegistroLavadoDAO {
    private final Connection connection;

    public RegistrosLavadoDAOimpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void crear(Registroslavado r) {
        String sql = "INSERT INTO registroslavado (vehiculoid, servicioid, fechalavado, horainicio, horafin, preciototal) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, r.getId_vehiculo());
            st.setInt(2, r.getId_servicio());

            // 🔥 Conversión correcta
            st.setDate(3, Date.valueOf(r.getFechaLavado()));
            st.setTime(4, Time.valueOf(r.getHoraInicio()));
            st.setTime(5, Time.valueOf(r.getHoraFin()));

            st.setDouble(6, r.getPrecioTotal());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Registroslavado leer(int id) {
        String sql = "SELECT * FROM registroslavado WHERE registroid=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                Registroslavado r = new Registroslavado(
                        rs.getInt("vehiculoid"),
                        rs.getInt("servicioid"),

                        // 🔥 Conversión inversa
                        rs.getDate("fechalavado").toLocalDate(),
                        rs.getTime("horainicio").toLocalTime(),
                        rs.getTime("horafin").toLocalTime(),

                        rs.getDouble("preciototal")
                );

                return r;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void actualizar(Registroslavado r) {
        String sql = "UPDATE registroslavado SET vehiculoid=?, servicioid=?, fechalavado=?, horainicio=?, horafin=?, preciototal=? WHERE registroid=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, r.getId_vehiculo());
            st.setInt(2, r.getId_servicio());
            st.setDate(3, Date.valueOf(r.getFechaLavado()));
            st.setTime(4, Time.valueOf(r.getHoraInicio()));
            st.setTime(5, Time.valueOf(r.getHoraFin()));
            st.setDouble(6, r.getPrecioTotal());

            // ⚠️ si luego agregas ID en el modelo, aquí va
            st.setInt(7, 1); // ← cámbialo por r.getId() si lo agregas

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM registroslavado WHERE registroid=?";

        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Registroslavado> listar() {
        List<Registroslavado> lista = new ArrayList<>();
        String sql = "SELECT * FROM registroslavado";

        try (PreparedStatement st = connection.prepareStatement(sql)) {

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                Registroslavado r = new Registroslavado(
                        rs.getInt("vehiculoid"),
                        rs.getInt("servicioid"),
                        rs.getDate("fechalavado").toLocalDate(),
                        rs.getTime("horainicio").toLocalTime(),
                        rs.getTime("horafin").toLocalTime(),
                        rs.getDouble("preciototal")
                );

                lista.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}

