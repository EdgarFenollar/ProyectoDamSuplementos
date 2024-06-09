package managers;

import DBManager.DBManager;
import clientes.Cliente;
import compras.Compra;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * La clase CompraManager gestiona las operaciones relacionadas con las compras,
 * incluyendo la obtención, adición, actualización y eliminación de compras en la base de datos.
 */
public class CompraManager {
    public static ArrayList<Compra> compras = new ArrayList<>();

    /**
     * Obtiene todas las compras de la base de datos y las almacena en la lista local.
     *
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean getCompras(){
        if (DBManager.connect()){
            try(ResultSet rs = DBManager.getTableDataBase("SELECT * FROM COMPRAS")){
                compras = new ArrayList<>();

                while (rs.next()){
                    int id = rs.getInt(1);
                    LocalDate fechaCompra = rs.getDate(2).toLocalDate();
                    int idProveedor = rs.getInt(3);
                    int idProducto = rs.getInt(4);
                    int cantidad = rs.getInt(5);
                    double precioUnitario = rs.getDouble(6);
                    LocalDate fechaRecepcion = rs.getDate(7).toLocalDate();
                    int idEmpleado = rs.getInt(8);

                    compras.add(new Compra(id, fechaCompra, idProveedor, idProducto, cantidad, precioUnitario, fechaRecepcion, idEmpleado));
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

    public static List<Compra> getComprasList(){
        return compras;
    }

    /**
     * Añade una nueva compra a la base de datos y a la lista local.
     *
     * @param compra la compra a añadir.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean anyadirCompra(Compra compra){
        if (DBManager.connect() & DBManager.insertarCompras(compra)){
            try {
                compras.add(new Compra(
                        compra.getId(),
                        compra.getFecha_compra(),
                        compra.getId_proveedor(),
                        compra.getId_producto(),
                        compra.getCantidad(),
                        compra.getPrecio_unitario(),
                        compra.getFecha_recepcion(),
                        compra.getId_empleado()));
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
     * Actualiza una compra existente en la base de datos y en la lista local.
     *
     * @param compra la compra a actualizar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean actualizarCompra(Compra compra){
        try {
            if (DBManager.connect()) {
                if (DBManager.actualizarCompras(compra)) {
                    borrarColumnaPorId(compra.getId());
                    compras.add(compra);
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
     * Elimina una compra existente de la base de datos y de la lista local.
     *
     * @param compra la compra a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean eliminarCompra(Compra compra){
        try {
            if (DBManager.connect()) {
                if (DBManager.eliminarCompra(compra.getId())) {
                    borrarColumnaPorId(compra.getId());
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
     * Elimina una compra de la lista local basada en su ID.
     *
     * @param id el ID de la compra a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean borrarColumnaPorId(int id){
        for (int i = 0; i < compras.size(); i++) {
            if (compras.get(i).getId()==id){
                compras.remove(i);
                return true;
            }
        }
        return false;
    }
}
