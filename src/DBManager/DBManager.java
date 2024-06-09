package DBManager;

import categorias.Categoria;
import clientes.Cliente;
import clientes.EnumTipoCliente;
import compras.Compra;
import empleados.Empleado;
import managers.*;
import productos.Producto;
import promociones.Promocion;
import proveedores.Proveedor;
import ventas.Venta;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

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
            rs.updateInt("IdEmpleado",compra.getId_empleado());
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
            rs.updateString("DESCRIPCION", categoria.getDescripcion());

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
            System.out.println("Error introduciendo empleado a la BD.");
            return false;
        }
    }

    public static boolean insertarProducto(Producto producto){
        try(ResultSet rs = getTableDataBase("SELECT * FROM PRODUCTOS")) {
            System.out.println("Introduciendo producto.");
            rs.moveToInsertRow();
            rs.updateString("Nombre", producto.getNombre());
            rs.updateInt("Stock", producto.getStock());
            rs.updateDouble("Peso", producto.getPeso());
            rs.updateDouble("PrecioVenta", producto.getPrecioVenta());
            rs.updateDouble("PrecioCompra", producto.getPrecioCompra());
            rs.updateDate("FechaEntrega", Date.valueOf(producto.getFechaEntrega()));
            rs.updateDate("FechaCaducidad", Date.valueOf(producto.getFechaCaducidad()));
            rs.updateString("Descripcion", producto.getDescripcion());
            rs.updateInt("idCategoria", producto.getIdCategoria());
            rs.updateInt("idProveedor", producto.getIdProveedor());
            rs.insertRow();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error introduciendo producto a la BD.");
            return false;
        }
    }

    public static boolean insertarProveedores(Proveedor proveedor){
        try(ResultSet rs = getTableDataBase("SELECT * FROM PROVEEDORES")) {
            System.out.println("Introduciendo proveedor.");
            rs.moveToInsertRow();
            rs.updateString("Nombre", proveedor.getNombre());
            rs.updateString("Correo", proveedor.getCorreo());
            rs.updateString("CodigoPostal", proveedor.getCp());
            rs.updateString("Direccion", proveedor.getDireccion());
            rs.insertRow();
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

    public static int contarFilas(String query) {
        int count = 0;
        try (Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static ResultSet getCompras (int codigo){
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM COMPRAS WHERE IdCompras = " + codigo;
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getVentas (int codigo){
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM VENTAS WHERE IdVenta = " + codigo;
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getPromociones (int codigo){
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM PROMOCIONES WHERE IdPromocion = " + codigo;
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getClientes (int codigo){
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM CLIENTES WHERE IdCliente = " + codigo;
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getProductos (int codigo){
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM PRODUCTOS WHERE IdProducto = " + codigo;
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getCategorias (int codigo){
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM CATEGORIAS WHERE IdCategorias = " + codigo;
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getProveedor (int codigo){
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String sql = "SELECT * FROM PROVEEDORES WHERE IdProveedor = " + codigo;
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean actualizarPromociones(Promocion promocion){
        try(ResultSet rs = getPromociones(promocion.getId())) {
            if (rs==null){
                return false;
            }

            if (rs.first()){//Primer valor encontrado por el id
                rs.updateString("DESCRIPCION", promocion.getDescripcion());
                rs.updateDouble("DESCUENTO",promocion.getDescuento());
                rs.updateDate("FechaInicio", Date.valueOf(promocion.getFechaInicio()));
                rs.updateDate("FechaFin", Date.valueOf(promocion.getFechaFin()));
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

    public static boolean actualizarClientes(Cliente cliente){
        try(ResultSet rs = getClientes(cliente.getId())) {
            if (rs==null){
                return false;
            }

            if (rs.first()){//Primer valor encontrado por el id
                rs.updateString("DNI", cliente.getDni());
                rs.updateString("NOMBRE", cliente.getNombre());
                rs.updateString("APELLIDOS", cliente.getApellidos());
                rs.updateString("CORREO", cliente.getCorreo());
                rs.updateString("TELEFONO",cliente.getTelefono());
                rs.updateString("CodigoPostal",cliente.getCodigoPostal());
                rs.updateString("DIRECCION", cliente.getDireccion());
                rs.updateString("TIPO", String.valueOf(cliente.getTipoCli()));
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

    public static boolean actualizarCategorias(Categoria categoria){
        try(ResultSet rs = getCategorias(categoria.getId())) {
            if (rs==null){
                return false;
            }

            if (rs.first()){//Primer valor encontrado por el id
                rs.updateString("NOMBRE", categoria.getNombre());
                rs.updateString("DESCRIPCION", categoria.getDescripcion());
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

    public static boolean actualizarCompras(Compra compra){
        try(ResultSet rs = getCompras(compra.getId())) {
            if (rs==null){
                return false;
            }

            if (rs.first()){//Primer valor encontrado por el id
                rs.updateDate("FechaCompra", Date.valueOf(compra.getFecha_compra()));
                rs.updateInt("idProveedor", compra.getId_proveedor());
                rs.updateInt("idProducto", compra.getId_producto());
                rs.updateInt("Cantidad", compra.getCantidad());
                rs.updateDouble("PrecioUnitario", compra.getPrecio_unitario());
                rs.updateDate("FechaRecepcion", Date.valueOf(compra.getFecha_recepcion()));
                rs.updateInt("idEmpleado", compra.getId_empleado());
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

    public static boolean actualizarProveedor(Proveedor proveedor){
        try(ResultSet rs = getProveedor(proveedor.getId())) {
            if (rs==null){
                return false;
            }

            if (rs.first()){//Primer valor encontrado por el id
                rs.updateString("NOMBRE", proveedor.getNombre());
                rs.updateString("CORREO", proveedor.getCorreo());
                rs.updateString("CodioPostal", proveedor.getCp());
                rs.updateString("DIRECCIOn", proveedor.getDireccion());
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

    public static boolean actualizarProducto(Producto producto){
        try(ResultSet rs = getProductos(producto.getId())) {
            if (rs==null){
                return false;
            }

            if (rs.first()){//Primer valor encontrado por el id
                rs.updateString("Nombre", producto.getNombre());
                rs.updateInt("Stock", producto.getStock());
                rs.updateDouble("Peso", producto.getPeso());
                rs.updateDouble("PrecioVenta", producto.getPrecioVenta());
                rs.updateDouble("PrecioCompra", producto.getPrecioCompra());
                rs.updateDate("FechaEntrega", Date.valueOf(producto.getFechaEntrega()));
                rs.updateDate("FechaCaducidad", Date.valueOf(producto.getFechaCaducidad()));
                rs.updateString("Descripcion", producto.getDescripcion());
                rs.updateInt("idCategoria", producto.getIdCategoria());
                rs.updateInt("idProveedor", producto.getIdProveedor());
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

    public static boolean actualizarVentas(Venta venta){
        try(ResultSet rs = getVentas(venta.getId())) {
            if (rs==null){
                return false;
            }

            if (rs.first()){//Primer valor encontrado por el id
                rs.updateDate("FechaVenta", Date.valueOf(venta.getFechaVenta()));
                rs.updateInt("Cantidad", venta.getCantidad());
                rs.updateDouble("PrecioUnitario", venta.getPrecioUnitario());
                rs.updateInt("idCliente", venta.getIdCliente());
                rs.updateInt("idEmpleado", venta.getIdEmpleado());
                rs.updateInt("idProducto", venta.getIdProducto());
                rs.updateInt("idPromocion", venta.getIdPromocion());
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

    public static boolean eliminarPromocion (int id){
        try(ResultSet rs = getPromociones(id)) {

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

    public static boolean eliminarCompra(int id){
        try(ResultSet rs = getCompras(id)) {

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

    public static boolean eliminarCliente (int id){
        try(ResultSet rs = getClientes(id)) {

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

    public static boolean eliminarCategoria (int id){
        try(ResultSet rs = getCategorias(id)) {

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

    public static boolean eliminarProveedor (int id){
        try(ResultSet rs = getProveedor(id)) {

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
    
    public static boolean eliminarProducto(int id){
        try(ResultSet rs = getProductos(id)) {

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

    public static boolean eliminarVenta(int id){
        try(ResultSet rs = getVentas(id)) {

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

    public static void editarEmpleado(int id, String dni, String nombre, String apellidos, String correo, String telefono, String direccion, String usuario, String contrasenya, LocalDate fechaNac, int admin){
        try{
            Statement stmt = DBManager.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLEADOS WHERE IDEMPLEADO=" + id);
            if (rs.next()) {
                rs.updateString(2,dni);
                rs.updateString(3,nombre);
                rs.updateString(4,apellidos);
                rs.updateString(5, correo);
                rs.updateString(6,telefono);
                rs.updateString(7,direccion);
                rs.updateDate(8, Date.valueOf(fechaNac.toString()));
                rs.updateString(9,usuario);
                rs.updateString(10,contrasenya);
                rs.updateInt(11, admin);
                rs.updateRow();
                rs.close();
                stmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar los datos, revise los datos introducidos y intentelo de nuevo.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void editarProducto(int id, String nombre, int stock, Double peso, Double precioVenta, Double precioCompra, LocalDate fechaEntrega, LocalDate fechaCaducidad, String descripcion, int idCategoria, int idProveedor){
        try{
            Statement stmt = DBManager.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTOS WHERE IDPRODUCTO=" + id);
            if (rs.next()) {
                rs.updateString(2,nombre);
                rs.updateInt(3,stock);
                rs.updateDouble(4,peso);
                rs.updateDouble(5, precioVenta);
                rs.updateDouble(6,precioCompra);
                rs.updateDate(7, Date.valueOf(fechaEntrega.toString()));
                rs.updateDate(8, Date.valueOf(fechaCaducidad.toString()));
                rs.updateString(9,descripcion);
                rs.updateInt(10,idCategoria);
                rs.updateInt(11, idProveedor);
                rs.updateRow();
                rs.close();
                stmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar los datos, revise los datos introducidos y intentelo de nuevo.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void editarPromociones(int id, String descripcion, double descuento, LocalDate fechaIn, LocalDate fechaFin){
        try{
            Statement stmt = DBManager.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM PROMOCIONES WHERE IDPROMOCION=" + id);
            if (rs.next()) {
                rs.updateString(2,descripcion);
                rs.updateDouble(3,descuento);
                rs.updateDate(4, Date.valueOf(fechaIn.toString()));
                rs.updateDate(5, Date.valueOf(fechaFin.toString()));
                rs.updateRow();
                rs.close();
                stmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar los datos, revise los datos introducidos y intentelo de nuevo.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void editarProveedores(int id, String nombre, String correo, String cp, String direccion){
        try{
            Statement stmt = DBManager.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM PROVEEDORES WHERE IDPROVEEDOR=" + id);
            if (rs.next()) {
                rs.updateString(2,nombre);
                rs.updateString(3,correo);
                rs.updateString(4,cp);
                rs.updateString(5,direccion);
                rs.updateRow();
                rs.close();
                stmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar los datos, revise los datos introducidos y intentelo de nuevo.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void editarCompras(int id, LocalDate fechaCompra, int idProveedor, int idProducto, int cantidad, double precioUnitario, LocalDate fechaRecepcion, int idEmpleado){
        try{
            Statement stmt = DBManager.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM COMPRAS WHERE IDCOMPRAS = " + id);
            if (rs.next()) {
                rs.updateDate(2, Date.valueOf(fechaCompra.toString()));
                rs.updateInt(3,idProveedor);
                rs.updateInt(4,idProducto);
                rs.updateInt(5,cantidad);
                rs.updateDouble(6,precioUnitario);
                rs.updateDate(7, Date.valueOf(fechaRecepcion.toString()));
                rs.updateInt(8,idEmpleado);
                rs.updateRow();
                rs.close();
                stmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar los datos, revise los datos introducidos y intentelo de nuevo.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void editarVentas(int id, LocalDate fechaVenta, int cantidad, Double precioUnitario, int idCliente, int idEmpleado, int idProducto, int idPromocion){
        try{
            Statement stmt = DBManager.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM VENTAS WHERE IDVENTA = " + id);
            if (rs.next()) {
                rs.updateDate(2, Date.valueOf(fechaVenta));
                rs.updateInt(3,cantidad);
                rs.updateDouble(4,precioUnitario);
                rs.updateInt(5,idCliente);
                rs.updateInt(6,idEmpleado);
                rs.updateInt(7,idProducto);
                rs.updateInt(8,idPromocion);
                rs.updateRow();
                rs.close();
                stmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar los datos, revise los datos introducidos y intentelo de nuevo.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void editarClientes(int id, String dni, String nombre, String apellidos, String correo, String telefono, String codPostal, String direccion, String tipo){
        try{
            Statement stmt = DBManager.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM CLIENTES WHERE IDCLIENTE = " + id);
            if (rs.next()) {
                rs.updateString(2,dni);
                rs.updateString(2,nombre);
                rs.updateString(2,apellidos);
                rs.updateString(2,correo);
                rs.updateString(2,telefono);
                rs.updateString(2,codPostal);
                rs.updateString(2,direccion);
                rs.updateString(2,tipo);
                rs.updateRow();
                rs.close();
                stmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar los datos, revise los datos introducidos y intentelo de nuevo.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void editarCategoria(int id, String nombre, String descripcion){
        try{
            Statement stmt = DBManager.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM CATEGORIAS WHERE IDCATEGORIA = " + id);
            if (rs.next()) {
                rs.updateString(2,nombre);
                rs.updateString(3,descripcion);
                rs.updateRow();
                rs.close();
                stmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar los datos, revise los datos introducidos y intentelo de nuevo.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    ///// FUNCIONES DE BUSQUEDA
    public static boolean getPromocionesPorFiltro(String tipo, String busqueda) {
        String query = "SELECT * FROM PROMOCIONES WHERE " + tipo + " LIKE ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + busqueda + "%");
            try (ResultSet rs = preparedStatement.executeQuery()) {
                PromocionManager.promociones = new ArrayList<>();

                while (rs.next()) {
                    int codigo = rs.getInt(1);
                    String descripcion = rs.getString(2);
                    double descuento = rs.getDouble(3);
                    LocalDate fechaInicial = rs.getDate(4).toLocalDate();
                    LocalDate fechaFinal = rs.getDate(5).toLocalDate();
                    PromocionManager.promociones.add(new Promocion(codigo, descripcion, descuento, fechaFinal, fechaInicial));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getProveedoresPorFiltro(String tipo, String busqueda) {
        String query = "SELECT * FROM PROVEEDORES WHERE " + tipo + " LIKE ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + busqueda + "%");
            try (ResultSet rs = preparedStatement.executeQuery()) {
                ProveedorManager.proveedores = new ArrayList<>();

                while (rs.next()) {
                    int codigo = rs.getInt(1);
                    String nombre = rs.getString(2);
                    String correo = rs.getString(3);
                    String cp = rs.getString(4);
                    String direccion = rs.getString(5);
                    ProveedorManager.proveedores.add(new Proveedor(codigo, nombre, correo, cp, direccion));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getEmpleadosPorFiltro(String tipo, String busqueda) {
        String query = "SELECT * FROM EMPLEADOS WHERE " + tipo + " LIKE ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + busqueda + "%");
            try (ResultSet rs = preparedStatement.executeQuery()) {
                EmpleadoManager.empleados = new ArrayList<>();

                while (rs.next()) {
                    int codigo = rs.getInt(1);
                    String dni =rs.getString(2);
                    String nombre =rs.getString(3);
                    String apellidos =rs.getString(4);
                    String correo =rs.getString(5);
                    String telefono =rs.getString(6);
                    String direccion =rs.getString(7);
                    String fechanac =rs.getString(8);
                    String usuario =rs.getString(9);
                    String contrasenya =rs.getString(10);
                    String administrador =rs.getString(11);

                    EmpleadoManager.empleados.add(new Empleado(codigo,dni, nombre,apellidos, correo, telefono, direccion, LocalDate.parse(fechanac), Integer.parseInt(administrador), usuario, contrasenya));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getProductosPorFiltro(String tipo, String busqueda) {
        String query = "SELECT * FROM PRODUCTOS WHERE " + tipo + " LIKE ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + busqueda + "%");
            try (ResultSet rs = preparedStatement.executeQuery()) {
                ProductoManager.productos = new ArrayList<>();

                while (rs.next()) {
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


                    ProductoManager.productos.add(new Producto(id, nombre, stock, peso, precioVenta, precioCompra, fechaEntrega, fechaCaducidad, descripcion, idCategoria, idProveedor));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getComprasPorFiltro(String tipo, String busqueda) {
        String query = "SELECT * FROM COMPRAS WHERE " + tipo + " LIKE ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + busqueda + "%");
            try (ResultSet rs = preparedStatement.executeQuery()) {
                CompraManager.compras = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    LocalDate fechaCompra = rs.getDate(2).toLocalDate();
                    int idProveedor = rs.getInt(3);
                    int idProducto = rs.getInt(4);
                    int cantidad = rs.getInt(5);
                    double precioUnirario = rs.getDouble(6);
                    LocalDate fechaEntrega = rs.getDate(7).toLocalDate();
                    int idEmpleado = rs.getInt(8);
                    CompraManager.compras.add(new Compra(id, fechaCompra,idProveedor,idProducto,cantidad,precioUnirario, fechaEntrega,idEmpleado));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getVentasPorFiltro(String tipo, String busqueda) {
        String query = "SELECT * FROM VENTAS WHERE " + tipo + " LIKE ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + busqueda + "%");
            try (ResultSet rs = preparedStatement.executeQuery()) {
                VentaManager.ventas = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    Date fechaVenta = rs.getDate(2);
                    int cantidad = rs.getInt(3);
                    double precioUnitario = rs.getDouble(4);
                    int idCliente = rs.getInt(5);
                    int idEmpleado = rs.getInt(6);
                    int idProducto = rs.getInt(7);
                    int idPromocion = rs.getInt(8);
                    VentaManager.ventas.add(new Venta(id , String.valueOf(fechaVenta), cantidad, precioUnitario, idCliente, idEmpleado, idProducto, idPromocion));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getCategoriasPorFiltro(String tipo, String busqueda) {
        String query = "SELECT * FROM CATEGORIAS WHERE " + tipo + " LIKE ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + busqueda + "%");
            try (ResultSet rs = preparedStatement.executeQuery()) {
                CategoriaManager.categorias = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    String nombre = rs.getString(2);
                    String descripcion = rs.getString(3);
                    CategoriaManager.categorias.add(new Categoria(id,nombre,descripcion));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getClientesPorFiltro(String tipo, String busqueda) {
        String query = "SELECT * FROM CLIENTES WHERE " + tipo + " LIKE ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, "%" + busqueda + "%");
            try (ResultSet rs = preparedStatement.executeQuery()) {
                ClienteManager.clientes = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    String dni = rs.getString(2);
                    String nombre = rs.getString(3);
                    String apellido = rs.getString(4);
                    String correo = rs.getString(5);
                    String telefono = rs.getString(6);
                    String codigoPostal = rs.getString(7);
                    String direccion = rs.getString(8);
                    String tipoCliente = rs.getString(9);
                    ClienteManager.clientes.add(new Cliente(id,dni,nombre,apellido,correo,telefono,codigoPostal,direccion, EnumTipoCliente.valueOf(tipoCliente)));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    // FUNCION ELIMINAR //
    //Funciona eliminada debido a que vamos a quitar la funcion de eliminar datos porque no seria necesario dentro de la empresa debido a que se debe de llevar el registro de todo.
    /*
    public static void eliminarEmpleado(int row) {
        Connection con;
        try {
            for (int i = 0; i < EmpleadoManager.empleados.size(); i++) {
                if (i == row){
                    con = DriverManager.getConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASS);
                    Statement statement = con.createStatement();
                    int rowCount = statement.executeUpdate("DELETE FROM EMPLEADOS WHERE IDEMPLEADO=" + i);
                    statement.close();
                    con.close();
                    if (rowCount > 0) {
                        JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "El empleado no existe o no se pudo eliminar", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el empleado", "ERROR", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
     */
}