package managers;

import promociones.Promocion;
import proveedores.Proveedor;

import java.util.ArrayList;
import java.util.List;

import static managers.EmpleadoManager.anyadirEmpleado;

public class DataManager {
    public static void cargarDatos(){
        anyadirEmpleado();
        ProveedorManager.proveedores.add(new Proveedor(1, "wadwad", "awdwadda", "awda", "wadwadaw"));
    }
}
