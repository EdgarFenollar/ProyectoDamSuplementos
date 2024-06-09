package categorias;

import DBManager.DBManager;
import managers.CategoriaManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Esta clase sirve para crear el panel utilizado para crear las categorias.
 * @version: 0.1
 */

public class MenuEditarCategorias extends JPanel{
    private JPanel panelCategorias;
    private JPanel panelEditarCategorias;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JLabel imgCategoria;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JLabel imgProveedorGrande;
    public static String nombre,descripcion;
    public static int id;

    public MenuEditarCategorias(){
        setLayout(new BorderLayout());
        add(panelCategorias, BorderLayout.CENTER);

        btnConfirmar.setBorder(null);
        btnConfirmar.setBackground(null);
        btnConfirmar.setOpaque(false);
        btnCancelar.setBorder(null);
        btnCancelar.setBackground(null);
        btnCancelar.setOpaque(false);

        txtDescripcion.setText(descripcion);
        txtNombre.setText(nombre);

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

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtNombre.getText().isEmpty() && !txtDescripcion.getText().isEmpty()) {
                        DBManager.editarCategoria(
                                id,
                                txtNombre.getText(),
                                txtDescripcion.getText());
                        CategoriaManager.getCategorias();
                        // Volver Atras
                        panelEditarCategorias.setLayout(new BorderLayout());
                        panelEditarCategorias.removeAll();  // Remove any existing components
                        panelEditarCategorias.add(new MenuCategorias(), BorderLayout.CENTER);  // Add new Dashboard panel
                        panelEditarCategorias.revalidate();  // Revalidate to apply layout changes
                        panelEditarCategorias.repaint();  // Repaint to refresh the component
                    } else {
                        JOptionPane.showMessageDialog(null, "Debes de introducir todos los datos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public static void insertarDatos(int idI, String nombreI, String descripcionI){
        id = idI;
        nombre = nombreI;
        descripcion = descripcionI;
    }

}

