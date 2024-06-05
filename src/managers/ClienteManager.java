package managers;

import DBManager.DBManager;
import categorias.Categoria;
import clientes.Cliente;
import clientes.EnumTipoCliente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteManager {
    public static List<Cliente> clientes = new ArrayList<>();

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
                    clientes.add(new Cliente(id,dni,nombre,apellidos,correo,telefono,codigoPostal,direccion, tipoCli));
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

    public static List<Cliente> getClientesList(){
        return clientes;
    }

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
