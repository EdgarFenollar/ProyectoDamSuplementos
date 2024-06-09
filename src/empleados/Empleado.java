package empleados;

import java.time.LocalDate;
import java.security.*;

/**
 * La clase Empleado representa a un empleado de la empresa.
 * Contiene información personal y de contacto del empleado,
 * así como sus credenciales de usuario y detalles administrativos.
 */
public class Empleado {
    private int id;
    private String dni;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    private String direccion;
    private LocalDate fechaNacimiento;
    private int administrador;
    private String usuario;
    private String contrasenya;

    /**
     * Constructor completo para la clase Empleado.
     *
     * @param id            el ID único del empleado.
     * @param dni           el DNI del empleado.
     * @param nombre        el nombre del empleado.
     * @param apellidos     los apellidos del empleado.
     * @param correo        el correo electrónico del empleado.
     * @param telefono      el número de teléfono del empleado.
     * @param direccion     la dirección del empleado.
     * @param fechaNacimiento la fecha de nacimiento del empleado.
     * @param administrador indica si el empleado tiene privilegios de administrador (1 si es administrador, 0 si no lo es).
     * @param usuario       el nombre de usuario del empleado para el sistema.
     * @param contrasenya   la contraseña del empleado para el sistema.
     */
    public Empleado(int id, String dni, String nombre, String apellidos, String correo, String telefono, String direccion, LocalDate fechaNacimiento, int administrador, String usuario, String contrasenya) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.administrador = administrador;
        this.usuario = usuario;
        this.contrasenya = contrasenya;
    }

    /**
     * Constructor parcial para la clase Empleado sin el ID.
     * Este constructor puede ser utilizado cuando el ID es asignado automáticamente por la base de datos.
     *
     * @param dni           el DNI del empleado.
     * @param nombre        el nombre del empleado.
     * @param apellidos     los apellidos del empleado.
     * @param correo        el correo electrónico del empleado.
     * @param telefono      el número de teléfono del empleado.
     * @param direccion     la dirección del empleado.
     * @param fechaNacimiento la fecha de nacimiento del empleado.
     * @param administrador indica si el empleado tiene privilegios de administrador (1 si es administrador, 0 si no lo es).
     * @param usuario       el nombre de usuario del empleado para el sistema.
     * @param contrasenya   la contraseña del empleado para el sistema.
     */
    public Empleado(String dni, String nombre, String apellidos, String correo, String telefono, String direccion, LocalDate fechaNacimiento, int administrador, String usuario, String contrasenya) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.administrador = administrador;
        this.usuario = usuario;
        this.contrasenya = contrasenya;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getAdministrador() {
        return administrador;
    }

    public void setAdministrador(int administrador) {
        this.administrador = administrador;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }
}
