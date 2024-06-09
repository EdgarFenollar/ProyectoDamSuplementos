package empleados;

import com.toedter.calendar.JDateChooser;
import managers.EmpleadoManager;
import principal.Login;
import proveedores.MenuProveedores;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.Key;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;

/**
 * Esta clase es utilizada para el panel de crear a los Empleados.
 * @version: 0.2
 */

public class MenuCrearEmpleados extends JPanel {
    private JPanel panelCrearEmpleados;
    private JLabel imgPromocion;
    private JButton btnCancelar;
    private JButton btnConfirmar;
    private JTextField txtDni;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtCorreo;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JTextField txtFechaNacimiento;
    private JTextField txtUsuario;
    private JPasswordField txtContrasenya;
    private JPanel panelEmpleados;
    private JCheckBox checkAdmin;
    private JPanel panelFecha1;
    private JDateChooser dateChooser1 = new JDateChooser();

    public MenuCrearEmpleados() {
        setLayout(new BorderLayout());
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
                try {
                    String pass = String.valueOf(txtContrasenya.getPassword());
                    LocalDate fechanac = dateChooser1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    int admin;
                    if (checkAdmin.isEnabled()){
                        admin = 1;
                    } else {
                        admin = 0;
                    }
                    if (!txtNombre.getText().isEmpty() && !txtApellidos.getText().isEmpty() && !txtCorreo.getText().isEmpty() && !txtTelefono.getText().isEmpty() && !txtDireccion.getText().isEmpty() && !txtUsuario.getText().isEmpty() && !pass.isEmpty()){
                    EmpleadoManager.anyadirEmpleado(new Empleado(
                            txtDni.getText(),
                            txtNombre.getText(),
                            txtApellidos.getText(),
                            txtCorreo.getText(),
                            txtTelefono.getText(),
                            txtDireccion.getText(),
                            fechanac,
                            admin,
                            txtUsuario.getText(),
                            encrypt(pass)));
                    EmpleadoManager.getEmpleados();
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
                    JOptionPane.showMessageDialog(null, "Debes de introducir todos los datos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
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
