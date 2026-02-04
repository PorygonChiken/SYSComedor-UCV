package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class vistaMenuUsuario extends JFrame {
    public JButton btnVerMenu;
    public JButton btnMonedero;
    public JButton btnSalir;
    public vistaMenuUsuario() {
        setTitle("Menú Principal - Comedor UCV");
        setSize(1200, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(181, 246, 255));
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setPreferredSize(new Dimension(500, 320)); 
        Dimension espacio10 = new Dimension(0, 10);
        Dimension espacio20 = new Dimension(0, 20);
        Dimension espacio30 = new Dimension(0, 30);
        menuPanel.add(Box.createRigidArea(espacio20));
        JLabel titulo = new JLabel("Bienvenido Usuario");
        titulo.setFont(new Font("IMPACT", Font.PLAIN, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(titulo);
        menuPanel.add(Box.createRigidArea(espacio30));
        btnVerMenu = crearBoton("Ver Menú del Día");
        menuPanel.add(btnVerMenu);
        menuPanel.add(Box.createRigidArea(espacio10));
        btnMonedero = crearBoton("monedero");
        menuPanel.add(btnMonedero);
        menuPanel.add(Box.createRigidArea(espacio30)); 
        btnSalir = crearBoton("Cerrar Sesión");
        btnSalir.setBackground(new Color(255, 100, 100)); 
        btnSalir.setForeground(Color.WHITE);
        menuPanel.add(btnSalir);
        menuPanel.add(Box.createRigidArea(espacio20));
        panelPrincipal.add(menuPanel);
        add(panelPrincipal);
    }
    private JButton crearBoton(String texto){
        JButton boton = new JButton(texto);
        boton.setFont(new Font("ARIAL", Font.BOLD, 16));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setFocusPainted(false);
        boton.setMaximumSize(new Dimension(250, 40)); 
        return boton;
    }
    public void setControlador(ActionListener ac){
        btnVerMenu.addActionListener(ac);
        btnVerMenu.setActionCommand("ver_menu");
        
        btnMonedero.addActionListener(ac);
        btnMonedero.setActionCommand("ver_saldo");

        btnSalir.addActionListener(ac);
        btnSalir.setActionCommand("logout"); 
    }
}