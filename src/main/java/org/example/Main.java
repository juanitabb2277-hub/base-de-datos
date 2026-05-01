package org.example;

import org.example.model.*;
import org.example.util.ConexionBD;
import org.example.dao.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = ConexionBD.obtenerConexion()) {

            Scanner scanner = new Scanner(System.in);
            int op, op1, op2, op3, op4;

            do {
                System.out.println("\nBienvenido a Lavajuanis!");
                System.out.println("1. Clientes");
                System.out.println("2. Servicios");
                System.out.println("3. Vehiculos");
                System.out.println("4. Registros de lavado");
                System.out.println("5. Salir");
                op = scanner.nextInt();

                switch (op) {
                    case 1:
                        do{
                            ClientesDAO clientesDAO = new ClientesDAOimpl(connection);
                            System.out.println("--->Seleccione:");
                            System.out.println("1. Registrar a un cliente");
                            System.out.println("2. Buscar (leer) un cliente");
                            System.out.println("3. Actualizar datos de un cliente");
                            System.out.println("4. Eliminar a un cliente");
                            System.out.println("5. Lista de los clientes");
                            System.out.println("6. Salir");
                            op1= scanner.nextInt();

                            switch (op1){
                                case 1://registrar
                                    scanner.nextLine();
                                    System.out.println("Nombre/s:");
                                    String nombre= scanner.nextLine();
                                    System.out.println("Apellido/s");
                                    String apellido=scanner.nextLine();
                                    System.out.println("Telefono:");
                                    String telefono=scanner.nextLine();
                                    System.out.println("Email:");
                                    String email=scanner.nextLine();
                                    System.out.println("Dirección:");
                                    String direccion=scanner.nextLine();

                                    Clientes clientes=new Clientes(nombre, apellido, telefono, email, direccion);
                                    clientesDAO.crear(clientes);
                                    System.out.println("---cliente registrado---");
                                    break;

                                case 2://leer
                                    System.out.println("Ingrese el ID del cliente:");
                                    int idleer=scanner.nextInt();
                                    Clientes clientes1= clientesDAO.leer(idleer);
                                    if(clientes1!=null){
                                        System.out.println(clientes1);
                                    }else{
                                        System.out.println("--->Cliente no encontrado<---");
                                    }
                                    break;

                                case 3://actualizar
                                    System.out.print("ID del cliente a actualizar: ");
                                    int idAC = scanner.nextInt();
                                    Clientes clientes2 = clientesDAO.leer(idAC);
                                    if (clientes2 != null) {
                                        scanner.nextLine();
                                        System.out.print("Nuevo nombre: ");
                                        clientes2.setNombre(scanner.nextLine());
                                        System.out.print("Nuevo apellido: ");
                                        clientes2.setApellido(scanner.nextLine());
                                        System.out.print("Nuevo telefono: ");
                                        clientes2.setTelefono(scanner.nextLine());
                                        System.out.print("Nuevo email: ");
                                        clientes2.setEmail(scanner.nextLine());
                                        System.out.print("Nueva direccion: ");
                                        clientes2.setDireccion(scanner.nextLine());

                                        clientesDAO.actualizar(clientes2);
                                        System.out.println("--->Cliente actualizado<---");
                                    } else {
                                        System.out.println("...Cliente no encontrado...");
                                    }
                                    break;

                                case 4://eliminar
                                    System.out.print("ID  del cliente que desea eliminar: ");
                                    int idEliminar = scanner.nextInt();
                                    clientesDAO.eliminar(idEliminar);
                                    System.out.println("--->Cliente eliminado<---");
                                    break;

                                case 5://lista
                                    List<Clientes> clientesList = clientesDAO.listar();
                                    for (Clientes c : clientesList) {
                                        System.out.println(c);
                                    }
                                    break;

                                case 6:
                                    System.out.println("...Saliendo al menu principal...");
                                    break;

                                default:
                                    System.out.println("---> Ingrese una opcion correcta >:");
                            }
                        }while (op1!=6);
                        break;


                    // ================= SERVICIOS =================
                    case 2:
                        ServiciosDAO serviciosDAO = new ServiciosDAOimpl(connection);

                        do {
                            System.out.println("\n--- SERVICIOS ---");
                            System.out.println("1. Registrar");
                            System.out.println("2. Leer");
                            System.out.println("3. Actualizar");
                            System.out.println("4. Eliminar");
                            System.out.println("5. Listar");
                            System.out.println("6. Salir");
                            op2 = scanner.nextInt();

                            switch (op2) {

                                case 1:
                                    scanner.nextLine();
                                    System.out.print("Nombre: ");
                                    String nombre = scanner.nextLine();
                                    System.out.print("Precio: ");
                                    double precio = scanner.nextDouble();

                                    serviciosDAO.crear(new Servicios(nombre, precio));
                                    break;

                                case 2:
                                    System.out.print("ID: ");
                                    Servicios s = serviciosDAO.leer(scanner.nextInt());
                                    System.out.println(s != null ? s : "No encontrado");
                                    break;

                                case 3:
                                    System.out.print("ID: ");
                                    int idS = scanner.nextInt();
                                    Servicios s2 = serviciosDAO.leer(idS);

                                    if (s2 != null) {
                                        scanner.nextLine();
                                        System.out.print("Nuevo nombre: ");
                                        s2.setNombre(scanner.nextLine());
                                        System.out.print("Nuevo precio: ");
                                        s2.setPrecio(scanner.nextDouble());

                                        serviciosDAO.actualizar(s2);
                                    }
                                    break;

                                case 4:
                                    System.out.print("ID: ");
                                    serviciosDAO.eliminar(scanner.nextInt());
                                    break;

                                case 5:
                                    List<Servicios> listaS = serviciosDAO.listar();
                                    listaS.forEach(System.out::println);
                                    break;

                            }

                        } while (op2 != 6);
                        break;

                    // ================= VEHICULOS =================
                    case 3:
                        VehiculosDAO vehiculosDAO = new VEHICULOSDAOimpl(connection);

                        do {
                            System.out.println("\n--- VEHICULOS ---");
                            System.out.println("1. Registrar");
                            System.out.println("2. Leer");
                            System.out.println("3. Actualizar");
                            System.out.println("4. Eliminar");
                            System.out.println("5. Listar");
                            System.out.println("6. Salir");
                            op3 = scanner.nextInt();

                            switch (op3) {

                                case 1:
                                    System.out.print("ID Cliente: ");
                                    int idc = scanner.nextInt();
                                    scanner.nextLine();

                                    System.out.print("Marca: ");
                                    String marca = scanner.nextLine();
                                    System.out.print("Modelo: ");
                                    String modelo = scanner.nextLine();
                                    System.out.print("Placa: ");
                                    String placa = scanner.nextLine();
                                    System.out.print("Color: ");
                                    String color = scanner.nextLine();
                                    System.out.print("Tipo: ");
                                    String tipo = scanner.nextLine();

                                    vehiculosDAO.crear(new Vehiculos(idc, marca, modelo, placa, color, tipo));
                                    break;

                                case 2:
                                    System.out.print("ID: ");
                                    Vehiculos v = vehiculosDAO.leer(scanner.nextInt());
                                    System.out.println(v != null ? v : "No encontrado");
                                    break;

                                case 3:
                                    System.out.print("ID: ");
                                    int idV = scanner.nextInt();
                                    Vehiculos v2 = vehiculosDAO.leer(idV);

                                    if (v2 != null) {
                                        scanner.nextLine();
                                        System.out.print("Marca: ");
                                        v2.setMarca(scanner.nextLine());
                                        System.out.print("Modelo: ");
                                        v2.setModelo(scanner.nextLine());
                                        System.out.print("Color: ");
                                        v2.setColor(scanner.nextLine());

                                        vehiculosDAO.actualizar(v2);
                                    }
                                    break;

                                case 4:
                                    System.out.print("ID: ");
                                    vehiculosDAO.eliminar(scanner.nextInt());
                                    break;

                                case 5:
                                    vehiculosDAO.listar().forEach(System.out::println);
                                    break;

                            }

                        } while (op3 != 6);
                        break;

                    // ================= REGISTROS =================
                    case 4:
                        RegistroLavadoDAO registroDAO = new RegistrosLavadoDAOimpl(connection) {
                        };

                        do {
                            System.out.println("\n--- REGISTROS ---");
                            System.out.println("1. Registrar");
                            System.out.println("2. Leer");
                            System.out.println("3. Actualizar");
                            System.out.println("4. Eliminar");
                            System.out.println("5. Listar");
                            System.out.println("6. Salir");
                            op4 = scanner.nextInt();

                            switch (op4) {

                                case 1:
                                    System.out.print("ID Vehiculo: ");
                                    int idv = scanner.nextInt();

                                    System.out.print("ID Servicio: ");
                                    int ids = scanner.nextInt();

                                    scanner.nextLine();
                                    System.out.print("Fecha (YYYY-MM-DD): ");
                                    LocalDate fecha = LocalDate.parse(scanner.nextLine());

                                    System.out.print("Hora inicio (HH:MM): ");
                                    LocalTime hi = LocalTime.parse(scanner.nextLine());

                                    System.out.print("Hora fin (HH:MM): ");
                                    LocalTime hf = LocalTime.parse(scanner.nextLine());

                                    System.out.print("Precio: ");
                                    double p = scanner.nextDouble();

                                    registroDAO.crear(new Registroslavado(idv, ids, fecha, hi, hf, p));
                                    break;

                                case 2:
                                    System.out.print("ID: ");
                                    System.out.println(registroDAO.leer(scanner.nextInt()));
                                    break;

                                case 3:
                                    System.out.println("Actualizar no implementado completamente");
                                    break;

                                case 4:
                                    System.out.print("ID: ");
                                    registroDAO.eliminar(scanner.nextInt());
                                    break;

                                case 5:
                                    registroDAO.listar().forEach(System.out::println);
                                    break;
                            }

                        } while (op4 != 6);
                        break;

                    case 5:
                        System.out.println("Saliendo...");
                        break;
                }

            } while (op != 5);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}