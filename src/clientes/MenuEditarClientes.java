package clientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEditarClientes extends JPanel{
    private JPanel panelProveedores;
    private JPanel panelCrearProveedores;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JLabel imgProveedor;
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JTextField txtPostal;
    private JTextField txtTelefono;
    private JRadioButton radioButtonMinorista;
    private JRadioButton radioButtonMayorista;
    private JTextField txtDireccion;
    private JLabel imgProveedorGrande;

    public MenuEditarClientes(){
        setLayout(new BorderLayout());
        add(panelProveedores, BorderLayout.CENTER);

        btnConfirmar.setBorder(null);
        btnConfirmar.setBackground(null);
        btnConfirmar.setOpaque(false);
        btnCancelar.setBorder(null);
        btnCancelar.setBackground(null);
        btnCancelar.setOpaque(false);

        //Redimensionar Imagen CANCELAR//
        ImageIcon cancelPrinc = new ImageIcon("imagenes/x.png");
        Image cancelPrincImage = cancelPrinc.getImage();
        Image cancelPrincImageScaledInstance = cancelPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon cancel = new ImageIcon(cancelPrincImageScaledInstance);
        btnCancelar.setIcon(cancel);
        //////////////////////////////

        //Redimensionar Imagen CREAR//
        ImageIcon crearPrinc = new ImageIcon("imagenes/save.png");
        Image crearPrincImage = crearPrinc.getImage();
        Image crearPrincImageScaledInstance = crearPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon crear = new ImageIcon(crearPrincImageScaledInstance);
        btnConfirmar.setIcon(crear);
        //////////////////////////////

        //Redimensionar Imagen CLIENTE//
        ImageIcon proveedorPrinc = new ImageIcon("imagenes/clientes.png");
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
                panelProveedores.add(new MenuClientes(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelProveedores.revalidate();  // Revalidate to apply layout changes
                panelProveedores.repaint();  // Repaint to refresh the component
            }
        });
    }
}
