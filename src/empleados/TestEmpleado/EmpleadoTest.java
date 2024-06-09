package empleados.TestEmpleado;
import empleados.Empleado;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias, asegurr que la clase Empleado funcione correctamente en términos de creación y manipulación de objetos.
 */


class EmpleadoTest  {
    private Empleado empleado;

    @BeforeEach
    public void setUp() {
        empleado = new Empleado(1, "12345678A", "Juan", "Pérez", "juan.perez@example.com", "123456789", "Calle Falsa 123", LocalDate.of(1980, 1, 1), 1, "juanperez", "password");
    }

    @Test
    public void testGetters() {
        assertEquals(1, empleado.getId());
        assertEquals("12345678A", empleado.getDni());
        assertEquals("Juan", empleado.getNombre());
        assertEquals("Pérez", empleado.getApellidos());
        assertEquals("juan.perez@example.com", empleado.getCorreo());
        assertEquals("123456789", empleado.getTelefono());
        assertEquals("Calle Falsa 123", empleado.getDireccion());
        assertEquals(LocalDate.of(1980, 1, 1), empleado.getFechaNacimiento());
        assertEquals(1, empleado.getAdministrador());
        assertEquals("juanperez", empleado.getUsuario());
        assertEquals("password", empleado.getContrasenya());
    }

    @Test
    public void testSetters() {
        empleado.setId(2);
        empleado.setDni("87654321B");
        empleado.setNombre("Ana");
        empleado.setApellidos("Gómez");
        empleado.setCorreo("ana.gomez@example.com");
        empleado.setTelefono("987654321");
        empleado.setDireccion("Avenida Siempreviva 742");
        empleado.setFechaNacimiento(LocalDate.of(1990, 2, 2));
        empleado.setAdministrador(0);
        empleado.setUsuario("anagomez");
        empleado.setContrasenya("newpassword");

        assertEquals(2, empleado.getId());
        assertEquals("87654321B", empleado.getDni());
        assertEquals("Ana", empleado.getNombre());
        assertEquals("Gómez", empleado.getApellidos());
        assertEquals("ana.gomez@example.com", empleado.getCorreo());
        assertEquals("987654321", empleado.getTelefono());
        assertEquals("Avenida Siempreviva 742", empleado.getDireccion());
        assertEquals(LocalDate.of(1990, 2, 2), empleado.getFechaNacimiento());
        assertEquals(0, empleado.getAdministrador());
        assertEquals("anagomez", empleado.getUsuario());
        assertEquals("newpassword", empleado.getContrasenya());
    }

    @Test
    public void testConstructorWithoutId() {
        Empleado newEmpleado = new Empleado("87654321B", "Ana", "Gómez", "ana.gomez@example.com", "987654321", "Avenida Siempreviva 742", LocalDate.of(1990, 2, 2), 0, "anagomez", "newpassword");

        assertEquals("87654321B", newEmpleado.getDni());
        assertEquals("Ana", newEmpleado.getNombre());
        assertEquals("Gómez", newEmpleado.getApellidos());
        assertEquals("ana.gomez@example.com", newEmpleado.getCorreo());
        assertEquals("987654321", newEmpleado.getTelefono());
        assertEquals("Avenida Siempreviva 742", newEmpleado.getDireccion());
        assertEquals(LocalDate.of(1990, 2, 2), newEmpleado.getFechaNacimiento());
        assertEquals(0, newEmpleado.getAdministrador());
        assertEquals("anagomez", newEmpleado.getUsuario());
        assertEquals("newpassword", newEmpleado.getContrasenya());
    }
}