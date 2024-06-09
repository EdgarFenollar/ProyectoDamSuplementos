package ventas;

import DBManager.DBManager;
import empleados.MenuEditarEmpleados;
import managers.ClienteManager;
import managers.EmpleadoManager;
import managers.VentaManager;
import proveedores.MenuCrearProveedores;
import proveedores.MenuEditarProveedores;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class MenuVentas extends JPanel {
    private JPanel panelVentas;
    private JButton btnBuscar;
    private JButton btnEditar;
    private JButton btnCrear;
    private JTable tableInfo;
    private JPanel panelTablaVentas;
    private JComboBox<String> comboBoxFiltrar;
    private JTextField txtBuscar;
    private JScrollPane scrollTable;
    private JLabel lblBuscar;

    public MenuVentas() {
        VentaManager.getVentas();
        setLayout(new BorderLayout());
        add(panelVentas, BorderLayout.CENTER);


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
                panelVentas.setLayout(new BorderLayout());
                panelVentas.removeAll();  // Remove any existing components
                panelVentas.add(new MenuCrearVentas(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelVentas.revalidate();  // Revalidate to apply layout changes
                panelVentas.repaint();  // Repaint to refresh the component
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableInfo.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Debes de seleccionar un empleado para editar.");
                } else {
                    for (int i = 0; i < VentaManager.ventas.size(); i++) {
                        if (i == tableInfo.getSelectedRow()){
                            MenuEditarVentas.insertarDatos(
                                    VentaManager.ventas.get(i).getId(),
                                    String.valueOf(VentaManager.ventas.get(i).getFechaVenta()),
                                    VentaManager.ventas.get(i).getCantidad(),
                                    VentaManager.ventas.get(i).getPrecioUnitario(),
                                    VentaManager.ventas.get(i).getIdCliente(),
                                    VentaManager.ventas.get(i).getIdEmpleado(),
                                    VentaManager.ventas.get(i).getIdProducto(),
                                    VentaManager.ventas.get(i).getIdPromocion());
                        }
                    }

                    panelVentas.setLayout(new BorderLayout());
                    panelVentas.removeAll();  // Remove any existing components
                    panelVentas.add(new MenuEditarVentas(), BorderLayout.CENTER);  // Add new Dashboard panel
                    panelVentas.revalidate();  // Revalidate to apply layout changes
                    panelVentas.repaint();  // Repaint to refresh the component
                }
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBManager.getVentasPorFiltro(Objects.requireNonNull(comboBoxFiltrar.getSelectedItem()).toString(), txtBuscar.getText());
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
            if (VentaManager.ventas == null) {
                VentaManager.ventas = new ArrayList<>();
            }

            String[][] data = new String[VentaManager.ventas.size()][8];
            cargarVentas(data);

            String[] cabe = {"ID", "Fecha Venta", "Cantidad", "Precio Unitario", "ID Cliente", "ID Empleado", "ID Producto", "ID Promocion"};

            tabla.setModel(new DefaultTableModel(data, cabe));
            tabla.getTableHeader().setReorderingAllowed(false);
            tabla.setEnabled(true);
            tabla.setDefaultEditor(Object.class, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String[][] cargarVentas(String[][] data) {
        try {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if (VentaManager.ventas.get(i) != null) {
                        if (j == 0) {
                            data[i][j] = String.valueOf(VentaManager.ventas.get(i).getId());
                        } else if (j == 1) {
                            data[i][j] = String.valueOf(VentaManager.ventas.get(i).getFechaVenta());
                        } else if (j == 2) {
                            data[i][j] = String.valueOf(VentaManager.ventas.get(i).getCantidad());
                        } else if (j == 3) {
                            data[i][j] = String.valueOf(VentaManager.ventas.get(i).getPrecioUnitario());
                        } else if (j == 4) {
                            data[i][j] = String.valueOf(VentaManager.ventas.get(i).getIdCliente());
                        } else if (j == 5) {
                            data[i][j] = String.valueOf(VentaManager.ventas.get(i).getIdEmpleado());
                        } else if (j == 6) {
                            data[i][j] = String.valueOf(VentaManager.ventas.get(i).getIdProducto());
                        } else if (j == 7) {
                            data[i][j] = String.valueOf(VentaManager.ventas.get(i).getIdPromocion());
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las ventas", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }
}
