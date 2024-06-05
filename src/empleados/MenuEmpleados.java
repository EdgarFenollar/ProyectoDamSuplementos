package empleados;

import DBManager.DBManager;
import managers.ClienteManager;
import managers.EmpleadoManager;
import proveedores.MenuCrearProveedores;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Esta clase sirve para crear el panel utilizado para visualizar los Empleados, editar, borrar o crear nuevos Empleados.
 * @version: 0.1
 */

public class MenuEmpleados extends JPanel {
    private JPanel panelTablaEmpleados;
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
    private JPanel panelEmpleados;

    public MenuEmpleados() {
        setLayout(new BorderLayout());
        add(panelEmpleados, BorderLayout.CENTER);

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
                panelEmpleados.setLayout(new BorderLayout());
                panelEmpleados.removeAll();  // Remove any existing components
                panelEmpleados.add(new MenuCrearEmpleados(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelEmpleados.revalidate();  // Revalidate to apply layout changes
                panelEmpleados.repaint();  // Repaint to refresh the component
            }
        });


        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableInfo.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Debes de seleccionar un empleado para editar.");
                } else {
                    for (int i = 0; i < EmpleadoManager.empleados.size(); i++) {
                        if (i == tableInfo.getSelectedRow()){
                            MenuEditarEmpleados.insertarDatos(
                                    EmpleadoManager.empleados.get(i).getId(),
                                    EmpleadoManager.empleados.get(i).getDni(),
                                    EmpleadoManager.empleados.get(i).getNombre(),
                                    EmpleadoManager.empleados.get(i).getApellidos(),
                                    EmpleadoManager.empleados.get(i).getCorreo(),
                                    EmpleadoManager.empleados.get(i).getTelefono(),
                                    EmpleadoManager.empleados.get(i).getDireccion(),
                                    EmpleadoManager.empleados.get(i).getFechaNacimiento().toString(),
                                    EmpleadoManager.empleados.get(i).getUsuario(),
                                    EmpleadoManager.empleados.get(i).getAdministrador());
                        }
                    }

                    panelEmpleados.setLayout(new BorderLayout());
                    panelEmpleados.removeAll();  // Remove any existing components
                    panelEmpleados.add(new MenuEditarEmpleados(), BorderLayout.CENTER);  // Add new Dashboard panel
                    panelEmpleados.revalidate();  // Revalidate to apply layout changes
                    panelEmpleados.repaint();  // Repaint to refresh the component
                }
            }
        });

        btnBorrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableInfo.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Debes de seleccionar un empleado para editar.");
                } else {
                    int eliminar = JOptionPane.showConfirmDialog(null, "Seguro que quieres eliminar el Empleado?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (eliminar == 0){
                        DBManager.eliminarEmpleado(tableInfo.getSelectedRow());
                        EmpleadoManager.borrarColumnaPorId(tableInfo.getSelectedRow());
                    }
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
            if (EmpleadoManager.empleados == null) {
                EmpleadoManager.empleados = new ArrayList<>();
            }

            String[][] data = new String[EmpleadoManager.empleados.size()][10];
            cargarEmpleados(data);

            String[] columnNames = {"ID", "DNI", "Nombre", "Apellidos", "Correo", "Telefono", "Dirección", "Fecha Nacimiento", "Usuario", "Contraseña"};

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

    public static String[][] cargarEmpleados(String[][] data) {
        try {
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if (EmpleadoManager.empleados.get(i) != null) {
                        if (j == 0) {
                            data[i][j] = String.valueOf(EmpleadoManager.empleados.get(i).getId());
                        } else if (j == 1) {
                            data[i][j] = String.valueOf(EmpleadoManager.empleados.get(i).getDni());
                        } else if (j == 2) {
                            data[i][j] = String.valueOf(EmpleadoManager.empleados.get(i).getNombre());
                        } else if (j == 3) {
                            data[i][j] = String.valueOf(EmpleadoManager.empleados.get(i).getApellidos());
                        } else if (j == 4) {
                            data[i][j] = String.valueOf(EmpleadoManager.empleados.get(i).getCorreo());
                        } else if (j == 5) {
                            data[i][j] = String.valueOf(EmpleadoManager.empleados.get(i).getTelefono());
                        } else if (j == 6) {
                            data[i][j] = String.valueOf(EmpleadoManager.empleados.get(i).getDireccion());
                        } else if (j == 7) {
                            data[i][j] = String.valueOf(EmpleadoManager.empleados.get(i).getFechaNacimiento());
                        } else if (j == 8) {
                            data[i][j] = String.valueOf(EmpleadoManager.empleados.get(i).getUsuario());
                        } else if (j == 9) {
                            data[i][j] = String.valueOf(EmpleadoManager.empleados.get(i).getContrasenya());
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los empleados", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }
}
