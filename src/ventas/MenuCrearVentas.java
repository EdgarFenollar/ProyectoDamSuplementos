package ventas;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuCrearVentas extends JPanel{
    private JPanel panelventas;
    private JPanel panelCrearVentas;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JLabel imgProveedor;
    private JTextField txtCliente;
    private JTextField txtCantidad;
    private JTextField txtPrecio;
    private JTextField txtEmpleado;
    private JPanel panelFecha1;
    private JTextField txtIdPromo;
    private JTextField txtProducto;
    private JLabel imgProveedorGrande;
    private JDateChooser dateChooser1 = new JDateChooser();

    public MenuCrearVentas(){
        setLayout(new BorderLayout());
        add(panelventas, BorderLayout.CENTER);

        btnConfirmar.setBorder(null);
        btnConfirmar.setBackground(null);
        btnConfirmar.setOpaque(false);
        btnCancelar.setBorder(null);
        btnCancelar.setBackground(null);
        btnCancelar.setOpaque(false);

        // FECHAS//
        panelFecha1.setLayout(new FlowLayout());
        dateChooser1.setDateFormatString("yyyy-MM-dd");
        JTextField dateEditor1 = (JTextField) dateChooser1.getDateEditor().getUiComponent();
        dateEditor1.setEditable(false);
        panelFecha1.add(dateChooser1);
        dateChooser1.setPreferredSize(new Dimension(485, 40));

        txtCliente.setSize(new Dimension(100, 100));

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

        //Redimensionar Imagen VENTA//
        ImageIcon proveedorPrinc = new ImageIcon("imagenes/cart.png");
        Image proveedorPrincImage = proveedorPrinc.getImage();
        Image proveedorPrincImageScaledInstance = proveedorPrincImage.getScaledInstance(20, 20,  Image.SCALE_SMOOTH);
        ImageIcon proveedor = new ImageIcon(proveedorPrincImageScaledInstance);
        imgProveedor.setIcon(proveedor);
        //////////////////////////////

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelventas.setLayout(new BorderLayout());
                panelventas.removeAll();  // Remove any existing components
                panelventas.add(new MenuVentas(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelventas.revalidate();  // Revalidate to apply layout changes
                panelventas.repaint();  // Repaint to refresh the component
            }
        });
    }
}
