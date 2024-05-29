import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Login extends JFrame{

    private JPanel panelLogin;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton LOGINButton;
    private JTabbedPane tabbedPane1;
    private JLabel imgLogo;
    private JLabel imgLineaAzul;

    public Login(){
        setContentPane(panelLogin);

        //Redimensionar Imagen LOGO//
        ImageIcon logoPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/peeklogo.png")));
        Image logoImg = logoPrinc.getImage();
        Image logoScaled = logoImg.getScaledInstance(400, 400,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon crear = new ImageIcon(logoScaled);
        imgLogo.setIcon(crear);
        //////////////////////////////

        //Redimensionar Imagen LINEA//
        ImageIcon lineaPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/lineaazul.png")));
        Image lineaImg = lineaPrinc.getImage();
        Image lineaScaled = lineaImg.getScaledInstance(50, 350,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon linea = new ImageIcon(lineaScaled);
        imgLineaAzul.setIcon(linea);
        //////////////////////////////
        LOGINButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame dashboard = new Dashboard();
                dashboard.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/miniLogo.png")));
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
            @Override

            public void run() {
                JFrame menuLogin = new Login();
                menuLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/miniLogo.png")));
                menuLogin.setVisible(true);
                menuLogin.setSize(800,450);
                menuLogin.setLocationRelativeTo(null);
                menuLogin.setResizable(false);
                menuLogin.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
    }
}