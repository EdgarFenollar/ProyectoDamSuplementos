package principal;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatDraculaIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatLightOwlIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import managers.DataManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame{

    private JPanel panelLogin;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton LOGINButton;
    private JLabel imgLogo;
    private JLabel imgLineaAzul;

    public Login(){
        super("Login - PeekPerformance");
        setContentPane(panelLogin);

        //Redimensionar Imagen LOGO//
        ImageIcon logoPrinc = new ImageIcon("imagenes/peeklogo.png");
        Image logoImg = logoPrinc.getImage();
        Image logoScaled = logoImg.getScaledInstance(400, 400,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon crear = new ImageIcon(logoScaled);
        imgLogo.setIcon(crear);
        //////////////////////////////

        //Redimensionar Imagen LINEA//
        ImageIcon lineaPrinc = new ImageIcon("imagenes/lineaazul.png");
        Image lineaImg = lineaPrinc.getImage();
        Image lineaScaled = lineaImg.getScaledInstance(50, 350,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon linea = new ImageIcon(lineaScaled);
        imgLineaAzul.setIcon(linea);
        //////////////////////////////
        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame dashboard = new PantallaPrincipal();
                dashboard.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/miniLogo.png"));
                dashboard.setVisible(true);
                dashboard.setSize(1500,900);
                dashboard.setLocationRelativeTo(null);
                dashboard.setDefaultCloseOperation(EXIT_ON_CLOSE);
                dashboard.setResizable(false);
                dispose();
            }
        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public FlatArcIJTheme FlatLightLaf;

            @Override

            public void run() {
                FlatArcIJTheme.setup();
                try {
                    UIManager.setLookAndFeel(new FlatLightOwlIJTheme());
                } catch( Exception ex ) {
                    System.err.println( "Failed to initialize LaF" );
                }
                JFrame menuLogin = new Login();
                menuLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/miniLogo.png"));
                menuLogin.setVisible(true);
                menuLogin.setSize(800,450);
                menuLogin.setLocationRelativeTo(null);
                menuLogin.setResizable(false);
                menuLogin.setDefaultCloseOperation(EXIT_ON_CLOSE);
                DataManager.cargarDatos();

            }
        });
    }
}