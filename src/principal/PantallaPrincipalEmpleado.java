package principal;

import categorias.MenuCategorias;
import clientes.MenuClientes;
import com.formdev.flatlaf.FlatClientProperties;
import compras.MenuCompras;
import empleados.MenuEmpleados;
import managers.EmpleadoManager;
import productos.MenuProductos;
import promociones.MenuPromociones;
import proveedores.MenuProveedores;
import ventas.MenuVentas;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;

public class PantallaPrincipalEmpleado extends JFrame{
    private JPanel panelDashboard;
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
    private JPanel panelPantallas;
    private JPanel panelAzul;
    private JPanel panelGeneral;
    private JButton productosBtn;
    private static boolean admin = true;

    public PantallaPrincipalEmpleado(){
        super("Menu Principal - PeekPerformance");
        setContentPane(panelDashboard);
        btnLogout.setBorder(null);
        btnLogout.setBackground(null);
        txtNombre.setText(Login.nombre);

        //Redimensionar Imagen MINILOGO//
        ImageIcon miniLogoPrinc = new ImageIcon("imagenes/miniLogo.png");
        Image miniLogoImg = miniLogoPrinc.getImage();
        Image miniLogoScaled = miniLogoImg.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon miniLogo = new ImageIcon(miniLogoScaled);
        imgMiniLogo.setIcon(miniLogo);
        //////////////////////////////

        //Redimensionar Imagen USUARIO//
        ImageIcon userPrinc = new ImageIcon("imagenes/usuario.png");
        Image userImg = userPrinc.getImage();
        Image userScaled = userImg.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon user = new ImageIcon(userScaled);
        imgUser.setIcon(user);
        //////////////////////////////

        //Redimensionar Imagen LOGOUT//
        ImageIcon logoutPrinc = new ImageIcon("imagenes/logout.png");
        Image logoutImg = logoutPrinc.getImage();
        Image logoutScaled = logoutImg.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon logout = new ImageIcon(logoutScaled);
        btnLogout.setIcon(logout);
        //////////////////////////////

        //Redimensionar Imagen DASHBOARD//
        ImageIcon dashboardPrinc = new ImageIcon("imagenes/dashboard.png");
        Image dashboardImage = dashboardPrinc.getImage();
        Image dashboardImageScaledInstance = dashboardImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon dashboard = new ImageIcon(dashboardImageScaledInstance);
        dashboardButton.setIcon(dashboard);
        //////////////////////////////


        //Redimensionar Imagen PROMOCIONES//
        ImageIcon promocionesPrinc = new ImageIcon("imagenes/promotions.png");
        Image promocionesPrincImage = promocionesPrinc.getImage();
        Image promocionesPrincImageScaledInstance = promocionesPrincImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon promociones = new ImageIcon(promocionesPrincImageScaledInstance);
        promocionesBtn.setIcon(promociones);
        //////////////////////////////

        //Redimensionar Imagen CLIENTES//
        ImageIcon clientesPrinc = new ImageIcon("imagenes/clientes.png");
        Image clientesPrincImage = clientesPrinc.getImage();
        Image clientesPrincImageScaledInstance = clientesPrincImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon clientes = new ImageIcon(clientesPrincImageScaledInstance);
        clientesBtn.setIcon(clientes);
        //////////////////////////////

        //Redimensionar Imagen CATEGORIAS//
        ImageIcon categoriasPrinc = new ImageIcon("imagenes/categoria.png");
        Image categoriasPrincImage = categoriasPrinc.getImage();
        Image categoriasPrincImageScaledInstance = categoriasPrincImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon categorias = new ImageIcon(categoriasPrincImageScaledInstance);
        categoriasBtn.setIcon(categorias);
        //////////////////////////////

        //Redimensionar Imagen VENTAS//
        ImageIcon ventasPrinc = new ImageIcon("imagenes/cart.png");
        Image ventasPrincImage = ventasPrinc.getImage();
        Image ventasPrincImageScaledInstance = ventasPrincImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon ventas = new ImageIcon(ventasPrincImageScaledInstance);
        ventasBtn.setIcon(ventas);
        //////////////////////////////

        //Redimensionar Imagen PEDIDOS//
        ImageIcon pedidosPrinc = new ImageIcon("imagenes/pedidos.png");
        Image pedidosPrincImage = pedidosPrinc.getImage();
        Image pedidosPrincImageScaledInstance = pedidosPrincImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon pedidos = new ImageIcon(pedidosPrincImageScaledInstance);
        pedidosBtn.setIcon(pedidos);
        //////////////////////////////

        //Redimensionar Imagen PRODUCTOS//
        ImageIcon productosPrinc = new ImageIcon("imagenes/productos.png");
        Image productosPrincImage = productosPrinc.getImage();
        Image productosPrincImageScaledInstance = productosPrincImage.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH);
        ImageIcon productos = new ImageIcon(productosPrincImageScaledInstance);
        productosBtn.setIcon(productos);
        //////////////////////////////

