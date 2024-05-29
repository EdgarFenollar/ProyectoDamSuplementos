import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Dashboard extends JFrame{
    private JPanel panelDashboard;
    private JButton btnHome;
    private JLabel imgMiniLogo;
    private JButton btnLogout;
    private JLabel imgUser;
    private JLabel txtNombre;
    private JButton dashboardButton;
    private JButton promocionesBtn;
    private JButton clientesBtn;
    private JButton categoriasBtn;
    private JButton ventasBtn;
    private JPanel panelOpciones;
    private JButton pedidosBtn;
    private JButton proveedoresBtn;

    public Dashboard(){
        setContentPane(panelDashboard);
        btnLogout.setBorder(null);
        btnLogout.setBackground(null);
        btnHome.setBorder(null);
        btnHome.setBackground(null);
        String usr = System.getProperty("user.name");
        txtNombre.setText(usr);

        //Menu Botones
        dashboardButton.setBackground(null);
        dashboardButton.setBorder(null);
        promocionesBtn.setBackground(null);
        promocionesBtn.setBorder(null);
        clientesBtn.setBackground(null);
        clientesBtn.setBorder(null);
        categoriasBtn.setBackground(null);
        categoriasBtn.setBorder(null);
        ventasBtn.setBackground(null);
        ventasBtn.setBorder(null);
        proveedoresBtn.setBackground(null);
        proveedoresBtn.setBorder(null);
        pedidosBtn.setBackground(null);
        pedidosBtn.setBorder(null);


        //Redimensionar Imagen MINILOGO//
        ImageIcon miniLogoPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/miniLogo.png")));
        Image miniLogoImg = miniLogoPrinc.getImage();
        Image miniLogoScaled = miniLogoImg.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon miniLogo = new ImageIcon(miniLogoScaled);
        imgMiniLogo.setIcon(miniLogo);
        //////////////////////////////

        //Redimensionar Imagen USUARIO//
        ImageIcon userPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/usuario.png")));
        Image userImg = userPrinc.getImage();
        Image userScaled = userImg.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon user = new ImageIcon(userScaled);
        imgUser.setIcon(user);
        //////////////////////////////

        //Redimensionar Imagen HOME//
        ImageIcon homePrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/home.png")));
        Image homeImg = homePrinc.getImage();
        Image homeScaled = homeImg.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon home = new ImageIcon(homeScaled);
        btnHome.setIcon(home);
        //////////////////////////////

        //Redimensionar Imagen LOGOUT//
        ImageIcon logoutPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/logout.png")));
        Image logoutImg = logoutPrinc.getImage();
        Image logoutScaled = logoutImg.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon logout = new ImageIcon(logoutScaled);
        btnLogout.setIcon(logout);
        //////////////////////////////
    }
}
