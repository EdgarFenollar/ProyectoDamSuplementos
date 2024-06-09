package ventas;

import java.time.LocalDate;
import java.util.Date;

public class Venta {
    private int id;
    private LocalDate fechaVenta;
    private int cantidad;
    private double precioUnitario;
    private int idCliente;
    private int idEmpleado;
    private int idProducto;
    private int idPromocion;

    public Venta(int id, String fechaVenta, int cantidad, double precioUnitario, int idCliente, int idEmpleado, int idProducto, int idPromocion) {
        this.id = id;
        this.fechaVenta = LocalDate.parse(fechaVenta);
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.idProducto = idProducto;
        this.idPromocion = idPromocion;
    }

    public Venta(String fechaVenta, int cantidad, double precioUnitario, int idCliente, int idEmpleado, int idProducto, int idPromocion) {
        this.fechaVenta = LocalDate.parse(fechaVenta);
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.idCliente = idCliente;
        this.idEmpleado = idEmpleado;
        this.idProducto = idProducto;
        this.idPromocion = idPromocion;
    }

    public int getId() {
        return id;
    }

    public LocalDate getFechaVenta() {
        return fechaVenta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
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
