package compras;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class Compra {
    private int id;
    private LocalDate fecha_compra;
    private int id_proveedor;
    private int id_producto;
    private int cantidad;
    private double precio_unitario;
    private LocalDate fecha_recepcion;
    private int id_empleado;

    public Compra(int id, String fecha_compra, int id_proveedor, int id_producto, int cantidad, double precio_unitario, String fecha_recepcion, int id_empleado) {
        this.id = id;
        this.fecha_compra = LocalDate.parse(fecha_compra);
        this.id_proveedor = id_proveedor;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.fecha_recepcion = LocalDate.parse(fecha_recepcion);
        this.id_empleado = id_empleado;
    }

    public Compra(LocalDate fecha_compra, int id_proveedor, int id_producto, int cantidad, double precio_unitario, LocalDate fecha_recepcion, int id_empleado) {
        this.fecha_compra = fecha_compra;
        this.id_proveedor = id_proveedor;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.fecha_recepcion = fecha_recepcion;
        this.id_empleado = id_empleado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(LocalDate fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public LocalDate getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(LocalDate fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }
}
