package clientes;

public class Cliente {
    private int id;
    private String dni;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private String codigoPostal;
    private String direccion;
    private EnumTipoCliente tipoCli;

    public Cliente(int id, String dni, String nombre, String apellidos, String correo, String telefono, String codigoPostal, String direccion, EnumTipoCliente tipoCli) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.codigoPostal = codigoPostal;
        this.direccion = direccion;
        this.tipoCli = tipoCli;
    }

    public Cliente(String dni, String nombre, String apellidos, String correo, String telefono, String codigoPostal, String direccion, EnumTipoCliente tipoCli) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.codigoPostal = codigoPostal;
        this.direccion = direccion;
        this.tipoCli = tipoCli;
    }

    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public String getDireccion() {
        return direccion;
    }


    public EnumTipoCliente getTipoCli() {
        return tipoCli;
    }
}
