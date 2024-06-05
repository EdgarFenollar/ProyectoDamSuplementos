package empleados;

import DBManager.DBManager;
import com.toedter.calendar.JDateChooser;
import managers.EmpleadoManager;
import principal.Login;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

/**
 * Esta clase es utilizada para el panel de editar los Empleados.
 * @version: 0.2
 */

public class MenuEditarEmpleados extends JPanel {
    private JPanel panelCrearEmpleados;
    private JLabel imgPromocion;
    private JButton btnCancelar;
    private JButton btnConfirmar;
    private JPanel panelEmpleados;
    private JTextField txtDni;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JTextField txtUsuario;
    public static String dni, nombre, apellidos, correo, telefono, direccion, usuario, fechaNac;
    public static int admin, id;
    private JPasswordField txtContrasenya;
    private JPanel panelFecha1;
    private JCheckBox checkAdmin;
    private JDateChooser dateChooser1 = new JDateChooser();

    public MenuEditarEmpleados() {
        setLayout(new BorderLayout());

        txtDni.setText(dni);
        txtNombre.setText(nombre);
        txtApellidos.setText(apellidos);
        txtCorreo.setText(correo);
        txtTelefono.setText(telefono);
        txtDireccion.setText(direccion);
        txtUsuario.setText(usuario);
        try {
            dateChooser1.setDateFormatString(fechaNac);
        } catch (Exception e){
            e.printStackTrace();
        }
        checkAdmin.setSelected(admin == 1);


        add(panelEmpleados, BorderLayout.CENTER);

        btnConfirmar.setBorder(null);
        btnConfirmar.setBackground(null);
        btnConfirmar.setOpaque(false);
        btnCancelar.setBorder(null);
        btnCancelar.setBackground(null);
        btnCancelar.setOpaque(false);

        // FECHAS//
        panelFecha1.setLayout(new FlowLayout());
        dateChooser1.setDateFormatString("yyyy-MM-dd");
        JTextField dateEditor1 = (JTextField) dateChooser1.getDateEditor().getUiComponent();
        dateEditor1.setEditable(false);
        panelFecha1.add(dateChooser1);
        dateChooser1.setPreferredSize(new Dimension(485, 40));


        //Redimensionar Imagen CANCELAR//
        ImageIcon cancelPrinc = new ImageIcon("imagenes/x.png");
        Image cancelPrincImage = cancelPrinc.getImage();
        Image cancelPrincImageScaledInstance = cancelPrincImage.getScaledInstance(20, 20,  Image.SCALE_SMOOTH);
        ImageIcon cancel = new ImageIcon(cancelPrincImageScaledInstance);
        btnCancelar.setIcon(cancel);
        //////////////////////////////

        //Redimensionar Imagen CREAR//
        ImageIcon crearPrinc = new ImageIcon("imagenes/save.png");
        Image crearPrincImage = crearPrinc.getImage();
        Image crearPrincImageScaledInstance = crearPrincImage.getScaledInstance(20, 20,  Image.SCALE_SMOOTH);
        ImageIcon crear = new ImageIcon(crearPrincImageScaledInstance);
        btnConfirmar.setIcon(crear);
        //////////////////////////////

        //Redimensionar Imagen EMPLEADO//
        ImageIcon proveedorPrinc = new ImageIcon("imagenes/employe.png");
        Image proveedorPrincImage = proveedorPrinc.getImage();
        Image proveedorPrincImageScaledInstance = proveedorPrincImage.getScaledInstance(20, 20,  Image.SCALE_SMOOTH);
        ImageIcon proveedor = new ImageIcon(proveedorPrincImageScaledInstance);
        imgPromocion.setIcon(proveedor);
        //////////////////////////////

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelEmpleados.setLayout(new BorderLayout());
                panelEmpleados.removeAll();  // Remove any existing components
                panelEmpleados.add(new MenuEmpleados(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelEmpleados.revalidate();  // Revalidate to apply layout changes
                panelEmpleados.repaint();  // Repaint to refresh the component
            }
        });

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass = String.valueOf(txtContrasenya.getPassword());
                int admin;
                if (checkAdmin.isEnabled()){
                    admin = 1;
                } else {
                    admin = 0;
                }
                try {
                    if (!txtNombre.getText().isEmpty() && !txtApellidos.getText().isEmpty() && !txtCorreo.getText().isEmpty() && !txtTelefono.getText().isEmpty() && !txtDireccion.getText().isEmpty() && !txtUsuario.getText().isEmpty() && !pass.isEmpty()){
                    for (int i = 0; i < EmpleadoManager.empleados.size(); i++) {
                        if (id == EmpleadoManager.empleados.get(i).getId()){
                            LocalDate fechanac = dateChooser1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            DBManager.editarEmpleado(
                                    id,
                                    txtDni.getText(),
                                    txtNombre.getText(),
                                    txtApellidos.getText(),
                                    txtCorreo.getText(),
                                    txtTelefono.getText(),
                                    txtDireccion.getText(),
                                    txtUsuario.getText(),
                                    encrypt(pass),
                                    fechanac,
                                    admin
                            );
                            EmpleadoManager.getEmpleados();
                        }
                    }
                    // Volver Atras
                    panelEmpleados.setLayout(new BorderLayout());
                    panelEmpleados.removeAll();  // Remove any existing components
                    panelEmpleados.add(new MenuEmpleados(), BorderLayout.CENTER);  // Add new Dashboard panel
                    panelEmpleados.revalidate();  // Revalidate to apply layout changes
                    panelEmpleados.repaint();  // Repaint to refresh the component
                    } else {
                        JOptionPane.showMessageDialog(null, "Debes de introducir todos los datos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        //// EJEMPO PRUEBA /////
        /*
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tablaVisitas.getSelectedRow() != -1){
                    int option = JOptionPane.showConfirmDialog(null, "Estas seguro de que quieres eliminar la visita?", "WARNING", JOptionPane.YES_NO_OPTION);
                    if (option == 0){
                        DataManager.visitas.remove(tablaVisitas.getSelectedRow()-1);
                        DataManager.eliminarVisita(DataManager.visitas.get(tablaVisitas.getSelectedRow()-1).getCodigo());
                        DataManager.visitas = new ArrayList<>();
                        DBManager.cargarDatosBDVisitas();
                        createTableVisitas();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error, debes de seleccionar una visita para eliminar", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
         */
    }

    public static void insertarDatos(int idI, String dniI, String nombreI, String apellidosI, String correoI, String telefonoI, String direccionI, String fechaNacI, String usuarioI, int adminI){
        id = idI;
        dni = dniI;
        nombre = nombreI;
        apellidos = apellidosI;
        correo = correoI;
        telefono = telefonoI;
        direccion = direccionI;
        fechaNac = fechaNacI;
        usuario = usuarioI;
        admin = adminI;
    }

    public static String encrypt(String text) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(Login.ENCRYPT_KEY.getBytes());

        if (decodedKey.length != 32) {
            throw new IllegalArgumentException("Longitud de clave AES no válida (debe ser 32 bytes para AES-256)");
        }

        SecretKey aesKey = new SecretKeySpec(decodedKey, "AES");

        Cipher cipher = Cipher.getInstance("AES");

        cipher.init(Cipher.ENCRYPT_MODE, aesKey);

        byte[] encrypted = cipher.doFinal(text.getBytes());

        return Base64.getEncoder().encodeToString(encrypted);
    }
}
