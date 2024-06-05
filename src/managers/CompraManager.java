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

public class CompraManager {
    public static ArrayList<Compra> compras = new ArrayList<>();

    public static boolean getCompras(){
        if (DBManager.connect()){
            try(ResultSet rs = DBManager.getTableDataBase("SELECT * FROM COMPRAS")){
                compras = new ArrayList<>();

                while (rs.next()){
                    int id = rs.getInt(1);
                    Date fechaCompra = rs.getDate(2);
                    int idProveedor = rs.getInt(3);
                    int idProducto = rs.getInt(4);
                    int cantidad = rs.getInt(5);
                    double precioUnitario = rs.getDouble(6);
                    Date fechaRecepcion = rs.getDate(7);
                    int idEmpleado = rs.getInt(8);

                    compras.add(new Compra(id,String.valueOf(fechaCompra), idProveedor, idProducto, cantidad, precioUnitario, String.valueOf(fechaRecepcion), idEmpleado));
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

    public static boolean anyadirCompra(Compra compra){
        if (DBManager.connect() & DBManager.insertarCompras(compra)){
            try {
                compras.add(new Compra(compra.getId(), String.valueOf(compra.getFecha_compra()), compra.getId_proveedor(), compra.getId_producto(),
                        compra.getCantidad(), compra.getPrecio_unitario(), String.valueOf(compra.getFecha_recepcion()), compra.getId_empleado()));
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
