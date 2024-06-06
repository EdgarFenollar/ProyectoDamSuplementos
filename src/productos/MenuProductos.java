package productos;

import DBManager.DBManager;
import empleados.MenuEditarEmpleados;
import managers.EmpleadoManager;
import managers.ProductoManager;
import managers.PromocionManager;
import promociones.MenuCrearPromociones;
import promociones.MenuEditarPromociones;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Esta clase sirve para crear el panel utilizado el cual servira para visualizar los productos, y poder editar, borrar o crear otros productos.
 * @version: 0.1
 */

public class MenuProductos extends JPanel {
    private JPanel panelProductos;
    private JButton btnBuscar;
    private JButton btnEditar;
    private JButton btnCrear;
    private JTable tableInfo;
    private JPanel panelTablaProductos;
    private JComboBox<String> comboBoxFiltrar;
    private JTextField txtBuscar;
    private JScrollPane scrollTable;
    private JLabel lblBuscar;

    public MenuProductos() {
        setLayout(new BorderLayout());
        add(panelProductos, BorderLayout.CENTER);

        ProductoManager.getProductos();

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
                panelProductos.setLayout(new BorderLayout());
                panelProductos.removeAll();  // Remove any existing components
                panelProductos.add(new MenuCrearProductos(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelProductos.revalidate();  // Revalidate to apply layout changes
                panelProductos.repaint();  // Repaint to refresh the component
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableInfo.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Debes de seleccionar un producto para editar.");
                } else {
                    for (int i = 0; i < ProductoManager.productos.size(); i++) {
                        if (i == tableInfo.getSelectedRow()){
                            MenuEditarProductos.insertarDatos(
                                    ProductoManager.productos.get(i).getId(),
                                    ProductoManager.productos.get(i).getNombre(),
                                    ProductoManager.productos.get(i).getStock(),
                                    ProductoManager.productos.get(i).getPeso(),
                                    ProductoManager.productos.get(i).getPrecioVenta(),
                                    ProductoManager.productos.get(i).getPrecioCompra(),
                                    ProductoManager.productos.get(i).getFechaEntrega().toString(),
                                    ProductoManager.productos.get(i).getFechaCaducidad().toString(),
                                    ProductoManager.productos.get(i).getDescripcion(),
                                    ProductoManager.productos.get(i).getIdCategoria(),
                                    ProductoManager.productos.get(i).getIdProveedor());
                        }
                    }

                    panelProductos.setLayout(new BorderLayout());
                    panelProductos.removeAll();  // Remove any existing components
                    panelProductos.add(new MenuEditarProductos(), BorderLayout.CENTER);  // Add new Dashboard panel
                    panelProductos.revalidate();  // Revalidate to apply layout changes
                    panelProductos.repaint();  // Repaint to refresh the component
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBManager.getProductosPorFiltro(Objects.requireNonNull(comboBoxFiltrar.getSelectedItem()).toString(), txtBuscar.getText());
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
            if (ProductoManager.productos == null) {
                ProductoManager.productos = new ArrayList<>();
            }

            String[][] data = new String[ProductoManager.productos.size()][11];
            cargarProductos(data);

            String[] cabe = {"ID", "Nombre", "Stock", "Peso", "Precio Venta", "Precio Compra", "Fecha Entrega", "Fecha Caducidad", "Descripcion", "ID Categoria", "ID Proveedor"};

            tabla.setModel(new DefaultTableModel(data, cabe) {
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

    public static String[][] cargarProductos(String[][] data) {
        try {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if (ProductoManager.productos.get(i) != null) {
                        if (j == 0) {
                            data[i][j] = String.valueOf(ProductoManager.productos.get(i).getId());
                        } else if (j == 1) {
                            data[i][j] = String.valueOf(ProductoManager.productos.get(i).getNombre());
                        } else if (j == 2) {
                            data[i][j] = String.valueOf(ProductoManager.productos.get(i).getStock());
                        } else if (j == 3) {
                            data[i][j] = String.valueOf(ProductoManager.productos.get(i).getPeso());
                        } else if (j == 4) {
                            data[i][j] = String.valueOf(ProductoManager.productos.get(i).getPrecioVenta());
                        } else if (j == 5) {
                            data[i][j] = String.valueOf(ProductoManager.productos.get(i).getPrecioCompra());
                        } else if (j == 6) {
                            data[i][j] = String.valueOf(ProductoManager.productos.get(i).getFechaEntrega());
                        } else if (j == 7) {
                            data[i][j] = String.valueOf(ProductoManager.productos.get(i).getFechaCaducidad());
                        } else if (j == 8) {
                            data[i][j] = String.valueOf(ProductoManager.productos.get(i).getDescripcion());
                        } else if (j == 9) {
                            data[i][j] = String.valueOf(ProductoManager.productos.get(i).getIdCategoria());
                        } else if (j == 10) {
                            data[i][j] = String.valueOf(ProductoManager.productos.get(i).getIdProveedor());
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los productos", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }
}
