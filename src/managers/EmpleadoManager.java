package managers;

import clientes.Cliente;
import empleados.Empleado;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoManager {
    public static List<Empleado> empleados = new ArrayList<>();

    public static void anyadirEmpleado(){
        empleados.add(new Empleado(1, "12345678A", "Carlos", "García", "carlos.garcia@example.com", "600123456", "Calle Falsa 123", LocalDate.of(1990, 5, 15), 0, "ejemplo", "123"));
        empleados.add(new Empleado(2, "87654321B", "María", "Lopez", "maria.lopez@example.com", "610654321", "Avenida Siempreviva 742", LocalDate.of(1985, 8, 25), 1, "ejemplo2", "1234"));
    }
}
