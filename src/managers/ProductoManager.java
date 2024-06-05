package managers;

import DBManager.DBManager;
import productos.Producto;
import ventas.Venta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductoManager {
    public static List<Producto> productos = new ArrayList<>();

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
                    Date fechaEntrega = rs.getDate(7);
                    Date fechaCaducidad = rs.getDate(8);
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

    public static boolean eliminarProducto(Producto producto){
        try {
            if (DBManager.connect()) {
                if (DBManager.eliminarProducto(producto.getId())) {
                    borrarColumnaPorId(producto.getId());
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
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId()==id){
                productos.remove(i);
                return true;
            }
        }
        return false;
    }
}
