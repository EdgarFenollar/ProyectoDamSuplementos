package productos;

import com.toedter.calendar.JDateChooser;
import empleados.Empleado;
import empleados.MenuEmpleados;
import managers.EmpleadoManager;
import managers.ProductoManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Esta clase sirve para crear el panel utilizado para crear los productos.
 * @version: 0.1
 */


public class MenuCrearProductos extends JPanel{
    private JPanel panelProductos;
    private JPanel panelCrearProductos;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JLabel imgPromocion;
    private JTextField txtStock;
    private JPanel panelFecha2;
    private JPanel panelFecha1;
    private JTextField txtNombre;
    private JTextField txtProveedor;
    private JTextField txtCategoria;
    private JTextField txtDescripcion;
    private JTextField txtPrecioCompra;
    private JTextField txtPrecioVenta;
    private JTextField txtPeso;
    private JLabel imgProveedorGrande;
    private JDateChooser dateChooser1 = new JDateChooser();
    private JDateChooser dateChooser2 = new JDateChooser();

    public MenuCrearProductos(){
        setLayout(new BorderLayout());
        add(panelProductos, BorderLayout.CENTER);

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
        panelFecha2.setLayout(new FlowLayout());
        dateChooser2.setDateFormatString("yyyy-MM-dd");
        JTextField dateEditor2 = (JTextField) dateChooser2.getDateEditor().getUiComponent();
        dateEditor2.setEditable(false);
        panelFecha2.add(dateChooser2);
        dateChooser2.setPreferredSize(new Dimension(485, 40));
        ///////////

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

        //Redimensionar Imagen PROMOCION//
        ImageIcon promocionPrinc = new ImageIcon("imagenes/productos.png");
        Image promocionPrincImage = promocionPrinc.getImage();
        Image promocionPrincImageScaledInstance = promocionPrincImage.getScaledInstance(20, 20,  Image.SCALE_SMOOTH);
        ImageIcon promocion = new ImageIcon(promocionPrincImageScaledInstance);
        imgPromocion.setIcon(promocion);
        //////////////////////////////

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelProductos.setLayout(new BorderLayout());
                panelProductos.removeAll();  // Remove any existing components
                panelProductos.add(new MenuProductos(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelProductos.revalidate();  // Revalidate to apply layout changes
                panelProductos.repaint();  // Repaint to refresh the component
            }
        });

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LocalDate fechaEntrega = dateChooser1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate fechaCaducidad = dateChooser2.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if (!txtNombre.getText().isEmpty() && !txtStock.getText().isEmpty() && !txtPeso.getText().isEmpty() && !txtPrecioVenta.getText().isEmpty() && !txtPrecioCompra.getText().isEmpty() && !txtDescripcion.getText().isEmpty() && !txtCategoria.getText().isEmpty() && !txtProveedor.getText().isEmpty()){
                    ProductoManager.anyadirProducto(new Producto(txtNombre.getText(),
                            Integer.parseInt(txtStock.getText()),
                            Double.parseDouble(txtPeso.getText()),
                            Double.parseDouble(txtPrecioVenta.getText()),
                            Double.parseDouble(txtPrecioCompra.getText()),
                            fechaEntrega,
                            fechaCaducidad,
                            txtDescripcion.getText(),
                            Integer.parseInt(txtCategoria.getText()),
                            Integer.parseInt(txtProveedor.getText()))
                    );
                    ProductoManager.getProductos();
                    // Volver Atras
                    panelProductos.setLayout(new BorderLayout());
                    panelProductos.removeAll();  // Remove any existing components
                    panelProductos.add(new MenuProductos(), BorderLayout.CENTER);  // Add new Dashboard panel
                    panelProductos.revalidate();  // Revalidate to apply layout changes
                    panelProductos.repaint();  // Repaint to refresh the component
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
