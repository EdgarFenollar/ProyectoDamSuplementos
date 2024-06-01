package empleados;

import proveedores.MenuProveedores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public MenuCrearEmpleados() {
        setLayout(new BorderLayout());
        add(panelEmpleados, BorderLayout.CENTER);

        btnConfirmar.setBorder(null);
        btnConfirmar.setBackground(null);
        btnConfirmar.setOpaque(false);
        btnCancelar.setBorder(null);
        btnCancelar.setBackground(null);
        btnCancelar.setOpaque(false);

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

            }
        });
    }
}
