package managers;

import empleados.Empleado;
import DBManager.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static empleados.MenuCrearEmpleados.encrypt;

public class EmpleadoManager {
    public static List<Empleado> empleados = new ArrayList<>();

    public static boolean getEmpleados(){
        if (DBManager.connect()){
            try(ResultSet rs = DBManager.getTableDataBase("SELECT * FROM EMPLEADOS")){
                empleados = new ArrayList<>();

                while (rs.next()){
                    int codigo = rs.getInt(1);
                    String dni =rs.getString(2);
                    String nombre =rs.getString(3);
                    String apellidos =rs.getString(4);
                    String correo =rs.getString(5);
                    String telefono =rs.getString(6);
                    String direccion =rs.getString(7);
                    String fechanac =rs.getString(8);
                    String usuario =rs.getString(9);
                    String contrasenya =rs.getString(10);
                    String administrador =rs.getString(11);
                    empleados.add(new Empleado(codigo,dni, nombre,apellidos, correo, telefono, direccion, LocalDate.parse(fechanac), Integer.parseInt(administrador), usuario, contrasenya));
                }
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }else {
            return false;
        }
    }

    public static List<Empleado> getEmpleadoList(){
        return empleados;
    }

    public static boolean anyadirEmpleado(Empleado empleado){
        if (DBManager.connect() & DBManager.insertarEmpleado(empleado)){
            try {
                String pass = String.valueOf(empleado.getContrasenya());
                empleados.add(new Empleado(
                        empleado.getId(),
                        empleado.getDni(),
                        empleado.getNombre(),
                        empleado.getApellidos(),
                        empleado.getCorreo(),
                        empleado.getTelefono(),
                        empleado.getDireccion(),
                        empleado.getFechaNacimiento(),
                        empleado.getAdministrador(),
                        empleado.getUsuario(),
                        encrypt(pass)));
            }catch (Exception ex){
                ex.printStackTrace();
                return false;
            }finally {
                DBManager.close();
            }
            return true;
        }else {
            return false;
        }
    }

    public static boolean actualizarEmpleados(Empleado empleado){
        try {
            if (DBManager.connect()) {
                if (DBManager.actualizarEmpleados(empleado)) {
                    borrarColumnaPorId(empleado.getId());
                    empleados.add(empleado);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBManager.close();
        }
        return false;
    }

    public static boolean borrarColumnaPorId(int id){
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getId()==id){
                empleados.remove(i);
                EmpleadoManager.getEmpleados();
                return true;
            }
        }
        return false;
    }
}
