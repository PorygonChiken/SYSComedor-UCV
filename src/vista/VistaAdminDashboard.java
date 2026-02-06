package vista;
import javax.swing.*;
import java.awt.*;

public class VistaAdminDashboard extends JFrame {

    public JButton btnInventario;
    public JButton btnReporteDemanda;
    public JButton btnRegistrarConsumo;
    public JButton btnGestionarMenus;
    public JButton btnCerrarSesion;

    public VistaAdminDashboard() {
        setTitle("Menú Administrador - Comedor UCV");
        setSize(1200, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(181, 246, 255));

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        menuPanel.setBackground(Color.WHITE);
        
        menuPanel.setPreferredSize(new Dimension(500, 430)); 

  
        Dimension espacio10 = new Dimension(0, 10);
        Dimension espacio20 = new Dimension(0, 20);
        Dimension espacio30 = new Dimension(0, 30);

     
        
        menuPanel.add(Box.createRigidArea(espacio20)); 

     
        JLabel titulo = new JLabel("Bienvenido Administrador");
        titulo.setFont(new Font("IMPACT", Font.PLAIN, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(titulo);

        menuPanel.add(Box.createRigidArea(espacio30)); 

       
        btnInventario = crearBoton("Inventario");
        menuPanel.add(btnInventario);
        menuPanel.add(Box.createRigidArea(espacio10));

        btnReporteDemanda = crearBoton("Generar Reporte de Demanda");
        menuPanel.add(btnReporteDemanda);
        menuPanel.add(Box.createRigidArea(espacio10));

        btnRegistrarConsumo = crearBoton("Registrar Consumo");
        menuPanel.add(btnRegistrarConsumo);
        menuPanel.add(Box.createRigidArea(espacio10));

        btnGestionarMenus = crearBoton("Gestionar Menús");
        menuPanel.add(btnGestionarMenus);
        menuPanel.add(Box.createRigidArea(espacio10));

        menuPanel.add(Box.createRigidArea(espacio30)); 

        btnCerrarSesion = crearBoton("Cerrar Sesión");
        btnCerrarSesion.setBackground(new Color(255, 100, 100));
        btnCerrarSesion.setForeground(Color.WHITE);
        menuPanel.add(btnCerrarSesion);

     
        panelPrincipal.add(menuPanel);
        add(panelPrincipal);
    }

    private JButton crearBoton(String texto){
        JButton boton = new JButton(texto);
        boton.setFont(new Font("ARIAL", Font.BOLD, 16));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setFocusPainted(false);
        boton.setMaximumSize(new Dimension(300, 40)); 
        return boton;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaAdminDashboard().setVisible(true));
    }
}