package clientes;

/**
 * Esta clase representa un cliente con sus detalles básicos como DNI, nombre, apellidos, correo, teléfono, código postal, dirección y tipo de cliente.
 * Proporciona métodos para obtener y establecer estos valores.
 *
 * @version 1.0
 */

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

    /**
     * Constructor para crear un cliente con todos los detalles, incluido el ID.
     *
     * @param id El ID del cliente.
     * @param dni El DNI del cliente.
     * @param nombre El nombre del cliente.
     * @param apellidos Los apellidos del cliente.
     * @param correo El correo electrónico del cliente.
     * @param telefono El número de teléfono del cliente.
     * @param codigoPostal El código postal del cliente.
     * @param direccion La dirección del cliente.
     * @param tipoCli El tipo de cliente (EnumTipoCliente).
     */

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
    /**
     * Constructor para crear un cliente sin especificar el ID.
     *
     * @param dni El DNI del cliente.
     * @param nombre El nombre del cliente.
     * @param apellidos Los apellidos del cliente.
     * @param correo El correo electrónico del cliente.
     * @param telefono El número de teléfono del cliente.
     * @param codigoPostal El código postal del cliente.
     * @param direccion La dirección del cliente.
     * @param tipoCli El tipo de cliente (EnumTipoCliente).
     */

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

    public void setId(int id) {
        this.id = id;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTipoCli(EnumTipoCliente tipoCli) {
        this.tipoCli = tipoCli;
    }

    public EnumTipoCliente getTipoCli() {
        return tipoCli;
    }
}
