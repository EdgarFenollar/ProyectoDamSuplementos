package categorias;

import managers.CategoriaManager;
import managers.ProveedorManager;
import proveedores.MenuProveedores;
import proveedores.Proveedor;

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
    private JLabel imgCategoria;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JLabel imgProveedorGrande;

    /**
     * Constructor de la clase MenuCrearCategorias.
     */
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
        imgCategoria.setIcon(categoria);
        //////////////////////////////

        /**
         * Acción realizada cuando se presiona el botón "Cancelar".
         * Elimina todos los componentes existentes del panel y añade el panel de categorías,
         * aplicando los cambios necesarios en el diseño y repintando el componente.
         *
         * @param e El evento de acción que desencadena el método.
         */
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCategorias.setLayout(new BorderLayout());
                panelCategorias.removeAll();// Eliminar cualquier componente existente
                panelCategorias.add(new MenuCategorias(), BorderLayout.CENTER);// Añadir el nuevo panel de categorías
                panelCategorias.revalidate(); // Revalidar para aplicar los cambios de diseño
                panelCategorias.repaint();  // Repintar para refrescar el componente
            }
        });

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtNombre.getText().isEmpty() && !txtDescripcion.getText().isEmpty()) {
                        CategoriaManager.anyadirCategoria(new Categoria(
                                txtNombre.getText(),
                                txtDescripcion.getText()));
                        CategoriaManager.getCategorias();
                        // Volver Atras
                        panelCrearCategorias.setLayout(new BorderLayout());
                        panelCrearCategorias.removeAll();  // Remove any existing components
                        panelCrearCategorias.add(new MenuCategorias(), BorderLayout.CENTER);  // Add new Dashboard panel
                        panelCrearCategorias.revalidate();  // Revalidate to apply layout changes
                        panelCrearCategorias.repaint();  // Repaint to refresh the component
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
