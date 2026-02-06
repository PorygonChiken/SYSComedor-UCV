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
        menuUtils.configurarFrame(this, "Menú Administrador - Comedor UCV", 1200, 720, JFrame.EXIT_ON_CLOSE);

        JPanel panelPrincipal = menuUtils.crearPanelPrincipal();
        JPanel menuPanel = menuUtils.crearPanelCaja(new Dimension(500, 430));

        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_20)); 

        menuPanel.add(menuUtils.crearTitulo("Bienvenido Administrador"));

        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_30)); 

       
        btnInventario = menuUtils.crearBoton("Inventario", new Dimension(300, 40));
        menuPanel.add(btnInventario);
        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_10));

        btnReporteDemanda = menuUtils.crearBoton("Generar Reporte de Demanda", new Dimension(300, 40));
        menuPanel.add(btnReporteDemanda);
        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_10));

        btnRegistrarConsumo = menuUtils.crearBoton("Registrar Consumo", new Dimension(300, 40));
        menuPanel.add(btnRegistrarConsumo);
        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_10));

        btnGestionarMenus = menuUtils.crearBoton("Gestionar Menús", new Dimension(300, 40));
        menuPanel.add(btnGestionarMenus);
        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_10));

        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_30)); 

        btnCerrarSesion = menuUtils.crearBoton("Cerrar Sesión", new Dimension(300, 40));
        btnCerrarSesion.setBackground(new Color(255, 100, 100));
        btnCerrarSesion.setForeground(Color.WHITE);
        menuPanel.add(btnCerrarSesion);

     
        panelPrincipal.add(menuPanel);
        add(panelPrincipal);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VistaAdminDashboard().setVisible(true));
    }
}