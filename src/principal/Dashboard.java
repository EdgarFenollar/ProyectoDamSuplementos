package principal;

import DBManager.DBManager;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatLineBorder;

import javax.swing.*;
import java.awt.*;
import java.beans.Statement;
import java.sql.ResultSet;
import java.util.Objects;
/**
 * La clase Dashboard crea el panel principal del tablero de mandos, mostrando estadísticas como el total de ventas,
 * clientes y empleados. Configura iconos y bordes personalizados para los componentes del panel.
 */
public class Dashboard extends JPanel{
    private JPanel panelPantallas;
    private JLabel imgVentasDash;
    private JLabel txtTotalVentas;
    private JLabel imgClientesDash;
    private JLabel txtTotalClientes;
    private JLabel imgEmpleadosDash;
    private JLabel txtTotalEmpleados;
    private JLabel txtBienvenida;
    private JPanel panelStats;


    /**
     * Constructor que inicializa el tablero de mandos con estadísticas y configuraciones personalizadas.
     */
    public Dashboard(){
        String usr = System.getProperty("user.name");
        txtBienvenida.setText(usr);
        txtBienvenida.setText("BIENVENIDO " + usr);

        txtTotalVentas.setText(String.valueOf(getVentas()));
        txtTotalClientes.setText(String.valueOf(getClientes()));
        txtTotalEmpleados.setText(String.valueOf(getEmpleados()));

        panelStats.setBorder( new FlatLineBorder( new Insets( 16, 16, 16, 16 ), Color.blue, 1, 8 ) );
        panelStats.putClientProperty( FlatClientProperties.STYLE, "arc: 8" );
        setLayout(new BorderLayout());
        add(panelPantallas, BorderLayout.CENTER);


        setButtonIcon(imgVentasDash, "imagenes/cart.png");
        setButtonIcon(imgClientesDash, "imagenes/clientes.png");
        setButtonIcon(imgEmpleadosDash, "imagenes/employe.png");
    }

    /**
     * Configura el icono de un JLabel con una imagen escalada.
     *
     * @param lbl el JLabel al que se le establecerá el icono.
     * @param iconPath la ruta del archivo de imagen.
     */
    private void setButtonIcon(JLabel lbl, String iconPath) {
        ImageIcon icon = new ImageIcon(iconPath);
        Image iconImage = icon.getImage();
        Image scaledIconImage = iconImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        lbl.setIcon(new ImageIcon(scaledIconImage));
    }

    // RECIBIR CONTADORES PARA PANTALLA PRINCIPAL

    private int getVentas(){
        return DBManager.contarFilas("SELECT COUNT(*) FROM VENTAS");
    }

    private int getClientes(){
        return DBManager.contarFilas("SELECT COUNT(*) FROM CLIENTES");
    }

    private int getEmpleados(){
        return DBManager.contarFilas("SELECT COUNT(*) FROM EMPLEADOS");
    }
}
