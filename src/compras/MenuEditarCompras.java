package compras;

import empleados.MenuEmpleados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEditarCompras extends JPanel {
    private JPanel panelCrearCompras;
    private JLabel imgPromocion;
    private JButton btnCancelar;
    private JButton btnConfirmar;
    private JTextField txtFechaCompra;
    private JTextField txtIDproveedor;
    private JTextField txtIDproducto;
    private JTextField txtCantidad;
    private JTextField txtPrecioUnitario;
    private JTextField txtIDempleado;
    private JTextField txtFechaRecepcion;
    private JPanel panelPicture;
    private JPanel panelCompras;

    public MenuEditarCompras() {setLayout(new BorderLayout());
        add(panelCompras, BorderLayout.CENTER);

        btnConfirmar.setBorder(null);
        btnConfirmar.setBackground(null);
        btnConfirmar.setOpaque(false);
        btnCancelar.setBorder(null);
        btnCancelar.setBackground(null);
        btnCancelar.setOpaque(false);

        txtIDempleado.setSize(new Dimension(100, 100));

        //Redimensionar Imagen CANCELAR//
        ImageIcon cancelPrinc = new ImageIcon("imagenes/x.png");
        Image cancelPrincImage = cancelPrinc.getImage();
        Image cancelPrincImageScaledInstance = cancelPrincImage.getScaledInstance(20, 20,  Image.SCALE_SMOOTH);
        ImageIcon cancel = new ImageIcon(cancelPrincImageScaledInstance);
        btnCancelar.setIcon(cancel);
        //////////////////////////////

        //Redimensionar Imagen CREAR//
        ImageIcon crearPrinc = new ImageIcon("imagenes/save.png");
        Image crearPrincImage = crearPrinc.getImage();
        Image crearPrincImageScaledInstance = crearPrincImage.getScaledInstance(20, 20,  Image.SCALE_SMOOTH);
        ImageIcon crear = new ImageIcon(crearPrincImageScaledInstance);
        btnConfirmar.setIcon(crear);
        //////////////////////////////

        //Redimensionar Imagen COMPRAS//
        ImageIcon comprasPrinc = new ImageIcon("imagenes/pedidos.png");
        Image comprasPrincImage = comprasPrinc.getImage();
        Image comprasPrincImageScaledInstance = comprasPrincImage.getScaledInstance(20, 20,  Image.SCALE_SMOOTH);
        ImageIcon compras = new ImageIcon(comprasPrincImageScaledInstance);
        imgPromocion.setIcon(compras);
        //////////////////////////////

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCompras.setLayout(new BorderLayout());
                panelCompras.removeAll();  // Remove any existing components
                panelCompras.add(new MenuCompras(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelCompras.revalidate();  // Revalidate to apply layout changes
                panelCompras.repaint();  // Repaint to refresh the component
            }
        });
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
