package managers;

import DBManager.DBManager;
import productos.Producto;
import ventas.Venta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * La clase EmpleadoManager gestiona las operaciones relacionadas con los empleados,
 * incluyendo la obtención, adición, actualización y eliminación de empleados en la base de datos.
 */
public class ProductoManager {
    public static List<Producto> productos = new ArrayList<>();

    /**
     * Obtiene todos los empleados de la base de datos y los almacena en la lista local.
     *
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean getProductos(){
        if (DBManager.connect()){
            try(ResultSet rs = DBManager.getTableDataBase("SELECT * FROM PRODUCTOS")){
                productos = new ArrayList<>();

                while (rs.next()){
                    int id = rs.getInt(1);
                    String nombre = rs.getString(2);
                    int stock = rs.getInt(3);
                    double peso = rs.getDouble(4);
                    double precioVenta = rs.getDouble(5);
                    double precioCompra = rs.getDouble(6);
                    LocalDate fechaEntrega = rs.getDate(7).toLocalDate();
                    LocalDate fechaCaducidad = rs.getDate(8).toLocalDate();
                    String descripcion = rs.getString(9);
                    int idCategoria = rs.getInt(10);
                    int idProveedor = rs.getInt(11);


                    productos.add(new Producto(id, nombre, stock, peso, precioVenta, precioCompra, fechaEntrega, fechaCaducidad, descripcion, idCategoria, idProveedor));
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

    public static List<Producto> getProductosList(){
        return productos;
    }

    /**
     * Añade un nuevo empleado a la base de datos y a la lista local.
     *
     * @param empleado el empleado a añadir.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean anyadirProducto(Producto producto){
        if (DBManager.connect() & DBManager.insertarProducto(producto)){
            try {
                productos.add(new Producto(producto.getId(), producto.getNombre(), producto.getStock(), producto.getPeso(), producto.getPrecioVenta(), producto.getPrecioCompra(),
                        producto.getFechaEntrega(), producto.getFechaCaducidad(), producto.getDescripcion(), producto.getIdCategoria(), producto.getIdProveedor()));
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
     * Actualiza un empleado existente en la base de datos y en la lista local.
     *
     * @param empleado el empleado a actualizar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean actualizarProducto(Producto producto){
        try {
            if (DBManager.connect()) {
                if (DBManager.actualizarProducto(producto)) {
                    borrarColumnaPorId(producto.getId());
                    productos.add(producto);
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
     * Elimina un empleado de la lista local basada en su ID.
     *
     * @param id el ID del empleado a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean borrarColumnaPorId(int id){
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId()==id){
                productos.remove(i);
                return true;
            }
        }
        return false;
    }
}
