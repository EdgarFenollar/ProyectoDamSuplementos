package compras;

import DBManager.DBManager;
import empleados.MenuCrearEmpleados;
import empleados.MenuEditarEmpleados;
import managers.CompraManager;
import managers.EmpleadoManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class MenuCompras extends JPanel {
    private JPanel panelTablaCompras;
    private JButton btnEditar;
    private JButton btnCrear;
    private JComboBox comboBoxFiltrar;
    private JLabel lblBuscar;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JScrollPane scrollTable;
    private JTable tableInfo;
    private JPanel panelCompras;

    /**
     * Constructor de la clase MenuCompras.
     * Inicializa el panel de compras y sus componentes.
     */
    public MenuCompras() {
        setLayout(new BorderLayout());
        add(panelCompras, BorderLayout.CENTER);

        CompraManager.getCompras();

        btnEditar.setBackground(null);
        btnCrear.setBackground(null);
        createTable(tableInfo);

        // Establecer bordes y apariencia de los botones
        btnEditar.setBackground(null);
        btnCrear.setBackground(null);
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
                panelCompras.setLayout(new BorderLayout());
                panelCompras.removeAll();  // Remove any existing components
                panelCompras.add(new MenuCrearCompras(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelCompras.revalidate();  // Revalidate to apply layout changes
                panelCompras.repaint();  // Repaint to refresh the component
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableInfo.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Debes de seleccionar una compra para editar.");
                } else {
                    for (int i = 0; i < CompraManager.compras.size(); i++) {
                        if (i == tableInfo.getSelectedRow()) {
                            MenuEditarCompras.insertarDatos(
                                    CompraManager.compras.get(i).getId(),
                                    CompraManager.compras.get(i).getFecha_compra(),
                                    CompraManager.compras.get(i).getId_proveedor(),
                                    CompraManager.compras.get(i).getId_producto(),
                                    CompraManager.compras.get(i).getCantidad(),
                                    CompraManager.compras.get(i).getPrecio_unitario(),
                                    CompraManager.compras.get(i).getFecha_recepcion(),
                                    CompraManager.compras.get(i).getId_empleado());
                        }
                    }

                    panelCompras.setLayout(new BorderLayout());
                    panelCompras.removeAll();  // Remove any existing components
                    panelCompras.add(new MenuEditarCompras(), BorderLayout.CENTER);  // Add new Dashboard panel
                    panelCompras.revalidate();  // Revalidate to apply layout changes
                    panelCompras.repaint();  // Repaint to refresh the component
                }
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBManager.getComprasPorFiltro(Objects.requireNonNull(comboBoxFiltrar.getSelectedItem()).toString(), txtBuscar.getText());
                createTable(tableInfo);
            }
        });
    }


    /**
     * Establece el icono de un botón dado.
     *
     * @param button   El botón al que se le establecerá el icono.
     * @param iconPath La ruta del archivo del icono.
     */
    private void setButtonIcon(JButton button, String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image iconImage = icon.getImage();
        Image scaledIconImage = iconImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        button.setIcon(new ImageIcon(scaledIconImage));
    }

    /**
     * Crea una tabla de información de compras.
     *
     * @param tabla La tabla en la que se mostrará la información de las compras.
     */
    public static void createTable(JTable tabla) {
        try {
            if (CompraManager.compras == null) {
                CompraManager.compras = new ArrayList<>();
            }

            String[][] data = new String[CompraManager.compras.size()][9];
            cargarCompras(data);

            String[] columnNames = {"ID", "Fecha de Compra", "ID del Proveedor", "ID del Producto", "Cantidad", "Precio Unitario", "Fecha de Recepción", "ID del Empleado"};

            tabla.setModel(new DefaultTableModel(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            tabla.getTableHeader().setReorderingAllowed(false);
            tabla.setEnabled(true);
            tabla.setDefaultEditor(Object.class, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Carga los datos de las compras en un arreglo bidimensional.
     *
     * @param data El arreglo bidimensional en el que se cargarán los datos de las compras.
     * @return El arreglo con los datos de las compras.
     */
    public static String[][] cargarCompras(String[][] data) {
        try {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if (CompraManager.compras.get(i) != null) {
                        if (j == 0) {
                            data[i][j] = String.valueOf(CompraManager.compras.get(i).getId());
                        } else if (j == 1) {
                            data[i][j] = String.valueOf(CompraManager.compras.get(i).getFecha_compra());
                        } else if (j == 2) {
                            data[i][j] = String.valueOf(CompraManager.compras.get(i).getId_proveedor());
                        } else if (j == 3) {
                            data[i][j] = String.valueOf(CompraManager.compras.get(i).getId_producto());
                        } else if (j == 4) {
                            data[i][j] = String.valueOf(CompraManager.compras.get(i).getCantidad());
                        } else if (j == 5) {
                            data[i][j] = String.valueOf(CompraManager.compras.get(i).getPrecio_unitario());
                        } else if (j == 6) {
                            data[i][j] = String.valueOf(CompraManager.compras.get(i).getFecha_recepcion());
                        } else if (j == 7) {
                            data[i][j] = String.valueOf(CompraManager.compras.get(i).getId_empleado());
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las Compras", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }
}
