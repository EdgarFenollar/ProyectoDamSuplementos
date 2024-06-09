package proveedores.TestProveedores;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import proveedores.Proveedor;

class ProveedorTest {
    @Test
    public void testConstructorWithId() {
        Proveedor proveedor = new Proveedor(1, "Proveedor A", "correo@proveedor.com", "12345", "Calle Falsa 123");

        assertEquals(1, proveedor.getId());
        assertEquals("Proveedor A", proveedor.getNombre());
        assertEquals("correo@proveedor.com", proveedor.getCorreo());
        assertEquals("12345", proveedor.getCp());
        assertEquals("Calle Falsa 123", proveedor.getDireccion());
    }

    @Test
    public void testConstructorWithoutId() {
        Proveedor proveedor = new Proveedor("Proveedor B", "contacto@proveedor.com", "67890", "Avenida Siempre Viva 456");

        assertEquals("Proveedor B", proveedor.getNombre());
        assertEquals("contacto@proveedor.com", proveedor.getCorreo());
        assertEquals("67890", proveedor.getCp());
        assertEquals("Avenida Siempre Viva 456", proveedor.getDireccion());
    }

}