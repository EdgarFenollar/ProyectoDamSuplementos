package clientes;

import managers.ClienteManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCrearClientes extends JPanel{
    private JPanel panelClientes;
    private JPanel panelCrearClientes;
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
    private JTextField txtCodPostal;
    private JTextField txtCorreo;
    private JLabel imgProveedorGrande;
    public static String tipo;

    /**
     * Esta clase sirve para crear el panel utilizado para crear nuevos clientes.
     * @version 0.1
     */

    public MenuCrearClientes() {
        setLayout(new BorderLayout());
        add(panelClientes, BorderLayout.CENTER);
        radioButtonMinorista.setSelected(true);

        btnConfirmar.setBorder(null);
        btnConfirmar.setBackground(null);
        btnConfirmar.setOpaque(false);
        btnCancelar.setBorder(null);
        btnCancelar.setBackground(null);
        btnCancelar.setOpaque(false);

        txtNombre.setSize(new Dimension(100, 100));

        //Redimensionar Imagen CANCELAR//
        ImageIcon cancelPrinc = new ImageIcon("imagenes/x.png");
        Image cancelPrincImage = cancelPrinc.getImage();
        Image cancelPrincImageScaledInstance = cancelPrincImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon cancel = new ImageIcon(cancelPrincImageScaledInstance);
        btnCancelar.setIcon(cancel);
        //////////////////////////////

        //Redimensionar Imagen CREAR//
        ImageIcon crearPrinc = new ImageIcon("imagenes/save.png");
        Image crearPrincImage = crearPrinc.getImage();
        Image crearPrincImageScaledInstance = crearPrincImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon crear = new ImageIcon(crearPrincImageScaledInstance);
        btnConfirmar.setIcon(crear);
        //////////////////////////////

        //Redimensionar Imagen CLIENTES//
        ImageIcon proveedorPrinc = new ImageIcon("imagenes/clientes.png");
        Image proveedorPrincImage = proveedorPrinc.getImage();
        Image proveedorPrincImageScaledInstance = proveedorPrincImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon proveedor = new ImageIcon(proveedorPrincImageScaledInstance);
        imgProveedor.setIcon(proveedor);
        //////////////////////////////

        // Acción para el botón Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelClientes.setLayout(new BorderLayout());
                panelClientes.removeAll();
                panelClientes.add(new MenuClientes(), BorderLayout.CENTER);
                panelClientes.revalidate();
                panelClientes.repaint();
            }
        });

        // Acción para el botón Confirmar
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtDni.getText().isEmpty() && !txtNombre.getText().isEmpty() && !txtApellidos.getText().isEmpty() && !txtTelefono.getText().isEmpty() &&
                            !txtDireccion.getText().isEmpty() && !txtApellidos.getText().isEmpty()) {

                        if (radioButtonMayorista.isSelected()) {
                            ClienteManager.anyadirCliente(new Cliente(
                                    txtDni.getText(),
                                    txtNombre.getText(),
                                    txtApellidos.getText(),
                                    txtApellidos.getText(),
                                    txtTelefono.getText(),
                                    txtCodPostal.getText(),
                                    txtDireccion.getText(),
                                    EnumTipoCliente.MAYORISTA)
                            );
                        } else if (radioButtonMinorista.isSelected()) {
                            ClienteManager.anyadirCliente(new Cliente(
                                    txtDni.getText(),
                                    txtNombre.getText(),
                                    txtApellidos.getText(),
                                    txtApellidos.getText(),
                                    txtTelefono.getText(),
                                    txtCodPostal.getText(),
                                    txtDireccion.getText(),
                                    EnumTipoCliente.MINORISTA)
                            );
                        }


                        ClienteManager.getClientes();


                        // Volver Atras
                        panelClientes.setLayout(new BorderLayout());
                        panelClientes.removeAll();
                        panelClientes.add(new MenuClientes(), BorderLayout.CENTER);
                        panelClientes.revalidate();
                        panelClientes.repaint();
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
}
