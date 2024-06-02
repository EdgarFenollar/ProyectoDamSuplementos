package compras;

import empleados.MenuCrearEmpleados;
import managers.CompraManager;
import managers.EmpleadoManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuCompras extends JPanel {
    private JPanel panelTablaCompras;
    private JButton btnEditar;
    private JButton btnBorrar;
    private JButton btnCrear;
    private JComboBox comboBoxFiltrar;
    private JButton btnFiltrar;
    private JLabel lblBuscar;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JScrollPane scrollTable;
    private JTable tableInfo;
    private JPanel panelCompras;

    public MenuCompras() {
        setLayout(new BorderLayout());
        add(panelCompras, BorderLayout.CENTER);

        createTable(tableInfo);

        // Establecer bordes y apariencia de los botones
        btnBorrar.setBackground(null);
        btnEditar.setBackground(null);
        btnCrear.setBackground(null);
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
                panelCompras.setLayout(new BorderLayout());
                panelCompras.removeAll();  // Remove any existing components
                panelCompras.add(new MenuEditarCompras(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelCompras.revalidate();  // Revalidate to apply layout changes
                panelCompras.repaint();  // Repaint to refresh the component
            }
        });
        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int eliminar = JOptionPane.showConfirmDialog(null, "Seguro que quieres eliminar el Empleado?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (eliminar == 0){

                }
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
            if (CompraManager.compras == null) {
                CompraManager.compras = new ArrayList<>();
            }

            String[][] data = new String[CompraManager.compras.size()][5];
            cargarCompras(data);

            String[] columnNames = {"ID", "Fecha de Compra", "ID del Proveedor", "ID del Producto", "Cantidad", "Precio Unitario", "Fecha de Recepción", "Fecha de Recepción", "ID del Empleado"};

            tabla.setModel(new DefaultTableModel(data, columnNames));
            tabla.getTableHeader().setReorderingAllowed(false);
            tabla.setEnabled(true);
            tabla.setDefaultEditor(Object.class, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
