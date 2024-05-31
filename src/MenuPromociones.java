import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Esta clase sirve para crear el panel utilizado el cual servira para visualizar las promociones, y poder editar, borrar o crear otras promociones.
 * @version: 0.1
 */

public class MenuPromociones extends JPanel {
    private JPanel panelProveedores;
    private JButton btnBuscar;
    private JButton btnBorrar;
    private JButton btnEditar;
    private JButton btnCrear;
    private JButton btnFiltrar;
    private JTable tableInfo;
    private JPanel panelTablaPromociones;
    private JComboBox<String> comboBoxFiltrar;
    private JTextField txtBuscar;
    private JScrollPane scrollTable;
    private JLabel lblBuscar;

    public MenuPromociones() {
        setLayout(new BorderLayout());
        add(panelProveedores, BorderLayout.CENTER);


        createTable(tableInfo);

        // Establecer bordes y apariencia de los botones
        btnBorrar.setBorder(null);
        btnBorrar.setOpaque(false);
        btnCrear.setBorder(null);
        btnCrear.setOpaque(false);
        btnEditar.setBorder(null);
        btnEditar.setOpaque(false);
        btnBuscar.setBorder(null);
        btnBuscar.setOpaque(false);
        btnFiltrar.setBorder(null);
        btnFiltrar.setOpaque(false);

        // Redimensionar e insertar iconos en los botones
        setButtonIcon(btnCrear, "imagenes/create.png");
        setButtonIcon(btnEditar, "imagenes/edit.png");
        setButtonIcon(btnBorrar, "imagenes/delete.png");
        setButtonIcon(btnBuscar, "imagenes/lupa.png");
        setButtonIcon(btnFiltrar, "imagenes/filtrar.png");

        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelProveedores.setLayout(new BorderLayout());
                panelProveedores.removeAll();  // Remove any existing components
                panelProveedores.add(new MenuCrearPromociones(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelProveedores.revalidate();  // Revalidate to apply layout changes
                panelProveedores.repaint();  // Repaint to refresh the component
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelProveedores.setLayout(new BorderLayout());
                panelProveedores.removeAll();  // Remove any existing components
                panelProveedores.add(new MenuEditarPromociones(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelProveedores.revalidate();  // Revalidate to apply layout changes
                panelProveedores.repaint();  // Repaint to refresh the component
            }
        });
        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int eliminar = JOptionPane.showConfirmDialog(null, "Seguro que quieres eliminar la promoción?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (eliminar == 0){

                }
            }
        });
    }

    private void setButtonIcon(JButton button, String iconPath) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(iconPath)));
        Image iconImage = icon.getImage();
        Image scaledIconImage = iconImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledIconImage));
    }

    public static void createTable(JTable tabla) {
        try {
            if (DataManager.promociones == null) {
                DataManager.promociones = new ArrayList<>();
            }

            String[][] data = new String[DataManager.promociones.size()][5];
            cargarPromociones(data);

            String[] cabe = {"ID", "Descripción", "Descuento", "Fecha Inicio", "Fecha Fin"};

            tabla.setModel(new DefaultTableModel(data, cabe));
            tabla.getTableHeader().setReorderingAllowed(false);
            tabla.setEnabled(true);
            tabla.setDefaultEditor(Object.class, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[][] cargarPromociones(String[][] data) {
        try {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if (DataManager.promociones.get(i) != null) {
                        if (j == 0) {
                            data[i][j] = String.valueOf(DataManager.promociones.get(i).getId());
                        } else if (j == 1) {
                            data[i][j] = String.valueOf(DataManager.promociones.get(i).getDescripcion());
                        } else if (j == 2) {
                            data[i][j] = String.valueOf(DataManager.promociones.get(i).getDescuento());
                        } else if (j == 3) {
                            data[i][j] = String.valueOf(DataManager.promociones.get(i).getFechaInicio());
                        } else if (j == 4) {
                            data[i][j] = String.valueOf(DataManager.promociones.get(i).getFechaFin());
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las promociones", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }
}
