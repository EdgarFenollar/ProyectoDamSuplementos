package proveedores;

import DBManager.DBManager;
import empleados.MenuEmpleados;
import managers.EmpleadoManager;
import managers.ProveedorManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

/**
 * Esta clase sirve para crear el panel utilizado para editar los proveedores.
 * @version: 0.1
 */

public class MenuEditarProveedores extends JPanel{
    private JPanel panelProveedores;
    private JPanel panelEditarProveedores;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JLabel imgProveedor;
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JTextField txtPostal;
    private JTextField txtDireccion;
    public static String nombre, correo, cp, direccion;
    public static int id;
    private JLabel imgProveedorGrande;

    public MenuEditarProveedores(){
        setLayout(new BorderLayout());
        add(panelProveedores, BorderLayout.CENTER);

        btnConfirmar.setBorder(null);
        btnConfirmar.setBackground(null);
        btnConfirmar.setOpaque(false);
        btnCancelar.setBorder(null);
        btnCancelar.setBackground(null);
        btnCancelar.setOpaque(false);

        txtNombre.setText(nombre);
        txtCorreo.setText(correo);
        txtPostal.setText(cp);
        txtDireccion.setText(direccion);

        txtNombre.setSize(new Dimension(100, 100));

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

        //Redimensionar Imagen PROVEEDOR//
        ImageIcon proveedorPrinc = new ImageIcon("imagenes/proveedor.png");
        Image proveedorPrincImage = proveedorPrinc.getImage();
        Image proveedorPrincImageScaledInstance = proveedorPrincImage.getScaledInstance(20, 20,  Image.SCALE_SMOOTH);
        ImageIcon proveedor = new ImageIcon(proveedorPrincImageScaledInstance);
        imgProveedor.setIcon(proveedor);
        //////////////////////////////

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelProveedores.setLayout(new BorderLayout());
                panelProveedores.removeAll();  // Remove any existing components
                panelProveedores.add(new MenuProveedores(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelProveedores.revalidate();  // Revalidate to apply layout changes
                panelProveedores.repaint();  // Repaint to refresh the component
            }
        });

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtNombre.getText().isEmpty() && !txtCorreo.getText().isEmpty() && !txtPostal.getText().isEmpty() && !txtDireccion.getText().isEmpty()){
                        DBManager.editarProveedores(
                                id,
                                txtNombre.getText(),
                                txtCorreo.getText(),
                                txtPostal.getText(),
                                txtDireccion.getText());
                        ProveedorManager.getProveedores();
                        // Volver Atras
                        panelEditarProveedores.setLayout(new BorderLayout());
                        panelEditarProveedores.removeAll();  // Remove any existing components
                        panelEditarProveedores.add(new MenuProveedores(), BorderLayout.CENTER);  // Add new Dashboard panel
                        panelEditarProveedores.revalidate();  // Revalidate to apply layout changes
                        panelEditarProveedores.repaint();  // Repaint to refresh the component
                    } else {
                        JOptionPane.showMessageDialog(null, "Debes de introducir todos los datos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public static void insertarDatos(int idI, String nombreI, String correoI, String codigoPostalI, String direccionI){
        id = idI;
        nombre = nombreI;
        correo = correoI;
        cp = codigoPostalI;
        direccion = direccionI;
    }
}
