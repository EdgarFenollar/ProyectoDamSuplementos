package DBManager;

import categorias.Categoria;
import clientes.Cliente;
import compras.Compra;
import empleados.Empleado;
import productos.Producto;
import promociones.Promocion;
import proveedores.Proveedor;
import ventas.Venta;

import java.sql.*;

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
            rs.updateDate("FechaEntrega", (Date) producto.getFechaEntrega());
            rs.updateDate("FechaCaducidad", (Date) producto.getFechaCaducidad());
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

    public static int contarColumnas(String sql) {
        try {
            Statement stm = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stm.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();

            return rsmd.getColumnCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
                rs.updateDate("FechaRecepcion", (Date) compra.getFecha_recepcion());
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
                rs.updateDate("FechaEntrega", (Date) producto.getFechaEntrega());
                rs.updateDate("FechaCaducidad", (Date) producto.getFechaCaducidad());
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

    // EJEMPLO EDITAR//
    /*
    public static void editarDatosBDPacientes(int id, String nombre, String direccion, String ciudad, int telefono, int diabetico, java.util.Date fechaNac, int turno){
        try{
            Statement stmt = DBManager.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM PACIENTES WHERE CODIGO=" + id);
            if (rs.next()) {
            rs.updateString(2,nombre);
            rs.updateString(3,direccion);
            rs.updateString(4,ciudad);
            rs.updateInt(5, telefono);
            rs.updateInt(6,diabetico);
            rs.updateDate(7, new java.sql.Date(fechaNac.getTime()));
            rs.updateInt(8,turno);
            rs.updateRow();
            rs.close();
            stmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al insertar los datos, revise los datos introducidos y intentelo de nuevo.", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
     */

    // EJEMPLO ELIMINAR //
    /*
    public static void eliminarPaciente(int paciente) {
        Connection con;
        try {
            con = DriverManager.getConnection(DBManager.DB_URL, DBManager.DB_USER, DBManager.DB_PASS);
            Statement statement = con.createStatement();
            int rowCount = statement.executeUpdate(DBManager.DROP_BD_PACIENTES + paciente);
            statement.close();
            con.close();
            if (rowCount > 0) {
                JOptionPane.showMessageDialog(null, "Paciente eliminado correctamente", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "El paciente no existe o no se pudo eliminar", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el paciente", "ERROR", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
     */
}
