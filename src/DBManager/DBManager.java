package DBManager;

import clientes.Cliente;
import compras.Compra;
import empleados.Empleado;
import managers.PromocionManager;
import promociones.Promocion;
import ventas.Venta;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    // Conexión a la base de datos
    private static Connection conn = null;

    // Configuración de la conexión a la base de datos
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_NAME = "tienda_suplementos";
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "";
    private static final String DB_MSQ_CONN_OK = "CONEXIÓN CORRECTA";
    private static final String DB_MSQ_CONN_NO = "ERROR EN LA CONEXIÓN";

    public static boolean loadDriver() {
        try {
            System.out.print("Cargando Driver...");
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("OK!");
            return true;
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean connect() {
        try {
            System.out.print("Conectando a la base de datos...");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("OK!");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static void close() {
        if (conn != null) {
            try {
                System.out.print("Cerrando la conexión...");
                conn.close();
                System.out.println("OK!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static ResultSet getTableDataBase(int ResulsetType,int ResulsetConcurrency,String sql){
        try {
            Statement stm = conn.createStatement(ResulsetType,ResulsetConcurrency);
            ResultSet rs = stm.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al obtener la tabla " + sql);
            return null;
        }
    }

    public static ResultSet getTableDataBase(String sql){
        return getTableDataBase(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE,sql);
    }

    public static boolean insertarClientes(Cliente cliente){
        try(ResultSet rs = getTableDataBase("SELECT * FROM CLIENTES")) {
            System.out.println("Introduciendo cliente.");

            rs.moveToInsertRow();
            rs.updateString("DNI", cliente.getDni());
            rs.updateString("NOMBRE", cliente.getNombre());
            rs.updateString("APELLIDOS", cliente.getApellidos());
            rs.updateString("CORREO", cliente.getCorreo());
            rs.updateString("TELEFONO",cliente.getTelefono());
            rs.updateString("CodigoPostal",cliente.getCodigoPostal());
            rs.updateString("DIRECCION", cliente.getDireccion());
            rs.updateString("TIPO", String.valueOf(cliente.getTipoCli()));
            rs.insertRow();
            System.out.println("Introduciendo valores de cliente a la BD.");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error introducir cliente en la BD.");
            return false;
        }
    }

    public static boolean insertarEmpleados(Empleado empleado){
        try(ResultSet rs = getTableDataBase("SELECT * FROM EMPLEADOS")) {
            System.out.println("Introduciendo empleado.");
            rs.moveToInsertRow();
            rs.updateString("DNI", empleado.getDni());
            rs.updateString("NOMBRE", empleado.getNombre());
            rs.updateString("CORREO", empleado.getCorreo());
            rs.updateString("TELEFONO", empleado.getTelefono());
            rs.updateString("DIRECCION", empleado.getDireccion());
            rs.updateDate("FechaNacimiento", Date.valueOf(empleado.getFechaNacimiento()));
            rs.insertRow();
            System.out.println("Introduciendo valores de empleado a la BD");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error introduciendo empleado a la BD.");
            return false;
        }
    }

    public static boolean insertarPromociones(Promocion promocion){
        try(ResultSet rs = getTableDataBase("SELECT * FROM PROMOCIONES")) {
            System.out.println("Introduciendo promocion.");
            rs.moveToInsertRow();
            rs.updateString("DESCRIPCION", promocion.getDescripcion());
            rs.updateDouble("DESCUENTO",promocion.getDescuento());
            rs.updateDate("FechaInicio", Date.valueOf(promocion.getFechaInicio()));
            rs.updateDate("FechaFin", Date.valueOf(promocion.getFechaFin()));
            rs.insertRow();
            System.out.println("Introduciendo valores de promocion a la BD");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error introduciendo promocion a la BD.");
            return false;
        }
    }

    public static boolean insertarVentas(Venta venta){
        try(ResultSet rs = getTableDataBase("SELECT * FROM VENTAS")) {
            System.out.println("Introduciendo venta.");
            rs.moveToInsertRow();
            rs.updateDate("FechaVenta", Date.valueOf(venta.getFechaVenta()));
            rs.updateInt("CANTIDAD",venta.getCantidad());
            rs.updateDouble("PrecioUnitario",venta.getPrecioUnitario());
            rs.updateInt("IdCliente",venta.getIdCliente());
            rs.updateInt("IdEmpleado", venta.getIdEmpleado());
            rs.updateInt("IdProducto", venta.getIdProducto());
            rs.updateInt("IdPromocion", venta.getIdPromocion());//Null, porque puede que ese producto seleccionado en la venta no tengo promocion.
            rs.insertRow();
            System.out.println("Introduciendo valores de venta a la BD");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error introduciendo venta a la BD.");
            return false;
        }
    }

    public static boolean insertarCompras(Compra compra){
        try(ResultSet rs = getTableDataBase("SELECT * FROM COMPRAS")) {
            System.out.println("Introduciendo venta.");
            rs.moveToInsertRow();
            rs.updateDate("FechaCompra", Date.valueOf(compra.getFecha_compra()));
            rs.updateInt("IdProveedor",compra.getId_proveedor());
            rs.updateInt("IdProducto", compra.getId_producto());
            rs.updateInt("CANTIDAD", compra.getCantidad());
            rs.updateDouble("PrecioUnitario", compra.getPrecio_unitario());
            rs.updateDate("FechaRecepcion",Date.valueOf(compra.getFecha_recepcion().toString()));
            rs.insertRow();
            System.out.println("Introduciendo valores de compra a la BD");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error introduciendo compra a la BD.");
            return false;
        }
    }

    public static void cargarDatosPromociones(){
        try{
            Connection con = DriverManager.getConnection(DBManager.DB_URL, DB_USER, DB_PASS);
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from promociones");
            while(resultSet.next()) {
                PromocionManager.promociones.add(new Promocion(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getString(6)));
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
