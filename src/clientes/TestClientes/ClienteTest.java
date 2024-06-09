package clientes.TestClientes;

import clientes.Cliente;
import clientes.EnumTipoCliente;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/*
* Las Pruebas están diseñadas para verificar que los constructores, getters, setters y métodos adicionales funcionen correctamente.
*/
class ClienteTest {

    @Test
    public void testConstructorWithId() {
        Cliente cliente = new Cliente(1, "12345678A", "Juan", "Pérez", "juan@example.com", "123456789", "08001", "Calle Falsa 123", EnumTipoCliente.MAYORISTA);

        assertEquals(1, cliente.getId());
        assertEquals("12345678A", cliente.getDni());
        assertEquals("Juan", cliente.getNombre());
        assertEquals("Pérez", cliente.getApellidos());
        assertEquals("juan@example.com", cliente.getCorreo());
        assertEquals("123456789", cliente.getTelefono());
        assertEquals("08001", cliente.getCodigoPostal());
        assertEquals("Calle Falsa 123", cliente.getDireccion());
        assertEquals(EnumTipoCliente.MAYORISTA, cliente.getTipoCli());
    }

    @Test
    public void testConstructorWithoutId() {
        Cliente cliente = new Cliente("12345678A", "Ana", "Gómez", "ana@example.com", "987654321", "08002", "Calle Verdadera 456", EnumTipoCliente.MINORISTA);

        assertEquals("12345678A", cliente.getDni());
        assertEquals("Ana", cliente.getNombre());
        assertEquals("Gómez", cliente.getApellidos());
        assertEquals("ana@example.com", cliente.getCorreo());
        assertEquals("987654321", cliente.getTelefono());
        assertEquals("08002", cliente.getCodigoPostal());
        assertEquals("Calle Verdadera 456", cliente.getDireccion());
        assertEquals(EnumTipoCliente.MINORISTA, cliente.getTipoCli());
    }

    @Test
    public void testSettersAndGetters() {
        Cliente cliente = new Cliente("12345678A", "Carlos", "Lopez", "carlos@example.com", "555555555", "08003", "Avenida Principal 789", EnumTipoCliente.MAYORISTA);

        cliente.setId(2);
        cliente.setDni("87654321B");
        cliente.setNombre("Roberto");
        cliente.setApellidos("Martínez");
        cliente.setCorreo("roberto@example.com");
        cliente.setTelefono("111111111");
        cliente.setCodigoPostal("08004");
        cliente.setDireccion("Calle Nueva 101");
        cliente.setTipoCli(EnumTipoCliente.MINORISTA);

        assertEquals(2, cliente.getId());
        assertEquals("87654321B", cliente.getDni());
        assertEquals("Roberto", cliente.getNombre());
        assertEquals("Martínez", cliente.getApellidos());
        assertEquals("roberto@example.com", cliente.getCorreo());
        assertEquals("111111111", cliente.getTelefono());
        assertEquals("08004", cliente.getCodigoPostal());
        assertEquals("Calle Nueva 101", cliente.getDireccion());
        assertEquals(EnumTipoCliente.MINORISTA, cliente.getTipoCli());
    }

}