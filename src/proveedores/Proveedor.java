package proveedores;


/**
 * Esta clase representa un proveedor con sus detalles básicos.
 */
public class Proveedor {
    private int id;
    private String nombre;
    private String correo;
    private String cp;
    private String direccion;

    /**
     * Constructor para inicializar un proveedor con todos sus detalles.
     *
     * @param id        el identificador del proveedor.
     * @param nombre    el nombre del proveedor.
     * @param correo    el correo electrónico del proveedor.
     * @param cp        el código postal del proveedor.
     * @param direccion la dirección del proveedor.
     */
    public Proveedor(int id, String nombre, String correo, String cp, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.cp = cp;
        this.direccion = direccion;
    }

    /**
     * Constructor para inicializar un proveedor sin su identificador.
     *
     * @param nombre    el nombre del proveedor.
     * @param correo    el correo electrónico del proveedor.
     * @param cp        el código postal del proveedor.
     * @param direccion la dirección del proveedor.
     */
    public Proveedor(String nombre, String correo, String cp, String direccion) {
        this.nombre = nombre;
        this.correo = correo;
        this.cp = cp;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCp() {
        return cp;
    }

    public String getDireccion() {
        return direccion;
    }
}
