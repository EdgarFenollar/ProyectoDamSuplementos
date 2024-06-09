package categorias;

import DBManager.DBManager;
import managers.CategoriaManager;
import managers.ProveedorManager;
import proveedores.MenuCrearProveedores;
import proveedores.MenuEditarProveedores;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Esta clase sirve para crear el panel utilizado el cual servira para visualizar las categorias, y poder editar, borrar o crear otras categorias.
 * @version: 0.1
 */

public class MenuCategorias extends JPanel {
    private JPanel panelCategorias;
    private JButton btnBuscar;
    private JButton btnEditar;
    private JButton btnCrear;
    private JTable tableInfo;
    private JPanel panelTablaCategorias;
    private JComboBox<String> comboBoxFiltrar;
    private JTextField txtBuscar;
    private JScrollPane scrollTable;
    private JLabel lblBuscar;

    /**
     * Constructor de la clase MenuCategorias.
     */
    public MenuCategorias() {
        CategoriaManager.getCategorias();
        setLayout(new BorderLayout());
        add(panelCategorias, BorderLayout.CENTER);

        btnEditar.setBackground(null);
        btnCrear.setBackground(null);

        createTable(tableInfo);

        // Establecer bordes y apariencia de los botones
        btnCrear.setBorder(null);
        btnCrear.setOpaque(false);
        btnEditar.setBorder(null);
        btnEditar.setOpaque(false);
        btnBuscar.setBorder(null);
        btnBuscar.setOpaque(false);

        // Redimensionar e insertar iconos en los botones
        setButtonIcon(btnCrear, "imagenes/create.png");
        setButtonIcon(btnEditar, "imagenes/edit.png");
        setButtonIcon(btnBuscar, "imagenes/lupa.png");

        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCategorias.setLayout(new BorderLayout());
                panelCategorias.removeAll();  // Remove any existing components
                panelCategorias.add(new MenuCrearCategorias(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelCategorias.revalidate();  // Revalidate to apply layout changes
                panelCategorias.repaint();  // Repaint to refresh the component
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableInfo.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Debes de seleccionar un categoria para editar.");
                } else {
                    for (int i = 0; i < CategoriaManager.categorias.size(); i++) {
                        if (i == tableInfo.getSelectedRow()){
                            MenuEditarCategorias.insertarDatos(
                                    CategoriaManager.categorias.get(i).getId(),
                                    CategoriaManager.categorias.get(i).getNombre(),
                                    CategoriaManager.categorias.get(i).getDescripcion());

                        }
                    }

                    panelCategorias.setLayout(new BorderLayout());
                    panelCategorias.removeAll();  // Remove any existing components
                    panelCategorias.add(new MenuEditarCategorias(), BorderLayout.CENTER);  // Add new Dashboard panel
                    panelCategorias.revalidate();  // Revalidate to apply layout changes
                    panelCategorias.repaint();  // Repaint to refresh the component
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBManager.getCategoriasPorFiltro(Objects.requireNonNull(comboBoxFiltrar.getSelectedItem()).toString(), txtBuscar.getText());
                createTable(tableInfo);
            }
        });
    }

    /**
     * Establece el ícono de un botón.
     * @param button El botón al cual se le va a establecer el ícono.
     * @param iconPath La ruta del ícono.
     */
    private void setButtonIcon(JButton button, String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image iconImage = icon.getImage();
        Image scaledIconImage = iconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledIconImage));
    }

    /**
     * Crea la tabla de categorías.
     * @param tabla La tabla en la que se mostrarán las categorías.
     */
    public static void createTable(JTable tabla) {
        try {
            if (CategoriaManager.categorias == null) {
                CategoriaManager.categorias = new ArrayList<>();
            }

            String[][] data = new String[CategoriaManager.categorias.size()][5];
            cargarProveedores(data);

            String[] cabe = {"ID", "Nombre", "Descripcion"};

            tabla.setModel(new DefaultTableModel(data, cabe));
            tabla.getTableHeader().setReorderingAllowed(false);
            tabla.setEnabled(true);
            tabla.setDefaultEditor(Object.class, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga los proveedores en la tabla.
     * @param data La matriz de datos a cargar en la tabla.
     * @return La matriz de datos cargada.
     */
    public static String[][] cargarProveedores(String[][] data) {
        try {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if (CategoriaManager.categorias.get(i) != null) {
                        if (j == 0) {
                            data[i][j] = String.valueOf(CategoriaManager.categorias.get(i).getId());
                        } else if (j == 1) {
                            data[i][j] = String.valueOf(CategoriaManager.categorias.get(i).getNombre());
                        } else if (j == 2) {
                            data[i][j] = String.valueOf(CategoriaManager.categorias.get(i).getDescripcion());
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las categorias", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }
}
