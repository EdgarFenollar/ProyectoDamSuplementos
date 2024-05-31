package ventas;

import proveedores.MenuProveedores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase sirve para crear el panel utilizado para crear los proveedores.
 * @version: 0.1
 */

public class MenuCrearVentas extends JPanel{
    private JPanel panelVentas;
    private JPanel panelEditarVentas;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JLabel imgProveedor;
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JTextField txtPostal;
    private JTextField txtDireccion;
    private JLabel imgProveedorGrande;

    public MenuCrearVentas(){
        setLayout(new BorderLayout());
        add(panelVentas, BorderLayout.CENTER);

        btnConfirmar.setBorder(null);
        btnConfirmar.setOpaque(false);
        btnCancelar.setBorder(null);
        btnCancelar.setOpaque(false);

        txtNombre.setSize(new Dimension(100, 100));

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
                panelVentas.setLayout(new BorderLayout());
                panelVentas.removeAll();  // Remove any existing components
                panelVentas.add(new MenuProveedores(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelVentas.revalidate();  // Revalidate to apply layout changes
                panelVentas.repaint();  // Repaint to refresh the component
            }
        });
    }
}
