package principal;

import DBManager.DBManager;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import managers.DataManager;
import managers.EmpleadoManager;

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
                EmpleadoManager.anyadirEmpleado();
                if (login(txtUsuario.getText(), txtPassword.getPassword())) {
                        JFrame dashboard = new PantallaPrincipal();
                        dashboard.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/miniLogo.png"));
                        dashboard.setVisible(true);
                        dashboard.setSize(1500, 900);
                        dashboard.setLocationRelativeTo(null);
                        dashboard.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        dashboard.setResizable(false);
                        dispose();
                }
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
                    UIManager.setLookAndFeel(new FlatMacLightLaf());
                } catch( Exception ex ) {
                    System.err.println( "Error al cargar el tema" );
                }
                if (DBManager.loadDriver()){
                    if (DBManager.connect()){
                        JFrame menuLogin = new Login();
                        menuLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/miniLogo.png"));
                        menuLogin.setVisible(true);
                        menuLogin.setSize(800,450);
                        menuLogin.setLocationRelativeTo(null);
                        menuLogin.setResizable(false);
                        menuLogin.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        DataManager.cargarDatos();
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error al cargar el driver", "ERROR", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
    }

    //Funcion de login
    private static boolean login(String usuario, char[] pass){
        try{
            String password = String.valueOf(pass);
            // Recorre la lista de empleados
            for (int i = 0; i < EmpleadoManager.empleados.size(); i++) {
                // Si acierta el usuario de algun empleado devuelve true
                if (EmpleadoManager.empleados.get(i).getUsuario().equals(usuario) && EmpleadoManager.empleados.get(i).getContrasenya().equals(password.toString())){
                    PantallaPrincipal.admin(usuario);
                    return true;
                }
            }
        }catch (NullPointerException e){
            e.fillInStackTrace();
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Si no devuelve false
        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
        return false;
    }
}