package proveedores;

import managers.EmpleadoManager;
import managers.ProveedorManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase sirve para crear el panel utilizado para crear los proveedores.
 * @version: 0.1
 */

public class MenuCrearProveedores extends JPanel{
    private JPanel panelProveedores;
    private JPanel panelCrearProveedores;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JLabel imgProveedor;
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JTextField txtPostal;
    private JTextField txtDireccion;
    private JLabel imgProveedorGrande;

    public MenuCrearProveedores(){
        setLayout(new BorderLayout());
        add(panelProveedores, BorderLayout.CENTER);

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

        //Redimensionar Imagen PROVEEDOR//
        ImageIcon proveedorPrinc = new ImageIcon("imagenes/proveedor.png");
        Image proveedorPrincImage = proveedorPrinc.getImage();
        Image proveedorPrincImageScaledInstance = proveedorPrincImage.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);
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
                        ProveedorManager.anyadirProveedor(new Proveedor(
                                txtNombre.getText(),
                                txtCorreo.getText(),
                                txtPostal.getText(),
                                txtDireccion.getText()));
                        ProveedorManager.getProveedores();
                        // Volver Atras
                        panelCrearProveedores.setLayout(new BorderLayout());
                        panelCrearProveedores.removeAll();
                        panelCrearProveedores.add(new MenuProveedores(), BorderLayout.CENTER);
                        panelCrearProveedores.revalidate();
                        panelCrearProveedores.repaint();
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
}
