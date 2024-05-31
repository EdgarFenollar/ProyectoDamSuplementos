package managers;

import promociones.Promocion;
import proveedores.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public static void cargarDatos(){
        ProveedorManager.proveedores.add(new Proveedor(1, "wadwad", "awdwadda", "awda", "wadwadaw"));
    }
}
