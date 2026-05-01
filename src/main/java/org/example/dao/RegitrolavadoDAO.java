package org.example.dao;
import org.example.model.Registroslavado;
import java.util.List;

import java.awt.*;

public interface RegitrolavadoDAO {
    void crear(Registroslavado r);
    Registroslavado leer(int id);
    void actualizar(Registroslavado r);
    void eliminar(int id);
    List<Registroslavado>listar();
}
