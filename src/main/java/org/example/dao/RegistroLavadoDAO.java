package org.example.dao;

import org.example.model.Registroslavado;

import java.util.List;

public interface RegistroLavadoDAO {
    void crear(Registroslavado r);
    Registroslavado leer(int id);
    void actualizar(Registroslavado r);
    void eliminar(int id);
    List<Registroslavado> listar();
}