package compras;

import DBManager.DBManager;
import com.toedter.calendar.JDateChooser;
import managers.CompraManager;
import managers.EmpleadoManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
/**
 * Clase que representa el panel para editar compras existentes en la aplicación.
 * Proporciona campos para modificar los detalles de la compra seleccionada y botones para confirmar o cancelar la operación.
 *
 * @version 0.1
 */
public class MenuEditarCompras extends JPanel {
    private JPanel panelCrearCompras;
    private JLabel imgPromocion;
    private JButton btnCancelar;
    private JButton btnConfirmar;
    private JTextField txtIDproveedor;
    private JTextField txtIDproducto;
    private JTextField txtCantidad;
    private JTextField txtPrecioUnitario;
    private JTextField txtIDempleado;
    private JPanel panelPicture;
    private JPanel panelCompras;
    private JPanel panelFechaCompra;
    private JPanel panelFechaRecep;
    public static String fechaCompra, fechaRecepcion;
    public static int id, idProveedor, idProducto, idEmpleado, cantidad;
    public static double precioUnitario;
    private JDateChooser fCompra = new JDateChooser();
    private JDateChooser fRecepcion = new JDateChooser();


    /**
     * Constructor de la clase MenuEditarCompras.
     * Inicializa el panel de edición de compras y sus componentes.
     */
    public MenuEditarCompras() {
        setLayout(new BorderLayout());

        txtIDproveedor.setText(String.valueOf(idProveedor));
        txtIDproducto.setText(String.valueOf(idProducto));
        txtCantidad.setText(String.valueOf(cantidad));
        txtPrecioUnitario.setText(String.valueOf(precioUnitario));
        txtIDempleado.setText(String.valueOf(idEmpleado));
        try {
            fCompra.setDateFormatString(fechaCompra);
            fCompra.setDateFormatString(fechaRecepcion);
        } catch (Exception e){
            e.printStackTrace();
        }

        add(panelCompras, BorderLayout.CENTER);


        btnConfirmar.setBorder(null);
        btnConfirmar.setBackground(null);
        btnConfirmar.setOpaque(false);
        btnCancelar.setBorder(null);
        btnCancelar.setBackground(null);
        btnCancelar.setOpaque(false);

        // FECHAS//
        panelFechaCompra.setLayout(new FlowLayout());
        panelFechaRecep.setLayout(new FlowLayout());
        fCompra.setDateFormatString("yyyy-MM-dd");
        JTextField dateEditor1 = (JTextField) fCompra.getDateEditor().getUiComponent();
        JTextField dateEditor2 = (JTextField) fRecepcion.getDateEditor().getUiComponent();
        dateEditor1.setEditable(false);
        dateEditor2.setEditable(false);
        panelFechaCompra.add(fCompra);
        panelFechaRecep.add(fRecepcion);
        fCompra.setPreferredSize(new Dimension(485, 40));
        fRecepcion.setPreferredSize(new Dimension(485, 40));

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
         * Valida los datos ingresados y actualiza la compra seleccionada si los datos son correctos.
         *
         * @param e El evento de acción.
         */
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtIDproveedor.getText().isEmpty() && !txtIDproducto.getText().isEmpty() && !txtCantidad.getText().isEmpty() && !txtPrecioUnitario.getText().isEmpty() && !txtIDempleado.getText().isEmpty()){
                        for (int i = 0; i < CompraManager.compras.size(); i++) {
                            if (id == CompraManager.compras.get(i).getId()){
                                LocalDate feCompra = fCompra.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                LocalDate feRecepcion = fCompra.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                                DBManager.editarCompras(
                                        id,
                                        feCompra,
                                        Integer.parseInt(txtIDproveedor.getText()),
                                        Integer.parseInt(txtIDproducto.getText()),
                                        Integer.parseInt(txtCantidad.getText()),
                                        Double.parseDouble(txtPrecioUnitario.getText()),
                                        feRecepcion,
                                        Integer.parseInt(txtIDempleado.getText())
                                );
                                CompraManager.getCompras();
                            }
                        }
                        // Volver Atras
                        panelCompras.setLayout(new BorderLayout());
                        panelCompras.removeAll();  // Remove any existing components
                        panelCompras.add(new MenuCompras(), BorderLayout.CENTER);  // Add new Dashboard panel
                        panelCompras.revalidate();  // Revalidate to apply layout changes
                        panelCompras.repaint();  // Repaint to refresh the component
                    } else {
                        JOptionPane.showMessageDialog(null, "Debes de introducir todos los datos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    /**
     * Inserta los datos de la compra seleccionada en los campos correspondientes para su edición.
     *
     * @param idI El ID de la compra.
     * @param fechaCompraI La fecha de la compra.
     * @param idProveedorI El ID del proveedor.
     * @param idProductoI El ID del producto.
     * @param cantidadI La cantidad comprada.
     * @param precioUnitarioI El precio unitario de la compra.
     * @param fechaRecepcionI La fecha de recepción de la compra.
     * @param idEmpleadoI El ID del empleado que realizó la compra.
     */
    public static void insertarDatos(int idI, LocalDate fechaCompraI, int idProveedorI, int idProductoI, int cantidadI,
                                     double precioUnitarioI, LocalDate fechaRecepcionI, int idEmpleadoI){
        id = idI;
        fechaCompra = String.valueOf(fechaCompraI);
        idProveedor = idProveedorI;
        idProducto = idProductoI;
        cantidad = cantidadI;
        precioUnitario = precioUnitarioI;
        fechaRecepcion = String.valueOf(fechaRecepcionI);
        idEmpleado = idEmpleadoI;
    }
}
