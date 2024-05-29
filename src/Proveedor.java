public class Proveedor {
    private int id;
    private String nombre;
    private String correo;
    private String cp;
    private String direccion;

    public Proveedor(int id, String nombre, String correo, String cp, String direccion) {
        this.id = id;
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
