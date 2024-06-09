package productos;

import java.time.LocalDate;
import java.util.Date;

/**
 * La clase Producto representa un producto en el sistema.
 */
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

    /**
     * Constructor para crear un objeto Producto con un ID.
     *
     * @param id el ID del producto.
     * @param nombre el nombre del producto.
     * @param stock la cantidad de productos disponibles en inventario.
     * @param peso el peso del producto.
     * @param precioVenta el precio de venta del producto.
     * @param precioCompra el precio de compra del producto.
     * @param fechaEntrega la fecha de entrega del producto.
     * @param fechaCaducidad la fecha de caducidad del producto.
     * @param descripcion una descripción del producto.
     * @param idCategoria el ID de la categoría a la que pertenece el producto.
     * @param idProveedor el ID del proveedor del producto.
     */
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

    /**
     * Constructor para crear un objeto Producto sin un ID.
     *
     * @param nombre el nombre del producto.
     * @param stock la cantidad de productos disponibles en inventario.
     * @param peso el peso del producto.
     * @param precioVenta el precio de venta del producto.
     * @param precioCompra el precio de compra del producto.
     * @param fechaEntrega la fecha de entrega del producto.
     * @param fechaCaducidad la fecha de caducidad del producto.
     * @param descripcion una descripción del producto.
     * @param idCategoria el ID de la categoría a la que pertenece el producto.
     * @param idProveedor el ID del proveedor del producto.
     */
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
