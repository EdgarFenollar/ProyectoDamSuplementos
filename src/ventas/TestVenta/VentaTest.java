package ventas.TestVenta;
import org.junit.jupiter.api.Test;
import ventas.Venta;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VentaTest {
    @Test
    public void testConstructorWithId() {
        Venta venta = new Venta(1, "2023-06-09", 5, 100.0, 1, 2, 3, 4);

        assertEquals(1, venta.getId());
        assertEquals(LocalDate.of(2023, 6, 9), venta.getFechaVenta());
        assertEquals(5, venta.getCantidad());
        assertEquals(100.0, venta.getPrecioUnitario());
        assertEquals(1, venta.getIdCliente());
        assertEquals(2, venta.getIdEmpleado());
        assertEquals(3, venta.getIdProducto());
        assertEquals(4, venta.getIdPromocion());
    }

    @Test
    public void testConstructorWithoutId() {
        Venta venta = new Venta("2023-06-09", 10, 150.0, 5, 6, 7, 8);

        assertEquals(LocalDate.of(2023, 6, 9), venta.getFechaVenta());
        assertEquals(10, venta.getCantidad());
        assertEquals(150.0, venta.getPrecioUnitario());
        assertEquals(5, venta.getIdCliente());
        assertEquals(6, venta.getIdEmpleado());
        assertEquals(7, venta.getIdProducto());
        assertEquals(8, venta.getIdPromocion());
    }
}