        panelPantallas.setLayout(new BorderLayout());
        panelPantallas.removeAll();  // Remove any existing components
        panelPantallas.add(new Dashboard(), BorderLayout.CENTER);  // Add new Dashboard panel
        panelPantallas.revalidate();  // Revalidate to apply layout changes
        panelPantallas.repaint();  // Repaint to refresh the component

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame menuLogin = new Login();
                menuLogin.setIconImage(Toolkit.getDefaultToolkit().getImage("imagenes/miniLogo.png"));
                menuLogin.setVisible(true);
                menuLogin.setSize(800,450);
                menuLogin.setLocationRelativeTo(null);
                menuLogin.setResizable(false);
                menuLogin.setDefaultCloseOperation(EXIT_ON_CLOSE);
                dispose();
            }
        });
        dashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPantallas.setLayout(new BorderLayout());
                panelPantallas.removeAll();  // Remove any existing components
                panelPantallas.add(new Dashboard(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelPantallas.revalidate();  // Revalidate to apply layout changes
                panelPantallas.repaint();  // Repaint to refresh the component
            }
        });
        promocionesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPantallas.setLayout(new BorderLayout());
                panelPantallas.removeAll();  // Remove any existing components
                panelPantallas.add(new MenuPromociones(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelPantallas.revalidate();  // Revalidate to apply layout changes
                panelPantallas.repaint();  // Repaint to refresh the component
            }
        });

        clientesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPantallas.setLayout(new BorderLayout());
                panelPantallas.removeAll();  // Remove any existing components
                panelPantallas.add(new MenuClientes(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelPantallas.revalidate();  // Revalidate to apply layout changes
                panelPantallas.repaint();  // Repaint to refresh the component
            }
        });

        ventasBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPantallas.setLayout(new BorderLayout());
                panelPantallas.removeAll();  // Remove any existing components
                panelPantallas.add(new MenuVentas(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelPantallas.revalidate();  // Revalidate to apply layout changes
                panelPantallas.repaint();  // Repaint to refresh the component
            }
        });
        categoriasBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPantallas.setLayout(new BorderLayout());
                panelPantallas.removeAll();  // Remove any existing components
                panelPantallas.add(new MenuCategorias(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelPantallas.revalidate();  // Revalidate to apply layout changes
                panelPantallas.repaint();  // Repaint to refresh the component
            }
        });
        productosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPantallas.setLayout(new BorderLayout());
                panelPantallas.removeAll();  // Remove any existing components
                panelPantallas.add(new MenuProductos(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelPantallas.revalidate();  // Revalidate to apply layout changes
                panelPantallas.repaint();  // Repaint to refresh the component
            }
        });
        pedidosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelPantallas.setLayout(new BorderLayout());
                panelPantallas.removeAll();  // Remove any existing components
                panelPantallas.add(new MenuCompras(), BorderLayout.CENTER);  // Add new Dashboard panel
                panelPantallas.revalidate();  // Revalidate to apply layout changes
                panelPantallas.repaint();  // Repaint to refresh the component
            }
        });
    }
}
