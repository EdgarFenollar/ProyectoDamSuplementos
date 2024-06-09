package managers;

import DBManager.DBManager;
import categorias.Categoria;
import clientes.Cliente;
import clientes.EnumTipoCliente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase ClienteManager gestiona las operaciones relacionadas con los clientes,
 * incluyendo la obtención, adición, actualización y eliminación de clientes en la base de datos.
 */
public class ClienteManager {
    public static List<Cliente> clientes = new ArrayList<>();

    /**
     * Obtiene todos los clientes de la base de datos y los almacena en la lista local.
     *
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean getClientes(){
        if (DBManager.connect()){
            try(ResultSet rs = DBManager.getTableDataBase("SELECT * FROM CLIENTES")){
                clientes=new ArrayList<>();

                while (rs.next()){
                    int id = rs.getInt(1);
                    String dni = rs.getString(2);
                    String nombre = rs.getString(3);
                    String apellidos = rs.getString(4);
                    String correo = rs.getString(5);
                    String telefono = rs.getString(6);
                    String codigoPostal = rs.getString(7);
                    String direccion = rs.getString(8);
                    String tipoCli = rs.getString(9);
                    if (tipoCli.equalsIgnoreCase("MAYORISTA")){
                        clientes.add(new Cliente(id,dni,nombre,apellidos,correo,telefono,codigoPostal,direccion, EnumTipoCliente.MAYORISTA));
                    } else {
                        clientes.add(new Cliente(id,dni,nombre,apellidos,correo,telefono,codigoPostal,direccion, EnumTipoCliente.MINORISTA));
                    }
                }
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }else {
            return false;
        }
    }
    /**
     * Devuelve la lista de clientes.
     *
     * @return una lista de clientes.
     */
    public static List<Cliente> getClientesList(){
        return clientes;
    }

    /**
     * Añade un nuevo cliente a la base de datos y a la lista local.
     *
     * @param cliente el cliente a añadir.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean anyadirCliente(Cliente cliente){
        if (DBManager.connect() & DBManager.insertarClientes(cliente)){
            try {
                clientes.add(new Cliente(cliente.getId(), cliente.getDni(), cliente.getNombre(), cliente.getApellidos(),
                        cliente.getCorreo(), cliente.getTelefono(), cliente.getCodigoPostal(), cliente.getDireccion(), cliente.getTipoCli()));
            }catch (Exception ex){
                ex.printStackTrace();
                return false;
            }finally {
                DBManager.close();
            }
            return true;
        }else {
            return false;
        }
    }

    /**
     * Actualiza un cliente existente en la base de datos y en la lista local.
     *
     * @param cliente el cliente a actualizar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean actualizarCliente(Cliente cliente){
        try {
            if (DBManager.connect()) {
                if (DBManager.actualizarClientes(cliente)) {
                    borrarColumnaPorId(cliente.getId());
                    clientes.add(cliente);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBManager.close();
        }
        return false;
    }

    /**
     * Elimina un cliente existente de la base de datos y de la lista local.
     *
     * @param cliente el cliente a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean eliminarCliente(Cliente cliente){
        try {
            if (DBManager.connect()) {
                if (DBManager.eliminarCliente(cliente.getId())) {
                    borrarColumnaPorId(cliente.getId());
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBManager.close();
        }
        return false;
    }

    /**
     * Elimina un cliente de la lista local basada en su ID.
     *
     * @param id el ID del cliente a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean borrarColumnaPorId(int id){
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId()==id){
                clientes.remove(i);
                return true;
            }
        }
        return false;
    }
}
