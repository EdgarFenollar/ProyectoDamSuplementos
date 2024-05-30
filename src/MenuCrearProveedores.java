import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MenuCrearProveedores extends JDialog{
    private JPanel panelProveedores;
    private JButton btnCancelar;
    private JLabel imgMiniLogo;
    private JButton btnLogout;
    private JLabel imgUser;
    private JPanel panelCrearProveedores;
    private JButton btnCrear;
    private JLabel txtNombre;
    private JPanel panelOpciones;

    public MenuCrearProveedores(){
        setContentPane(panelProveedores);
        btnCrear.setBorder(null);
        btnCrear.setOpaque(false);
        btnCancelar.setBorder(null);
        btnCancelar.setOpaque(false);


        //Redimensionar Imagen MINILOGO//
        ImageIcon miniLogoPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/miniLogo.png")));
        Image miniLogoImg = miniLogoPrinc.getImage();
        Image miniLogoScaled = miniLogoImg.getScaledInstance(70, 70,  Image.SCALE_SMOOTH);
        ImageIcon miniLogo = new ImageIcon(miniLogoScaled);
        imgMiniLogo.setIcon(miniLogo);
        //////////////////////////////

        //Redimensionar Imagen USUARIO//
        ImageIcon userPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/usuario.png")));
        Image userImg = userPrinc.getImage();
        Image userScaled = userImg.getScaledInstance(50, 50,  Image.SCALE_SMOOTH);
        ImageIcon user = new ImageIcon(userScaled);
        imgUser.setIcon(user);
        //////////////////////////////

        //Redimensionar Imagen CANCELAR//
        ImageIcon cancelPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/x.png")));
        Image cancelPrincImage = cancelPrinc.getImage();
        Image cancelPrincImageScaledInstance = cancelPrincImage.getScaledInstance(50, 50,  Image.SCALE_SMOOTH);
        ImageIcon cancel = new ImageIcon(cancelPrincImageScaledInstance);
        btnCancelar.setIcon(cancel);
        //////////////////////////////

        //Redimensionar Imagen CREAR//
        ImageIcon crearPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/save.png")));
        Image crearPrincImage = crearPrinc.getImage();
        Image crearPrincImageScaledInstance = crearPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon crear = new ImageIcon(crearPrincImageScaledInstance);
        btnCrear.setIcon(crear);
        //////////////////////////////
    }
}
