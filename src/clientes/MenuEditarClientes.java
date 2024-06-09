package clientes;

import DBManager.DBManager;
import managers.ClienteManager;
import productos.MenuProductos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEditarClientes extends JPanel{
    private JPanel panelClientes;
    private JPanel panelEditarClientes;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JLabel imgProveedor;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtDni;
    private JTextField txtTelefono;
    private JRadioButton radioButtonMinorista;
    private JRadioButton radioButtonMayorista;
    private JTextField txtDireccion;
    private JTextField txtCorreo;
    private JTextField txtCodPostal;
    private JLabel imgProveedorGrande;

    public static int id;

    public static String dni, nombre, apellidos, correo, telefono, codPostal, direccion, tipo;

    public MenuEditarClientes(){
        setLayout(new BorderLayout());
        add(panelClientes, BorderLayout.CENTER);

        txtNombre.setText(nombre);
        txtApellidos.setText(apellidos);
        txtDni.setText(dni);
        txtTelefono.setText(telefono);
        txtDireccion.setText(direccion);
        txtCodPostal.setText(codPostal);
        txtCorreo.setText(correo);

        //Seleccionar depende el tipo de cliente que sea

        if (tipo.equals("Minorista")) {
            radioButtonMinorista.setSelected(true);
            radioButtonMayorista.setSelected(false);
        } else if (tipo.equals("Mayorista")) {
            radioButtonMinorista.setSelected(false);
            radioButtonMayorista.setSelected(true);
        }


        btnConfirmar.setBorder(null);
        btnConfirmar.setBackground(null);
        btnConfirmar.setOpaque(false);
        btnCancelar.setBorder(null);
        btnCancelar.setBackground(null);
        btnCancelar.setOpaque(false);

        //Redimensionar Imagen CANCELAR//
        ImageIcon cancelPrinc = new ImageIcon("imagenes/x.png");
        Image cancelPrincImage = cancelPrinc.getImage();
        Image cancelPrincImageScaledInstance = cancelPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon cancel = new ImageIcon(cancelPrincImageScaledInstance);
        btnCancelar.setIcon(cancel);
        //////////////////////////////

        //Redimensionar Imagen CREAR//
        ImageIcon crearPrinc = new ImageIcon("imagenes/save.png");
        Image crearPrincImage = crearPrinc.getImage();
        Image crearPrincImageScaledInstance = crearPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon crear = new ImageIcon(crearPrincImageScaledInstance);
        btnConfirmar.setIcon(crear);
        //////////////////////////////

        //Redimensionar Imagen CLIENTE//
        ImageIcon proveedorPrinc = new ImageIcon("imagenes/clientes.png");
        Image proveedorPrincImage = proveedorPrinc.getImage();
        Image proveedorPrincImageScaledInstance = proveedorPrincImage.getScaledInstance(20, 20,  Image.SCALE_SMOOTH);
        ImageIcon proveedor = new ImageIcon(proveedorPrincImageScaledInstance);
        imgProveedor.setIcon(proveedor);
        //////////////////////////////

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelClientes.setLayout(new BorderLayout());
                panelClientes.removeAll();  // Remove any existing components
                panelClientes.add(new MenuClientes(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelClientes.revalidate();  // Revalidate to apply layout changes
                panelClientes.repaint();  // Repaint to refresh the component
            }
        });
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtDni.getText().isEmpty() && !txtNombre.getText().isEmpty() && !txtApellidos.getText().isEmpty() && !txtTelefono.getText().isEmpty() &&
                            !txtDireccion.getText().isEmpty() && !txtCorreo.getText().isEmpty()){
                        for (int i = 0; i < ClienteManager.clientes.size(); i++) {
                            if (id == ClienteManager.clientes.get(i).getId()){
                                DBManager.editarClientes(
                                        id,
                                        txtDni.getText(),
                                        txtNombre.getText(),
                                        txtApellidos.getText(),
                                        txtCorreo.getText(),
                                        txtTelefono.getText(),
                                        txtCodPostal.getText(),
                                        txtDireccion.getText(),
                                        tipo

                                );
                                ClienteManager.getClientes();
                            }
                        }
                        // Volver Atras
                        panelClientes.setLayout(new BorderLayout());
                        panelClientes.removeAll();  // Remove any existing components
                        panelClientes.add(new MenuClientes(), BorderLayout.CENTER);  // Add new Dashboard panel
                        panelClientes.revalidate();  // Revalidate to apply layout changes
                        panelClientes.repaint();  // Repaint to refresh the component
                    } else {
                        JOptionPane.showMessageDialog(null, "Debes de introducir todos los datos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Debes de introducir todos los datos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    public static void insertarDatos(int idI, String dniI, String nombreI, String apellidosI, String correoI, String telefonoI, String codPostalI, String direccionI, String tipoI){
        id = idI;
        dni = dniI;
        nombre = nombreI;
        apellidos = apellidosI;
        correo = correoI;
        telefono = telefonoI;
        codPostal = codPostalI;
        direccion = direccionI;
        tipo = tipoI;
    }

}
