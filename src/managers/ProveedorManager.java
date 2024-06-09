package managers;

import DBManager.DBManager;
import clientes.Cliente;
import proveedores.Proveedor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase ProveedorManager gestiona las operaciones relacionadas con los proveedores,
 * incluyendo la obtención, adición, actualización y eliminación de proveedores en la base de datos y en una lista local.
 */
public class ProveedorManager {
    public static List<Proveedor> proveedores = new ArrayList<>();

    /**
     * Obtiene todos los proveedores de la base de datos y los almacena en la lista local.
     *
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean getProveedores(){
        if (DBManager.connect()){
            try(ResultSet rs = DBManager.getTableDataBase("SELECT * FROM PROVEEDORES")){
                proveedores=new ArrayList<>();

                while (rs.next()){
                    int id = rs.getInt(1);
                    String nombre = rs.getString(2);
                    String correo = rs.getString(3);
                    String cp = rs.getString(4);
                    String direccion = rs.getString(5);
                    proveedores.add(new Proveedor(id, nombre, correo, cp, direccion));
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
     * Devuelve la lista de proveedores.
     *
     * @return una lista de proveedores.
     */
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

    /**
     * Actualiza un proveedor existente en la base de datos y en la lista local.
     *
     * @param proveedor el proveedor a actualizar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
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


    /**
     * Elimina un proveedor de la base de datos y de la lista local.
     *
     * @param proveedor el proveedor a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
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

    /**
     * Borra un proveedor de la lista local basado en su ID.
     *
     * @param id el ID del proveedor a borrar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
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
