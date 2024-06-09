package promociones;

import DBManager.DBManager;
import empleados.MenuEditarEmpleados;
import managers.EmpleadoManager;
import managers.PromocionManager;

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
    private JPanel panelPromociones;
    private JButton btnBuscar;
    private JButton btnEditar;
    private JButton btnCrear;
    private JTable tableInfo;
    private JPanel panelTablaPromociones;
    private JComboBox<String> comboBoxFiltrar;
    private JTextField txtBuscar;
    private JScrollPane scrollTable;
    private JLabel lblBuscar;

    public MenuPromociones() {
        PromocionManager.getPromociones();
        setLayout(new BorderLayout());
        add(panelPromociones, BorderLayout.CENTER);

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
                panelPromociones.setLayout(new BorderLayout());
                panelPromociones.removeAll();  // Remove any existing components
                panelPromociones.add(new MenuCrearPromociones(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelPromociones.revalidate();  // Revalidate to apply layout changes
                panelPromociones.repaint();  // Repaint to refresh the component
            }
        });
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableInfo.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Debes de seleccionar una promocion para editar.");
                } else {
                    for (int i = 0; i < PromocionManager.promociones.size(); i++) {
                        if (i == tableInfo.getSelectedRow()){
                            MenuEditarPromociones.insertarDatos(
                                    PromocionManager.promociones.get(i).getId(),
                                    PromocionManager.promociones.get(i).getDescripcion(),
                                    PromocionManager.promociones.get(i).getDescuento(),
                                    PromocionManager.promociones.get(i).getFechaInicio().toString(),
                                    PromocionManager.promociones.get(i).getFechaFin().toString());
                        }
                    }

                    panelPromociones.setLayout(new BorderLayout());
                    panelPromociones.removeAll();  // Remove any existing components
                    panelPromociones.add(new MenuEditarPromociones(), BorderLayout.CENTER);  // Add new Dashboard panel
                    panelPromociones.revalidate();  // Revalidate to apply layout changes
                    panelPromociones.repaint();  // Repaint to refresh the component
                }
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBManager.getPromocionesPorFiltro(Objects.requireNonNull(comboBoxFiltrar.getSelectedItem()).toString(), txtBuscar.getText());
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
            if (PromocionManager.promociones == null) {
                PromocionManager.promociones = new ArrayList<>();
            }

            String[][] data = new String[PromocionManager.promociones.size()][5];
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

    /**
     * Método para cargar las promociones en un arreglo bidimensional.
     *
     * @param data el arreglo bidimensional donde se cargarán los datos de las promociones.
     * @return el arreglo con los datos cargados.
     */
    public static String[][] cargarPromociones(String[][] data) {
        try {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if (PromocionManager.promociones.get(i) != null) {
                        if (j == 0) {
                            data[i][j] = String.valueOf(PromocionManager.promociones.get(i).getId());
                        } else if (j == 1) {
                            data[i][j] = String.valueOf(PromocionManager.promociones.get(i).getDescripcion());
                        } else if (j == 2) {
                            data[i][j] = String.valueOf(PromocionManager.promociones.get(i).getDescuento());
                        } else if (j == 3) {
                            data[i][j] = String.valueOf(PromocionManager.promociones.get(i).getFechaInicio());
                        } else if (j == 4) {
                            data[i][j] = String.valueOf(PromocionManager.promociones.get(i).getFechaFin());
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
