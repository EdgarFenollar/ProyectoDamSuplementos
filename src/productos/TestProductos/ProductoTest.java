package productos.TestProductos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import productos.Producto;

import java.time.LocalDate;

/*
*  Verifica  que los métodos getter y los constructores se inicialicen correctamente.
* */

class ProductoTest {

    @Test
    public void testConstructorWithId() {
        LocalDate fechaEntrega = LocalDate.of(2023, 6, 1);
        LocalDate fechaCaducidad = LocalDate.of(2024, 6, 1);
        Producto producto = new Producto(1, "Producto A", 100, 1.5, 50.0, 30.0, fechaEntrega, fechaCaducidad, "Descripción A", 2, 3);

        assertEquals(1, producto.getId());
        assertEquals("Producto A", producto.getNombre());
        assertEquals(100, producto.getStock());
        assertEquals(1.5, producto.getPeso());
        assertEquals(50.0, producto.getPrecioVenta());
        assertEquals(30.0, producto.getPrecioCompra());
        assertEquals(fechaEntrega, producto.getFechaEntrega());
        assertEquals(fechaCaducidad, producto.getFechaCaducidad());
        assertEquals("Descripción A", producto.getDescripcion());
        assertEquals(2, producto.getIdCategoria());
        assertEquals(3, producto.getIdProveedor());
    }

    @Test
    public void testConstructorWithoutId() {
        LocalDate fechaEntrega = LocalDate.of(2023, 6, 1);
        LocalDate fechaCaducidad = LocalDate.of(2024, 6, 1);
        Producto producto = new Producto("Producto B", 50, 2.0, 100.0, 70.0, fechaEntrega, fechaCaducidad, "Descripción B", 4, 5);

        assertEquals("Producto B", producto.getNombre());
        assertEquals(50, producto.getStock());
        assertEquals(2.0, producto.getPeso());
        assertEquals(100.0, producto.getPrecioVenta());
        assertEquals(70.0, producto.getPrecioCompra());
        assertEquals(fechaEntrega, producto.getFechaEntrega());
        assertEquals(fechaCaducidad, producto.getFechaCaducidad());
        assertEquals("Descripción B", producto.getDescripcion());
        assertEquals(4, producto.getIdCategoria());
        assertEquals(5, producto.getIdProveedor());
    }

}