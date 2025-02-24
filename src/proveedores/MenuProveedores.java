package proveedores;

import DBManager.DBManager;
import empleados.MenuEditarEmpleados;
import managers.DataManager;
import managers.EmpleadoManager;
import managers.ProveedorManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Esta clase sirve para crear el panel utilizado el cual servira para visualizar los proveedores, y poder editar, borrar o crear otros proveedores.
 * @version: 0.1
 */

public class MenuProveedores extends JPanel {
    private JPanel panelProveedores;
    private JButton btnBuscar;
    private JButton btnEditar;
    private JButton btnCrear;
    private JButton btnFiltrar;
    private JTable tableInfo;
    private JPanel panelTablaPromociones;
    private JComboBox<String> comboBoxFiltrar;
    private JTextField txtBuscar;
    private JScrollPane scrollTable;
    private JLabel lblBuscar;

    public MenuProveedores() {
        ProveedorManager.getProveedores();
        setLayout(new BorderLayout());
        add(panelProveedores, BorderLayout.CENTER);

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
        btnFiltrar.setBorder(null);
        btnFiltrar.setOpaque(false);

        // Redimensionar e insertar iconos en los botones
        setButtonIcon(btnCrear, "imagenes/create.png");
        setButtonIcon(btnEditar, "imagenes/edit.png");
        setButtonIcon(btnBuscar, "imagenes/lupa.png");
        setButtonIcon(btnFiltrar, "imagenes/filtrar.png");

        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelProveedores.setLayout(new BorderLayout());
                panelProveedores.removeAll();  // Remove any existing components
                panelProveedores.add(new MenuCrearProveedores(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelProveedores.revalidate();  // Revalidate to apply layout changes
                panelProveedores.repaint();  // Repaint to refresh the component
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableInfo.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Debes de seleccionar un proveedor para editar.");
                } else {
                    for (int i = 0; i < ProveedorManager.proveedores.size(); i++) {
                        if (i == tableInfo.getSelectedRow()){
                            MenuEditarProveedores.insertarDatos(
                                    ProveedorManager.proveedores.get(i).getId(),
                                    ProveedorManager.proveedores.get(i).getNombre(),
                                    ProveedorManager.proveedores.get(i).getCorreo(),
                                    ProveedorManager.proveedores.get(i).getCp(),
                                    ProveedorManager.proveedores.get(i).getDireccion());
                        }
                    }

                    panelProveedores.setLayout(new BorderLayout());
                    panelProveedores.removeAll();  // Remove any existing components
                    panelProveedores.add(new MenuEditarProveedores(), BorderLayout.CENTER);  // Add new Dashboard panel
                    panelProveedores.revalidate();  // Revalidate to apply layout changes
                    panelProveedores.repaint();  // Repaint to refresh the component
                }
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBManager.getProveedoresPorFiltro(Objects.requireNonNull(comboBoxFiltrar.getSelectedItem()).toString(), txtBuscar.getText());
                createTable(tableInfo);
            }
        });
    }

    private void setButtonIcon(JButton button, String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image iconImage = icon.getImage();
        Image scaledIconImage = iconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledIconImage));
    }

    public static void createTable(JTable tabla) {
        try {
            if (ProveedorManager.proveedores == null) {
                ProveedorManager.proveedores = new ArrayList<>();
            }

            String[][] data = new String[ProveedorManager.proveedores.size()][5];
            cargarProveedores(data);

            String[] cabe = {"ID", "Nombre", "Correo Electrónico", "Código Postal", "Dirección"};

            tabla.setModel(new DefaultTableModel(data, cabe));
            tabla.getTableHeader().setReorderingAllowed(false);
            tabla.setEnabled(true);
            tabla.setDefaultEditor(Object.class, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[][] cargarProveedores(String[][] data) {
        try {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if (ProveedorManager.proveedores.get(i) != null) {
                        if (j == 0) {
                            data[i][j] = String.valueOf(ProveedorManager.proveedores.get(i).getId());
                        } else if (j == 1) {
                            data[i][j] = String.valueOf(ProveedorManager.proveedores.get(i).getNombre());
                        } else if (j == 2) {
                            data[i][j] = String.valueOf(ProveedorManager.proveedores.get(i).getCorreo());
                        } else if (j == 3) {
                            data[i][j] = String.valueOf(ProveedorManager.proveedores.get(i).getCp());
                        } else if (j == 4) {
                            data[i][j] = String.valueOf(ProveedorManager.proveedores.get(i).getDireccion());
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los proveedores", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }
}
