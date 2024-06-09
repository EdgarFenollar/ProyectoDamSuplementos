package compras;

import DBManager.DBManager;
import com.toedter.calendar.JDateChooser;
import empleados.MenuEmpleados;
import managers.CompraManager;
import managers.ProductoManager;
import productos.MenuProductos;
import productos.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Clase que representa el panel para crear nuevas compras en la aplicación.
 * Proporciona campos para ingresar los detalles de la compra y botones para confirmar o cancelar la operación.
 *
 * @version 0.1
 */
public class MenuCrearCompras extends JPanel {
    private JPanel panelCrearCompras;
    private JLabel imgPromocion;
    private JButton btnCancelar;
    private JButton btnConfirmar;
    private JTextField txtIDproveedor;
    private JTextField txtIDproducto;
    private JTextField txtCantidad;
    private JTextField txtPrecioUnitario;
    private JTextField txtIDempleado;
    private JPanel panelCompras;
    private JPanel panelPicture;
    private JPanel panelFechaCompra;
    private JPanel panelFechaRecepcion;
    public static String nombre, descripcion, fechaEntrega, fechaCaducidad;
    public static Double peso, precioVenta, precioCompra;
    public static int idCategoria, idProveedor, id, stock;
    private JDateChooser fCompra = new JDateChooser();
    private JDateChooser fRecepcion = new JDateChooser();

    /**
     * Constructor de la clase MenuCrearCompras.
     * Inicializa el panel de creación de compras y sus componentes.
     */
    public MenuCrearCompras() {
        setLayout(new BorderLayout());
        add(panelCompras, BorderLayout.CENTER);

        btnConfirmar.setBorder(null);
        btnConfirmar.setBackground(null);
        btnConfirmar.setOpaque(false);
        btnCancelar.setBorder(null);
        btnCancelar.setBackground(null);
        btnCancelar.setOpaque(false);

        // FECHAS//
        panelFechaCompra.setLayout(new FlowLayout());
        panelFechaRecepcion.setLayout(new FlowLayout());
        fCompra.setDateFormatString("yyyy-MM-dd");
        JTextField dateEditor1 = (JTextField) fCompra.getDateEditor().getUiComponent();
        JTextField dateEditor2 = (JTextField) fRecepcion.getDateEditor().getUiComponent();
        dateEditor1.setEditable(false);
        dateEditor2.setEditable(false);
        panelFechaCompra.add(fCompra);
        panelFechaRecepcion.add(fRecepcion);
        fCompra.setPreferredSize(new Dimension(485, 40));
        fRecepcion.setPreferredSize(new Dimension(485, 40));

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
        /**
         * Acción realizada al hacer clic en el botón "Cancelar".
         * Vuelve al panel principal de compras.
         *
         * @param e El evento de acción.
         */
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCompras.setLayout(new BorderLayout());
                panelCompras.removeAll();
                panelCompras.add(new MenuCompras(), BorderLayout.CENTER);
                panelCompras.revalidate();
                panelCompras.repaint();
            }
        });
        /**
         * Acción realizada al hacer clic en el botón "Confirmar".
         * Valida los datos ingresados y crea una nueva compra si los datos son correctos.
         *
         * @param e El evento de acción.
         */
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    LocalDate fechaCompr = fCompra.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate fechaRecep = fRecepcion.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if (!txtIDproveedor.getText().isEmpty() && !txtIDproducto.getText().isEmpty() && !txtCantidad.getText().isEmpty() && !txtPrecioUnitario.getText().isEmpty() && !txtIDempleado.getText().isEmpty()){
                            CompraManager.anyadirCompra(new Compra(
                                    fechaCompr,
                                    Integer.parseInt(txtIDproveedor.getText()),
                                    Integer.parseInt(txtIDproducto.getText()),
                                    Integer.parseInt(txtCantidad.getText()),
                                    Double.parseDouble(txtPrecioUnitario.getText()),
                                    fechaRecep,
                                    Integer.parseInt(txtIDempleado.getText()))
                            );
                            CompraManager.getCompras();
                        // Volver Atras
                        panelCompras.setLayout(new BorderLayout());
                        panelCompras.removeAll();
                        panelCompras.add(new MenuCompras(), BorderLayout.CENTER);
                        panelCompras.revalidate();
                        panelCompras.repaint();
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
