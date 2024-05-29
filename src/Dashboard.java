import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JLabel txtBienvenida;
    private JLabel imgVentasDash;
    private JLabel imgClientesDash;
    private JLabel imgEmpleadosDash;
    private JLabel txtTotalVentas;
    private JLabel txtTotalClientes;
    private JLabel txtTotalEmpleados;
    private JButton empleadosBtn;

    public Dashboard(){
        setContentPane(panelDashboard);
        btnLogout.setBorder(null);
        btnLogout.setBackground(null);
        btnHome.setBorder(null);
        btnHome.setBackground(null);
        String usr = System.getProperty("user.name");
        txtNombre.setText(usr);
        txtBienvenida.setText("BIENVENIDO " + usr);

        //Menu Botones
        dashboardButton.setBorder(null);
        dashboardButton.setOpaque(false);

        promocionesBtn.setBorder(null);
        promocionesBtn.setOpaque(false);

        clientesBtn.setBorder(null);
        clientesBtn.setOpaque(false);

        categoriasBtn.setBorder(null);
        categoriasBtn.setOpaque(false);

        ventasBtn.setBorder(null);
        ventasBtn.setOpaque(false);

        proveedoresBtn.setBorder(null);
        proveedoresBtn.setOpaque(false);

        pedidosBtn.setBorder(null);
        pedidosBtn.setOpaque(false);

        empleadosBtn.setBorder(null);
        empleadosBtn.setOpaque(false);

        //Redimensionar Imagen MINILOGO//
        ImageIcon miniLogoPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/miniLogo.png")));
        Image miniLogoImg = miniLogoPrinc.getImage();
        Image miniLogoScaled = miniLogoImg.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH);
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

        //Redimensionar Imagen DASHBOARD//
        ImageIcon dashboardPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/dashboard.png")));
        Image dashboardImage = dashboardPrinc.getImage();
        Image dashboardImageScaledInstance = dashboardImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon dashboard = new ImageIcon(dashboardImageScaledInstance);
        dashboardButton.setIcon(dashboard);
        //////////////////////////////

        //Redimensionar Imagen PROVEEDOR//
        ImageIcon proveedorPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/proveedor.png")));
        Image proveedorPrincImage = proveedorPrinc.getImage();
        Image proveedorPrincImageScaledInstance = proveedorPrincImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon proveedor = new ImageIcon(proveedorPrincImageScaledInstance);
        proveedoresBtn.setIcon(proveedor);
        //////////////////////////////

        //Redimensionar Imagen PROMOCIONES//
        ImageIcon promocionesPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/promotions.png")));
        Image promocionesPrincImage = promocionesPrinc.getImage();
        Image promocionesPrincImageScaledInstance = promocionesPrincImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon promociones = new ImageIcon(promocionesPrincImageScaledInstance);
        promocionesBtn.setIcon(promociones);
        //////////////////////////////

        //Redimensionar Imagen CLIENTES//
        ImageIcon clientesPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/clientes.png")));
        Image clientesPrincImage = clientesPrinc.getImage();
        Image clientesPrincImageScaledInstance = clientesPrincImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon clientes = new ImageIcon(clientesPrincImageScaledInstance);
        clientesBtn.setIcon(clientes);
        imgClientesDash.setIcon(clientes);
        //////////////////////////////

        //Redimensionar Imagen CATEGORIAS//
        ImageIcon categoriasPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/categoria.png")));
        Image categoriasPrincImage = categoriasPrinc.getImage();
        Image categoriasPrincImageScaledInstance = categoriasPrincImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon categorias = new ImageIcon(categoriasPrincImageScaledInstance);
        categoriasBtn.setIcon(categorias);
        //////////////////////////////

        //Redimensionar Imagen VENTAS//
        ImageIcon ventasPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/cart.png")));
        Image ventasPrincImage = ventasPrinc.getImage();
        Image ventasPrincImageScaledInstance = ventasPrincImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon ventas = new ImageIcon(ventasPrincImageScaledInstance);
        ventasBtn.setIcon(ventas);
        imgVentasDash.setIcon(ventas);
        //////////////////////////////

        //Redimensionar Imagen PEDIDOS//
        ImageIcon pedidosPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/pedidos.png")));
        Image pedidosPrincImage = pedidosPrinc.getImage();
        Image pedidosPrincImageScaledInstance = pedidosPrincImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon pedidos = new ImageIcon(pedidosPrincImageScaledInstance);
        pedidosBtn.setIcon(pedidos);
        //////////////////////////////

        //Redimensionar Imagen EMPLEADOS//
        ImageIcon empleadosPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/employe.png")));
        Image empleadosPrincImage = empleadosPrinc.getImage();
        Image empleadosPrincImageScaledInstance = empleadosPrincImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon empleados = new ImageIcon(empleadosPrincImageScaledInstance);
        empleadosBtn.setIcon(empleados);
        imgEmpleadosDash.setIcon(empleados);
        //////////////////////////////

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame menuLogin = new Login();
                menuLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("imagenes/miniLogo.png")));
                menuLogin.setVisible(true);
                menuLogin.setSize(800,450);
                menuLogin.setLocationRelativeTo(null);
                menuLogin.setResizable(false);
                menuLogin.setDefaultCloseOperation(EXIT_ON_CLOSE);
                dispose();
            }
        });
    }
}
