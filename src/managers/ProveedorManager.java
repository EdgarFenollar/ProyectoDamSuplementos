package managers;

import DBManager.DBManager;
import clientes.Cliente;
import proveedores.Proveedor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorManager {
    public static List<Proveedor> proveedores = new ArrayList<>();

    public static boolean getProveedores(){
        if (DBManager.connect()){
            try(ResultSet rs = DBManager.getTableDataBase("SELECT * FROM PROVEEDORES")){
                proveedores=new ArrayList<>();

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
                    proveedores.add(new Cliente(id,dni,nombre,apellidos,correo,telefono,codigoPostal,direccion, tipoCli));
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

    public static List<Proveedor> getProveedoresList(){
        return proveedores;
    }

    public static boolean anyadirProveedor(Proveedor proveedor){
        if (DBManager.connect() & DBManager.insertarProveedores(proveedor)){
            try {
                proveedores.add(new Proveedor(
                        proveedor.getId(),
                        proveedor.getNombre(),
                        proveedor.getCorreo(),
                        proveedor.getCp(),
                        proveedor.getDireccion()));
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

    public static boolean actualizarProveedor(Proveedor proveedor){
        try {
            if (DBManager.connect()) {
                if (DBManager.actualizarProveedor(proveedor)) {
                    borrarColumnaPorId(proveedor.getId());
                    proveedores.add(proveedor);
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

    public static boolean eliminarProveedor(Proveedor proveedor){
        try {
            if (DBManager.connect()) {
                if (DBManager.eliminarProveedor(proveedor.getId())) {
                    borrarColumnaPorId(proveedor.getId());
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
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getId()==id){
                proveedores.remove(i);
                return true;
            }
        }
        return false;
    }
}
