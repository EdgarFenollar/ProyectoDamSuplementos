package ventas;

import DBManager.DBManager;
import com.toedter.calendar.JDateChooser;
import managers.ProductoManager;
import managers.VentaManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;

public class MenuEditarVentas extends JPanel{
    private JPanel panelventas;
    private JPanel panelEditarVentas;
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
    public static int id,cantidad, idCliente, idEmpleado, idProducto, idPromocion;
    public static String fechaVenta;
    public static Double precio;
    private JDateChooser dateChooser1 = new JDateChooser();

    public MenuEditarVentas(){
        setLayout(new BorderLayout());
        add(panelventas, BorderLayout.CENTER);
        txtCantidad.setText(String.valueOf(cantidad));
        txtCliente.setText(String.valueOf(idCliente));
        txtEmpleado.setText(String.valueOf(idEmpleado));
        txtEmpleado.setEnabled(false);
        txtProducto.setText(String.valueOf(idProducto));
        txtIdPromo.setText(String.valueOf(idPromocion));
        txtPrecio.setText(String.valueOf(precio));

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

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LocalDate fechaVenta = dateChooser1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if (!txtCliente.getText().isEmpty() && !txtCantidad.getText().isEmpty() && !txtPrecio.getText().isEmpty() && !txtEmpleado.getText().isEmpty() && !txtIdPromo.getText().isEmpty() && !txtProducto.getText().isEmpty()){
                        for (int i = 0; i < VentaManager.ventas.size(); i++) {
                            if (id == VentaManager.ventas.get(i).getId()){
                                DBManager.editarVentas(
                                        id,
                                        fechaVenta,
                                        Integer.parseInt(txtCantidad.getText()),
                                        Double.parseDouble(txtPrecio.getText()),
                                        Integer.parseInt(txtCliente.getText()),
                                        Integer.parseInt(txtEmpleado.getText()),
                                        Integer.parseInt(txtProducto.getText()),
                                        Integer.parseInt(txtIdPromo.getText())
                                );
                                VentaManager.getVentas();
                            }
                        }
                        panelventas.setLayout(new BorderLayout());
                        panelventas.removeAll();  // Remove any existing components
                        panelventas.add(new MenuVentas(), BorderLayout.CENTER);  // Add new Dashboard panel
                        panelventas.revalidate();  // Revalidate to apply layout changes
                        panelventas.repaint();  // Repaint to refresh the component
                }else{
                        JOptionPane.showMessageDialog(null, "Debes de introducir todos los datos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Debe de introducir todos los datos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void insertarDatos(int idI, String fechaVentaI, int cantidadI, Double precioI, int idClienteI, int idEmpleadoI, int idProductoI, int idPromocionI){
       id = idI;
       fechaVenta = fechaVentaI;
       cantidad = cantidadI;
       precio = precioI;
       idCliente = idClienteI;
       idEmpleado = idEmpleadoI;
       idProducto = idProductoI;
       idPromocion = idPromocionI;
    }
}
