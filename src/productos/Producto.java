package productos;

import java.time.LocalDate;
import java.util.Date;

public class Producto {
    private int id;
    private String nombre;
    private int stock;
    private double peso;
    private double precioVenta;
    private double precioCompra;
    private LocalDate fechaEntrega;
    private LocalDate fechaCaducidad;
    private String descripcion;
    private int idCategoria;
    private int idProveedor;

    public Producto(int id, String nombre, int stock, double peso, double precioVenta, double precioCompra, LocalDate fechaEntrega, LocalDate fechaCaducidad, String descripcion, int idCategoria, int idProveedor) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.peso = peso;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.fechaEntrega = fechaEntrega;
        this.fechaCaducidad = fechaCaducidad;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
    }

    public Producto(String nombre, int stock, double peso, double precioVenta, double precioCompra, LocalDate fechaEntrega, LocalDate fechaCaducidad, String descripcion, int idCategoria, int idProveedor) {
        this.nombre = nombre;
        this.stock = stock;
        this.peso = peso;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.fechaEntrega = fechaEntrega;
        this.fechaCaducidad = fechaCaducidad;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }

    public double getPeso() {
        return peso;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public int getIdProveedor() {
        return idProveedor;
    }
}
