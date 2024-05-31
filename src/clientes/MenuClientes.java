package clientes;

import managers.ClienteManager;
import managers.ProveedorManager;
import proveedores.MenuCrearProveedores;
import proveedores.MenuEditarProveedores;
import managers.DataManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class MenuClientes extends JPanel {
    private JPanel panelClientes;
    private JButton btnBuscar;
    private JButton btnBorrar;
    private JButton btnEditar;
    private JButton btnCrear;
    private JButton btnFiltrar;
    private JTable tableInfo;
    private JPanel panelTablaClientes;
    private JComboBox<String> comboBoxFiltrar;
    private JTextField txtBuscar;
    private JScrollPane scrollTable;
    private JLabel lblBuscar;

    public MenuClientes() {
        setLayout(new BorderLayout());
        add(panelClientes, BorderLayout.CENTER);


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
                panelClientes.setLayout(new BorderLayout());
                panelClientes.removeAll();  // Remove any existing components
                panelClientes.add(new MenuCrearProveedores(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelClientes.revalidate();  // Revalidate to apply layout changes
                panelClientes.repaint();  // Repaint to refresh the component
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelClientes.setLayout(new BorderLayout());
                panelClientes.removeAll();  // Remove any existing components
                panelClientes.add(new MenuEditarProveedores(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelClientes.revalidate();  // Revalidate to apply layout changes
                panelClientes.repaint();  // Repaint to refresh the component
            }
        });
    }

    private void setButtonIcon(JButton button, String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image iconImage = icon.getImage();
        Image scaledIconImage = iconImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledIconImage));
    }

    public static void createTable(JTable tabla) {
        try {
            if (ClienteManager.clientes == null) {
                ClienteManager.clientes = new ArrayList<>();
            }

            String[][] data = new String[ClienteManager.clientes.size()][5];
            cargarClientes(data);

            String[] cabe = {"ID", "DNI", "Nombre", "Apellidos", "Correo", "Telefono", "Codigo Postal", "Direccion", "Tipo"};

            tabla.setModel(new DefaultTableModel(data, cabe));
            tabla.getTableHeader().setReorderingAllowed(false);
            tabla.setEnabled(true);
            tabla.setDefaultEditor(Object.class, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[][] cargarClientes(String[][] data) {
        try {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if (ClienteManager.clientes.get(i) != null) {
                        if (j == 0) {
                            data[i][j] = String.valueOf(ClienteManager.clientes.get(i).getId());
                        } else if (j == 1) {
                            data[i][j] = String.valueOf(ClienteManager.clientes.get(i).getDni());
                        } else if (j == 2) {
                            data[i][j] = String.valueOf(ClienteManager.clientes.get(i).getNombre());
                        } else if (j == 3) {
                            data[i][j] = String.valueOf(ClienteManager.clientes.get(i).getApellidos());
                        } else if (j == 4) {
                            data[i][j] = String.valueOf(ClienteManager.clientes.get(i).getCorreo());
                        } else if (j == 5) {
                            data[i][j] = String.valueOf(ClienteManager.clientes.get(i).getTelefono());
                        } else if (j == 6) {
                            data[i][j] = String.valueOf(ClienteManager.clientes.get(i).getCodigoPostal());
                        } else if (j == 7) {
                            data[i][j] = String.valueOf(ClienteManager.clientes.get(i).getDireccion());
                        } else if (j == 8) {
                            data[i][j] = String.valueOf(ClienteManager.clientes.get(i).getTipo());
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading students", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }
}
