package categorias;

import proveedores.MenuProveedores;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase sirve para crear el panel utilizado para crear las categorias.
 * @version: 0.1
 */

public class MenuCrearCategorias extends JPanel{
    private JPanel panelCategorias;
    private JPanel panelCrearCategorias;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JLabel imgProveedor;
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JLabel imgProveedorGrande;

    public MenuCrearCategorias(){
        setLayout(new BorderLayout());
        add(panelCategorias, BorderLayout.CENTER);

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

        //Redimensionar Imagen CATEGORIAS//
        ImageIcon categoriaPrinc = new ImageIcon("imagenes/categoria.png");
        Image categoriaPrincImage = categoriaPrinc.getImage();
        Image categoriaPrincImageScaledInstance = categoriaPrincImage.getScaledInstance(20, 20,  Image.SCALE_SMOOTH);
        ImageIcon categoria = new ImageIcon(categoriaPrincImageScaledInstance);
        imgProveedor.setIcon(categoria);
        //////////////////////////////

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCategorias.setLayout(new BorderLayout());
                panelCategorias.removeAll();  // Remove any existing components
                panelCategorias.add(new MenuCategorias(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelCategorias.revalidate();  // Revalidate to apply layout changes
                panelCategorias.repaint();  // Repaint to refresh the component
            }
        });
    }
}
