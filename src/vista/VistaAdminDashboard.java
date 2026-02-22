package vista;

import utils.menuUtils;

import javax.swing.*;
import java.awt.*;

public class VistaAdminDashboard extends JFrame {

    public JButton btnGestionarMenus;
    public JButton btnVerMenus; 
    public JButton btnCerrarSesion;

    public VistaAdminDashboard() {
        menuUtils.configurarFrame(this, "Menú Administrador - Comedor UCV", 1200, 720, JFrame.EXIT_ON_CLOSE);

        JPanel panelPrincipal = menuUtils.crearPanelPrincipal();
        JPanel menuPanel = menuUtils.crearPanelCaja(new Dimension(500, 500));

        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_20)); 

        menuPanel.add(menuUtils.crearTitulo("Bienvenido Administrador"));

        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_30)); 

        btnGestionarMenus = menuUtils.crearBoton("Gestionar Menús", new Dimension(300, 40));
        menuPanel.add(btnGestionarMenus);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        btnVerMenus = menuUtils.crearBoton("Ver Menús", new Dimension(300, 40));
        menuPanel.add(btnVerMenus);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 135))); 

        btnCerrarSesion = menuUtils.crearBoton("Cerrar Sesión", new Dimension(300, 40));
        btnCerrarSesion.setBackground(new Color(255, 100, 100));
        btnCerrarSesion.setForeground(Color.WHITE);
        menuPanel.add(btnCerrarSesion);

        panelPrincipal.add(menuPanel);
        add(panelPrincipal);
    }
}