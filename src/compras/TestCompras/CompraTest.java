package compras.TestCompras;

import static org.junit.jupiter.api.Assertions.*;

import compras.Compra;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/*
*Proposito es Verificar que los métodos setters y getters funcionen correctamente.
 */

class CompraTest {

    @Test
    public void testConstructorWithId() {
        LocalDate fechaCompra = LocalDate.of(2023, 6, 10);
        LocalDate fechaRecepcion = LocalDate.of(2023, 6, 15);
        Compra compra = new Compra(1, fechaCompra, 101, 202, 50, 25.75, fechaRecepcion, 303);

        assertEquals(1, compra.getId());
        assertEquals(fechaCompra, compra.getFecha_compra());
        assertEquals(101, compra.getId_proveedor());
        assertEquals(202, compra.getId_producto());
        assertEquals(50, compra.getCantidad());
        assertEquals(25.75, compra.getPrecio_unitario());
        assertEquals(fechaRecepcion, compra.getFecha_recepcion());
        assertEquals(303, compra.getId_empleado());
    }

    @Test
    public void testConstructorWithoutId() {
        LocalDate fechaCompra = LocalDate.of(2023, 7, 10);
        LocalDate fechaRecepcion = LocalDate.of(2023, 7, 15);
        Compra compra = new Compra(fechaCompra, 102, 203, 60, 30.50, fechaRecepcion, 304);

        assertEquals(fechaCompra, compra.getFecha_compra());
        assertEquals(102, compra.getId_proveedor());
        assertEquals(203, compra.getId_producto());
        assertEquals(60, compra.getCantidad());
        assertEquals(30.50, compra.getPrecio_unitario());
        assertEquals(fechaRecepcion, compra.getFecha_recepcion());
        assertEquals(304, compra.getId_empleado());
    }

    @Test
    public void testSettersAndGetters() {
        LocalDate fechaCompra = LocalDate.of(2023, 8, 10);
        LocalDate fechaRecepcion = LocalDate.of(2023, 8, 15);
        Compra compra = new Compra(fechaCompra, 103, 204, 70, 35.25, fechaRecepcion, 305);

        compra.setId(2);
        compra.setFecha_compra(LocalDate.of(2023, 9, 10));
        compra.setId_proveedor(104);
        compra.setId_producto(205);
        compra.setCantidad(80);
        compra.setPrecio_unitario(40.00);
        compra.setFecha_recepcion(LocalDate.of(2023, 9, 20));
        compra.setId_empleado(306);

        assertEquals(2, compra.getId());
        assertEquals(LocalDate.of(2023, 9, 10), compra.getFecha_compra());
        assertEquals(104, compra.getId_proveedor());
        assertEquals(205, compra.getId_producto());
        assertEquals(80, compra.getCantidad());
        assertEquals(40.00, compra.getPrecio_unitario());
        assertEquals(LocalDate.of(2023, 9, 20), compra.getFecha_recepcion());
        assertEquals(306, compra.getId_empleado());
    }

}