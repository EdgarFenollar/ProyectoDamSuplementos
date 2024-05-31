package principal;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Dashboard extends JPanel{
    private JPanel panelPantallas;
    private JLabel imgVentasDash;
    private JLabel txtTotalVentas;
    private JLabel imgClientesDash;
    private JLabel txtTotalClientes;
    private JLabel imgEmpleadosDash;
    private JLabel txtTotalEmpleados;
    private JLabel txtBienvenida;

    public Dashboard(){
        String usr = System.getProperty("user.name");
        txtBienvenida.setText(usr);
        txtBienvenida.setText("BIENVENIDO " + usr);

        setLayout(new BorderLayout());
        add(panelPantallas, BorderLayout.CENTER);


        setButtonIcon(imgVentasDash, "imagenes/cart.png");
        setButtonIcon(imgClientesDash, "imagenes/clientes.png");
        setButtonIcon(imgEmpleadosDash, "imagenes/employe.png");
    }

    private void setButtonIcon(JLabel lbl, String iconPath) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(iconPath)));
        Image iconImage = icon.getImage();
        Image scaledIconImage = iconImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        lbl.setIcon(new ImageIcon(scaledIconImage));
    }
}
