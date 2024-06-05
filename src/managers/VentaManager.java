package managers;

import DBManager.DBManager;
import compras.Compra;
import ventas.Venta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentaManager {
    public static List<Venta> ventas = new ArrayList<>();

    public static boolean getCompras(){
        if (DBManager.connect()){
            try(ResultSet rs = DBManager.getTableDataBase("SELECT * FROM VENTAS")){
                ventas = new ArrayList<>();

                while (rs.next()){
                    int id = rs.getInt(1);
                    Date fechaVenta = rs.getDate(2);
                    int cantidad = rs.getInt(3);
                    double precioUnitario = rs.getDouble(4);
                    int idCliente = rs.getInt(5);
                    int idEmpleado = rs.getInt(6);
                    int idProducto = rs.getInt(7);
                    int idPromocion = rs.getInt(8);

                    ventas.add(new Venta(id, String.valueOf(fechaVenta), cantidad, precioUnitario, idCliente, idEmpleado, idProducto, idPromocion));
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

    public static List<Venta> getVentasList(){
        return ventas;
    }

    public static boolean anyadirVenta(Venta venta){
        if (DBManager.connect() & DBManager.insertarVentas(venta)){
            try {
                ventas.add(new Venta(venta.getId(), String.valueOf(venta.getFechaVenta()), venta.getCantidad(), venta.getPrecioUnitario(),
                        venta.getIdCliente(), venta.getIdEmpleado(), venta.getIdProducto(), venta.getIdPromocion()));
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

    public static boolean actualizarVenta(Venta venta){
        try {
            if (DBManager.connect()) {
                if (DBManager.actualizarVentas(venta)) {
                    borrarColumnaPorId(venta.getId());
                    ventas.add(venta);
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

    public static boolean eliminarVenta(Venta venta){
        try {
            if (DBManager.connect()) {
                if (DBManager.eliminarVenta(venta.getId())) {
                    borrarColumnaPorId(venta.getId());
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
        for (int i = 0; i < ventas.size(); i++) {
            if (ventas.get(i).getId()==id){
                ventas.remove(i);
                return true;
            }
        }
        return false;
    }

}
