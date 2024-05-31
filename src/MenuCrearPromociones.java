import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MenuCrearPromociones extends JPanel{
    private JPanel panelPromociones;
    private JPanel panelCrearProveedores;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JLabel imgPromocion;
    private JTextField txtCorreo;
    private JPanel panelFecha2;
    private JPanel panelFecha1;
    private JLabel imgProveedorGrande;
    private JDateChooser dateChooser1 = new JDateChooser();
    private JDateChooser dateChooser2 = new JDateChooser();

    public MenuCrearPromociones(){
        setLayout(new BorderLayout());
        add(panelPromociones, BorderLayout.CENTER);

        btnConfirmar.setBorder(null);
        btnConfirmar.setOpaque(false);
        btnCancelar.setBorder(null);
        btnCancelar.setOpaque(false);
        panelFecha1.setLayout(new FlowLayout());
        panelFecha1.add(dateChooser1);
        panelFecha2.setLayout(new FlowLayout());
        panelFecha2.add(dateChooser2);

        //Redimensionar Imagen CANCELAR//
        ImageIcon cancelPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/x.png")));
        Image cancelPrincImage = cancelPrinc.getImage();
        Image cancelPrincImageScaledInstance = cancelPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon cancel = new ImageIcon(cancelPrincImageScaledInstance);
        btnCancelar.setIcon(cancel);
        //////////////////////////////

        //Redimensionar Imagen CREAR//
        ImageIcon crearPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/save.png")));
        Image crearPrincImage = crearPrinc.getImage();
        Image crearPrincImageScaledInstance = crearPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon crear = new ImageIcon(crearPrincImageScaledInstance);
        btnConfirmar.setIcon(crear);
        //////////////////////////////

        //Redimensionar Imagen PROMOCION//
        ImageIcon promocionPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/promotions.png")));
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
                panelPromociones.add(new MenuProveedores(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelPromociones.revalidate();  // Revalidate to apply layout changes
                panelPromociones.repaint();  // Repaint to refresh the component
            }
        });
    }
}
