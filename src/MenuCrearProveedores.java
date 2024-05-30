import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class MenuCrearProveedores extends JDialog{
    private JPanel panelProveedores;
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
    private JButton empleadosBtn;
    private JPanel panelTablaProveedores;
    private JComboBox comboBoxFiltrar;
    private JTextField txtBuscar;
    private JButton btnBuscar;
    private JButton btnBorrar;
    private JButton btnEditar;
    private JButton btnCrear;
    private JButton btnFiltrar;
    private JTable tableInfo;
    private JScrollPane scrollTable;

    public MenuCrearProveedores(){
        setContentPane(panelProveedores);
        createTable(tableInfo);
        btnLogout.setBorder(null);
        btnLogout.setBackground(null);
        btnHome.setBorder(null);
        btnHome.setBackground(null);

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

        btnBorrar.setBorder(null);
        btnBorrar.setOpaque(false);

        btnCrear.setBorder(null);
        btnCrear.setOpaque(false);

        btnEditar.setBorder(null);
        btnEditar.setOpaque(false);

        btnBuscar.setBorder(null);
        btnBuscar.setOpaque(false);

        btnFiltrar.setBorder(null);
        btnFiltrar.setOpaque(false);

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

        //Redimensionar Imagen HOME//
        ImageIcon homePrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/home.png")));
        Image homeImg = homePrinc.getImage();
        Image homeScaled = homeImg.getScaledInstance(50, 50,  Image.SCALE_SMOOTH);
        ImageIcon home = new ImageIcon(homeScaled);
        btnHome.setIcon(home);
        //////////////////////////////

        //Redimensionar Imagen LOGOUT//
        ImageIcon logoutPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/logout.png")));
        Image logoutImg = logoutPrinc.getImage();
        Image logoutScaled = logoutImg.getScaledInstance(50, 50,  Image.SCALE_SMOOTH);
        ImageIcon logout = new ImageIcon(logoutScaled);
        btnLogout.setIcon(logout);
        //////////////////////////////

        //Redimensionar Imagen DASHBOARD//
        ImageIcon dashboardPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/dashboard.png")));
        Image dashboardImage = dashboardPrinc.getImage();
        Image dashboardImageScaledInstance = dashboardImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon dashboard = new ImageIcon(dashboardImageScaledInstance);
        dashboardButton.setIcon(dashboard);
        //////////////////////////////

        //Redimensionar Imagen PROVEEDOR//
        ImageIcon proveedorPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/proveedor.png")));
        Image proveedorPrincImage = proveedorPrinc.getImage();
        Image proveedorPrincImageScaledInstance = proveedorPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon proveedor = new ImageIcon(proveedorPrincImageScaledInstance);
        proveedoresBtn.setIcon(proveedor);
        //////////////////////////////

        //Redimensionar Imagen PROMOCIONES//
        ImageIcon promocionesPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/promotions.png")));
        Image promocionesPrincImage = promocionesPrinc.getImage();
        Image promocionesPrincImageScaledInstance = promocionesPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon promociones = new ImageIcon(promocionesPrincImageScaledInstance);
        promocionesBtn.setIcon(promociones);
        //////////////////////////////

        //Redimensionar Imagen CLIENTES//
        ImageIcon clientesPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/clientes.png")));
        Image clientesPrincImage = clientesPrinc.getImage();
        Image clientesPrincImageScaledInstance = clientesPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon clientes = new ImageIcon(clientesPrincImageScaledInstance);
        clientesBtn.setIcon(clientes);
        //////////////////////////////

        //Redimensionar Imagen CATEGORIAS//
        ImageIcon categoriasPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/categoria.png")));
        Image categoriasPrincImage = categoriasPrinc.getImage();
        Image categoriasPrincImageScaledInstance = categoriasPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon categorias = new ImageIcon(categoriasPrincImageScaledInstance);
        categoriasBtn.setIcon(categorias);
        //////////////////////////////

        //Redimensionar Imagen VENTAS//
        ImageIcon ventasPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/cart.png")));
        Image ventasPrincImage = ventasPrinc.getImage();
        Image ventasPrincImageScaledInstance = ventasPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon ventas = new ImageIcon(ventasPrincImageScaledInstance);
        ventasBtn.setIcon(ventas);
        //////////////////////////////

        //Redimensionar Imagen PEDIDOS//
        ImageIcon pedidosPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/pedidos.png")));
        Image pedidosPrincImage = pedidosPrinc.getImage();
        Image pedidosPrincImageScaledInstance = pedidosPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon pedidos = new ImageIcon(pedidosPrincImageScaledInstance);
        pedidosBtn.setIcon(pedidos);
        //////////////////////////////

        //Redimensionar Imagen EMPLEADOS//
        ImageIcon empleadosPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/employe.png")));
        Image empleadosPrincImage = empleadosPrinc.getImage();
        Image empleadosPrincImageScaledInstance = empleadosPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon empleados = new ImageIcon(empleadosPrincImageScaledInstance);
        empleadosBtn.setIcon(empleados);
        //////////////////////////////

        //Redimensionar Imagen CREAR//
        ImageIcon crearPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/create.png")));
        Image crearPrincImage = crearPrinc.getImage();
        Image crearPrincImageScaledInstance = crearPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon crear = new ImageIcon(crearPrincImageScaledInstance);
        btnCrear.setIcon(crear);
        //////////////////////////////

        //Redimensionar Imagen EDITAR//
        ImageIcon editarPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/edit.png")));
        Image editarPrincImage = editarPrinc.getImage();
        Image editarPrincImageScaledInstance = editarPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon editar = new ImageIcon(editarPrincImageScaledInstance);
        btnEditar.setIcon(editar);
        //////////////////////////////

        //Redimensionar Imagen BORRAR//
        ImageIcon borrarPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/delete.png")));
        Image borrarPrincImage = borrarPrinc.getImage();
        Image borrarPrincImageScaledInstance = borrarPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon borrar = new ImageIcon(borrarPrincImageScaledInstance);
        btnBorrar.setIcon(borrar);
        //////////////////////////////

        //Redimensionar Imagen BUSCAR//
        ImageIcon buscarPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/lupa.png")));
        Image buscarPrincImage = buscarPrinc.getImage();
        Image buscarPrincImageScaledInstance = buscarPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon buscar = new ImageIcon(buscarPrincImageScaledInstance);
        btnBuscar.setIcon(buscar);
        //////////////////////////////

        //Redimensionar Imagen FILTRAR//
        ImageIcon filtrarPrinc = new ImageIcon(Objects.requireNonNull(getClass().getResource("imagenes/filtrar.png")));
        Image filtrarPrincImage = filtrarPrinc.getImage();
        Image filtrarPrincImageScaledInstance = filtrarPrincImage.getScaledInstance(30, 30,  Image.SCALE_SMOOTH);
        ImageIcon filtrar = new ImageIcon(filtrarPrincImageScaledInstance);
        btnFiltrar.setIcon(filtrar);
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
        dashboardButton.addActionListener(new ActionListener() {
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
        btnHome.addActionListener(new ActionListener() {
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

    public static void createTable(JTable tabla){
        try{
            if (DataManager.proveedores == null){
                DataManager.proveedores = new ArrayList<>();
            }

            String [][] data = new String[DataManager.proveedores.size()][5];
            cargarProveedores(data);


            String [] cabe = new String[]{"ID", "Nombre", "Correo Electrónico", "Código Postal", "Dirección"};

            tabla.setModel(new DefaultTableModel(
                    data, cabe
            ));
            tabla.getTableHeader().setReorderingAllowed(false);
            tabla.setEnabled(true);
            tabla.setDefaultEditor(Object.class, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String [][] cargarProveedores(String [][] data){
        try{
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    if (DataManager.proveedores.get(i) != null){
                        if (j == 0){
                            data[i][j] = String.valueOf((DataManager.proveedores.get(i).getId()));
                        } else if (j == 1){
                            data[i][j] = String.valueOf((DataManager.proveedores.get(i).getNombre()));
                        } else if (j == 2){
                            data[i][j] = String.valueOf((DataManager.proveedores.get(i).getCorreo()));
                        } else if (j == 3){
                            data[i][j] = String.valueOf((DataManager.proveedores.get(i).getCp()));
                        } else if (j == 4){
                            data[i][j] = String.valueOf((DataManager.proveedores.get(i).getDireccion()));
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading students", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return data;
    }
}
