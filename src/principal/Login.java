package principal;

import DBManager.DBManager;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import managers.DataManager;
import managers.EmpleadoManager;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
/**
 * La clase Login gestiona la interfaz gráfica para el inicio de sesión en la aplicación.
 * Permite a los usuarios ingresar sus credenciales y autentificarse.
 */

public class Login extends JFrame{

    private JPanel panelLogin;
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton LOGINButton;
    private JLabel imgLogo;
    private JLabel imgLineaAzul;
    public static String nombre;
    public static int id;
    public static String ENCRYPT_KEY = loadSecret();

    /**
     * Constructor que inicializa la ventana de inicio de sesión y sus componentes.
     */
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
                 if (login(txtUsuario.getText(), txtPassword.getPassword())) {
                     if (admin(txtUsuario.getText())) {
                         JFrame dashboard = new PantallaPrincipalAdmin();
                         dashboard.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/miniLogo.png"));
                         dashboard.setVisible(true);
                         dashboard.setSize(1500, 900);
                         dashboard.setLocationRelativeTo(null);
                         dashboard.setDefaultCloseOperation(EXIT_ON_CLOSE);
                         dashboard.setResizable(false);
                         dashboard.revalidate();
                         dashboard.repaint();
                         dispose();
                     } else {
                         JFrame dashboard = new PantallaPrincipalEmpleado();
                         dashboard.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/miniLogo.png"));
                         dashboard.setVisible(true);
                         dashboard.setSize(1500, 900);
                         dashboard.setLocationRelativeTo(null);
                         dashboard.setDefaultCloseOperation(EXIT_ON_CLOSE);
                         dashboard.setResizable(false);
                         dashboard.revalidate();
                         dashboard.repaint();
                         dispose();
                     }
                 }
            }
        });
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
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
    /**
     * Verifica las credenciales del usuario.
     *
     * @param usuario el nombre de usuario ingresado.
     * @param pass la contraseña ingresada.
     * @return true si las credenciales son correctas, false en caso contrario.
     */
    private static boolean login(String usuario, char[] pass){
        try{
            String password = String.valueOf(pass);
            // Recorre la lista de empleados
            for (int i = 0; i < EmpleadoManager.empleados.size(); i++) {
                // Si acierta el usuario de algun empleado devuelve true
                String decryptedText = decrypt(EmpleadoManager.empleados.get(i).getContrasenya());
                if (EmpleadoManager.empleados.get(i).getUsuario().equals(usuario) && decryptedText.equals(password)){
                    id = EmpleadoManager.empleados.get(i).getId();
                    admin(usuario);
                    return true;
                }
            }
        }catch (NullPointerException e){
            e.fillInStackTrace();
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Si no devuelve false
        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "ERROR", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    // Funcion para saber si un usuario logueado es admin.
    /**
     * Comprueba si el usuario tiene privilegios de administrador.
     *
     * @param usuario el nombre de usuario.
     * @return true si el usuario es administrador, false en caso contrario.
     */
    public static boolean admin(String usuario){
        nombre = usuario;
        for (int i = 0; i < EmpleadoManager.empleados.size(); i++) {
            if (EmpleadoManager.empleados.get(i).getUsuario().equals(usuario)){
                if (EmpleadoManager.empleados.get(i).getAdministrador() == 1){
                    return true;
                }
            }
        }
        return false;
    }

    public static String decrypt(String encrypted) throws Exception {
        try {
            // Decode the Base64 encoded key
            byte[] decodedKey = Base64.getDecoder().decode(ENCRYPT_KEY);

            // Check if the decoded key length is valid for AES (16, 24, or 32 bytes)
            if (decodedKey.length != 16 && decodedKey.length != 24 && decodedKey.length != 32) {
                throw new IllegalArgumentException("Invalid AES key length: " + decodedKey.length + " bytes");
            }

            // Decode the Base64 encoded string
            byte[] encryptedBytes = Base64.getDecoder().decode(encrypted.replace("\n", ""));

            // Create AES key
            Key aesKey = new SecretKeySpec(decodedKey, "AES");

            // Create and initialize cipher with padding
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);

            // Perform decryption
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            // Convert decrypted bytes to string using UTF-8 encoding
            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private static String loadSecret(){
        try (BufferedReader br = new BufferedReader(new FileReader("SECRET_KEY.txt"))){
            System.out.println("KEY cargada correctamente");
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar la secret KEY");
        }
        return null;
    }
}