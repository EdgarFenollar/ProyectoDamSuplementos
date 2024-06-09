package clientes;

import DBManager.DBManager;
import managers.ClienteManager;
import managers.EmpleadoManager;

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
    private JButton btnEditar;
    private JButton btnCrear;
    private JButton btnFiltrar;
    private JTable tableInfo;
    private JPanel panelTablaClientes;
    private JComboBox<String> comboBoxFiltrar;
    private JTextField txtBuscar;
    private JScrollPane scrollTable;
    private JLabel lblBuscar;

    /**
     * Esta clase sirve para crear el panel utilizado para visualizar, editar, buscar y crear clientes.
     * @version 0.1
     */

    public MenuClientes() {
        setLayout(new BorderLayout());
        add(panelClientes, BorderLayout.CENTER);

        ClienteManager.getClientes();

        createTable(tableInfo);

        // Establecer bordes y apariencia de los botones
        btnEditar.setBackground(null);
        btnCrear.setBackground(null);
        btnCrear.setBorder(null);
        btnCrear.setBackground(null);
        btnCrear.setOpaque(false);
        btnEditar.setBorder(null);
        btnEditar.setBackground(null);
        btnEditar.setOpaque(false);
        btnBuscar.setBorder(null);
        btnBuscar.setBackground(null);
        btnBuscar.setOpaque(false);
        btnFiltrar.setBorder(null);
        btnFiltrar.setBackground(null);
        btnFiltrar.setOpaque(false);

        // Redimensionar e insertar iconos en los botones
        setButtonIcon(btnCrear, "imagenes/create.png");
        setButtonIcon(btnEditar, "imagenes/edit.png");
        setButtonIcon(btnBuscar, "imagenes/lupa.png");
        setButtonIcon(btnFiltrar, "imagenes/filtrar.png");

        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelClientes.setLayout(new BorderLayout());
                panelClientes.removeAll();  // Remove any existing components
                panelClientes.add(new MenuCrearClientes(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelClientes.revalidate();  // Revalidate to apply layout changes
                panelClientes.repaint();  // Repaint to refresh the component
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableInfo.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Debes de seleccionar un cliente para editar.");
                } else {
                    for (int i = 0; i < ClienteManager.clientes.size(); i++) {
                        if (i == tableInfo.getSelectedRow()) {
                            MenuEditarClientes.insertarDatos(
                                    ClienteManager.clientes.get(i).getId(),
                                    ClienteManager.clientes.get(i).getDni(),
                                    ClienteManager.clientes.get(i).getNombre(),
                                    ClienteManager.clientes.get(i).getApellidos(),
                                    ClienteManager.clientes.get(i).getCorreo(),
                                    ClienteManager.clientes.get(i).getTelefono(),
                                    ClienteManager.clientes.get(i).getCodigoPostal(),
                                    ClienteManager.clientes.get(i).getDireccion(),
                                    String.valueOf(ClienteManager.clientes.get(i).getTipoCli()));
                        }
                    }
                    panelClientes.setLayout(new BorderLayout());
                    panelClientes.removeAll();  // Remove any existing components
                    panelClientes.add(new MenuEditarClientes(), BorderLayout.CENTER);  // Add new Dashboard panel
                    panelClientes.revalidate();  // Revalidate to apply layout changes
                    panelClientes.repaint();  // Repaint to refresh the component
                }
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBManager.getClientesPorFiltro(Objects.requireNonNull(comboBoxFiltrar.getSelectedItem()).toString(), txtBuscar.getText());
                createTable(tableInfo);
            }
        });
    }

    /**
     * Configura el icono de un botón.
     *
     * @param button El botón al que se le va a establecer el icono.
     * @param iconPath La ruta del archivo de icono.
     */
    private void setButtonIcon(JButton button, String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image iconImage = icon.getImage();
        Image scaledIconImage = iconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledIconImage));
    }

    /**
     * Crea y configura la tabla de información de los clientes.
     *
     * @param tabla La tabla donde se mostrarán los datos de los clientes.
     */
    public static void createTable(JTable tabla) {
        try {
            if (ClienteManager.clientes == null) {
                ClienteManager.clientes = new ArrayList<>();
            }

            String[][] data = new String[ClienteManager.clientes.size()][9];
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

    /**
     * Carga los datos de los clientes en una matriz de strings.
     *
     * @param data La matriz donde se cargarán los datos de los clientes.
     * @return La matriz con los datos de los clientes.
     */
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
                            data[i][j] = String.valueOf(ClienteManager.clientes.get(i).getTipoCli());
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
