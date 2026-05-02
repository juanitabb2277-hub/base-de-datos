package org.example.dao;
import org.example.model.Clientes;

import java.util.List;


public interface ClientesDAO {
    void crear(Clientes clientes);
    Clientes leer(int idCliente);
    void actualizar(Clientes clientes);
    void eliminar(int codigo);
    List<Clientes> listar();
}
