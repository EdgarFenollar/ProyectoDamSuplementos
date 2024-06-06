package promociones;

import DBManager.DBManager;
import com.toedter.calendar.JDateChooser;
import empleados.MenuEmpleados;
import managers.EmpleadoManager;
import managers.PromocionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

/**
 * Esta clase sirve para crear el panel utilizado para editar las promociones.
 * @version: 0.1
 */

public class MenuEditarPromociones extends JPanel{
    private JPanel panelPromociones;
    private JPanel panelCrearPromociones;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JLabel imgPromocion;
    private JTextField txtDescuento;
    private JPanel panelFecha2;
    private JPanel panelFecha1;
    private JTextField txtDescripcion;
    public static String descripcion, fechaIni, fechaFin;
    public static int id;
    public static double descuento;
    private JLabel imgProveedorGrande;
    private JDateChooser dateChooser1 = new JDateChooser();
    private JDateChooser dateChooser2 = new JDateChooser();

    public MenuEditarPromociones(){
        setLayout(new BorderLayout());
        add(panelPromociones, BorderLayout.CENTER);

        btnConfirmar.setBorder(null);
        btnConfirmar.setBackground(null);
        btnConfirmar.setOpaque(false);
        btnCancelar.setBorder(null);
        btnCancelar.setBackground(null);
        btnCancelar.setOpaque(false);

        txtDescripcion.setText(descripcion);
        txtDescuento.setText(String.valueOf(descuento));

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
        ImageIcon promocionPrinc = new ImageIcon("imagenes/promotions.png");
        Image promocionPrincImage = promocionPrinc.getImage();
        Image promocionPrincImageScaledInstance = promocionPrincImage.getScaledInstance(20, 20,  Image.SCALE_SMOOTH);
        ImageIcon promocion = new ImageIcon(promocionPrincImageScaledInstance);
        imgPromocion.setIcon(promocion);
        //////////////////////////////

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPromociones.setLayout(new BorderLayout());
                panelPromociones.removeAll();  // Remove any existing components
                panelPromociones.add(new MenuPromociones(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelPromociones.revalidate();  // Revalidate to apply layout changes
                panelPromociones.repaint();  // Repaint to refresh the component
            }
        });

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!txtDescripcion.getText().isEmpty() && !txtDescuento.getText().isEmpty()){
                        for (int i = 0; i < PromocionManager.promociones.size(); i++) {
                            if (id == PromocionManager.promociones.get(i).getId()){
                                LocalDate fechaIn = dateChooser1.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                LocalDate fechaFin = dateChooser2.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                                DBManager.editarPromociones(
                                        id,
                                        txtDescripcion.getText(),
                                        Double.parseDouble(txtDescuento.getText()),
                                        fechaIn,
                                        fechaFin
                                );
                                PromocionManager.getPromociones();
                            }
                        }
                        // Volver Atras
                        panelPromociones.setLayout(new BorderLayout());
                        panelPromociones.removeAll();  // Remove any existing components
                        panelPromociones.add(new MenuPromociones(), BorderLayout.CENTER);  // Add new Dashboard panel
                        panelPromociones.revalidate();  // Revalidate to apply layout changes
                        panelPromociones.repaint();  // Repaint to refresh the component
                    } else {
                        JOptionPane.showMessageDialog(null, "Debes de introducir todos los datos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
    }

    public static void insertarDatos(int idI, String descripcionI, double descuentoI, String fechaIniI, String fechaFinI){
        id = idI;
        descripcion = descripcionI;
        descuento = descuentoI;
        fechaIni = fechaIniI;
        fechaFin = fechaFinI;
    }
}
