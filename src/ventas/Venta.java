package ventas;

import java.util.Date;

public class Venta {
    private int id;
    private Date fechaVenta;
    private int cantidad;
    private double precio;
    private int idCliente;
    private int idEmpleado;
    private int idProducto;
    private int idPromocion;

    public Venta(int id, Date fechaVenta, int cantidad, double precio, int idCliente, int idEmpleado, int idProducto, int idPromocion) {
        this.id = id;
        this.fechaVenta = fechaVenta;
        this.cantidad = cantidad;
        this.precio = precio;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.idProducto = idProducto;
        this.idPromocion = idPromocion;
    }

    public int getId() {
        return id;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public int getIdPromocion() {
        return idPromocion;
    }
}
