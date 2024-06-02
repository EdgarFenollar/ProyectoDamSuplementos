package productos;

import java.util.Date;

public class Producto {
    private int id;
    private String nombre;
    private int stock;
    private double peso;
    private double precioVenta;
    private double precioCompra;
    private Date fechaEntrega;
    private Date fechaCaducidad;
    private String descripcion;
    private int idCategoria;
    private int idProveedor;

    public Producto(int id, String nombre, int stock, double peso, double precioVenta, double precioCompra, Date fechaEntrega, Date fechaCaducidad, String descripcion, int idCategoria, int idProveedor) {
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

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public Date getFechaCaducidad() {
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
