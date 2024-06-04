package DBManager;

import categorias.Categoria;
import clientes.Cliente;
import compras.Compra;
import empleados.Empleado;
import managers.PromocionManager;
import promociones.Promocion;
import proveedores.Proveedor;
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

    // Datos para Empleados
    private static final String TB_Empleados = "empleados";
    private static final String TB_Empleados_SelectTodo = "SELECT * FROM " + TB_Empleados;
    private static final String TB_Empleados_ID = "idEmpleado";
    private static final String TB_Empleados_DNI = "DNI";
    private static final String TB_Empleados_Nombre = "Nombre";
    private static final String TB_Empleados_Apellidos = "Apellidos";
    private static final String TB_Empleados_Correo = "Correo";
    private static final String TB_Empleados_Telefono = "Telefono";
    private static final String TB_Empleados_Direccion = "Direccion";
    private static final String TB_Empleados_FechaNac = "FechaNacimiento";
    private static final String TB_Empleados_Usuario = "Usuario";
    private static final String TB_Empleados_Contrasenya = "Contrasenya";
    private static final String TB_Empleados_Administrador = "Administrador";




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

    //////////////////////// EMPLEADOS //////////////////////////

    public static ResultSet getTablaEmpleados(int rsType, int rsConcurrency) {
        try {
            Statement stmt = conn.createStatement(rsType, rsConcurrency);
            ResultSet rs = stmt.executeQuery(TB_Empleados_SelectTodo);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getTablaEmpleados() {
        return getTablaEmpleados(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }

    public static ResultSet getEmpleado(String nombre) {
        try {
            String sql = TB_Empleados_SelectTodo + " WHERE " + TB_Empleados_Nombre + " = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1, nombre);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.first()) {
                return null;
            }

            return rs;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //////////////////////////////////////////////////////////////////////

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

    public static boolean insertarCategorias(Categoria categoria){
        try(ResultSet rs = getTableDataBase("SELECT * FROM CATEGORIAS")) {
            System.out.println("Introduciendo categoria.");
            rs.moveToInsertRow();
            rs.updateString("NOMBRE",categoria.getNombre());


            rs.insertRow();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error introduciendo categoria a la BD.");
            return false;
        }
    }

    public static boolean insertarEmpleado(Empleado empleado){
        try(ResultSet rs = getTableDataBase("SELECT * FROM EMPLEADOS")) {
            System.out.println("Introduciendo empleado.");
            rs.moveToInsertRow();
            rs.updateString("DNI",empleado.getDni());
            rs.updateString("NOMBRE",empleado.getNombre());
            rs.updateString("APELLIDOS",empleado.getApellidos());
            rs.updateString("CORREO",empleado.getCorreo());
            rs.updateString("TELEFONO",empleado.getTelefono());
            rs.updateString("DIRECCION",empleado.getDireccion());
            rs.updateDate("FECHANACIMIENTO", Date.valueOf(empleado.getFechaNacimiento()));
            rs.updateString("USUARIO",empleado.getUsuario());
            rs.updateString("CONTRASENYA",empleado.getContrasenya());
            rs.updateInt("ADMINISTRADOR",empleado.getAdministrador());
            rs.insertRow();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error introduciendo categoria a la BD.");
            return false;
        }
    }

    public static boolean insertarProveedores(Proveedor proveedor){
        try(ResultSet rs = getTableDataBase("SELECT * FROM PROVEEDOR")) {

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error introduciendo proveedor a la BD.");
            return false;
        }
        return false;
    }

    public static ResultSet getEmpleados (int codigo){
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM EMPLEADOS WHERE IdEmpleado = " + codigo;
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean actualizarEmpleados(Empleado empleado){
        try(ResultSet rs = getEmpleados(empleado.getId())) {
            if (rs==null){
                return false;
            }

            if (rs.first()){//Primer valor encontrado por el id
                rs.updateString("NOMBRE", empleado.getDni());
                rs.updateString("DESCRIPCION", empleado.getNombre());
                rs.updateRow();
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean eliminarEmpleado (int id){
        try(ResultSet rs = getEmpleados(id)) {

            if (rs == null) return false;

            if (rs.first()){
                rs.deleteRow();
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
