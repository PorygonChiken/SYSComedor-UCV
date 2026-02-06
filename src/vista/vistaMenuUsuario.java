package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class vistaMenuUsuario extends JFrame {
    
    public JButton btnSalir;
    private JLabel lblSaludo;
    private JTextArea txtTextoMenu;     
    private JLabel lblTextoMonedero; 

    public vistaMenuUsuario() {
        menuUtils.configurarFrame(this, "Menú Principal", 1200, 720, JFrame.EXIT_ON_CLOSE);

        JPanel panelPrincipal = menuUtils.crearPanelPrincipal();

        JPanel contenedor = new JPanel(new BorderLayout(0, 20));
        contenedor.setBackground(Color.WHITE);
        contenedor.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        contenedor.setPreferredSize(new Dimension(900, 500));

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        
        lblSaludo = new JLabel("Buenos días usuario");
        lblSaludo.setFont(new Font("ARIAL", Font.BOLD, 26));
        header.add(lblSaludo, BorderLayout.WEST);

        btnSalir = new JButton("Cerrar sesión");
        btnSalir.setFont(new Font("ARIAL", Font.BOLD, 14));
        btnSalir.setBackground(new Color(230, 230, 230));
        btnSalir.setFocusPainted(false);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        header.add(btnSalir, BorderLayout.EAST);

        JPanel body = new JPanel();
        body.setBackground(Color.WHITE);
        body.setLayout(new BoxLayout(body, BoxLayout.X_AXIS));

        JPanel panelMenu = new JPanel();
        panelMenu.setBackground(Color.WHITE);
        panelMenu.setLayout(new BoxLayout(panelMenu, BoxLayout.Y_AXIS));
        panelMenu.setPreferredSize(new Dimension(560, 380));
        
        JLabel tituloMenu = new JLabel("Menú del día");
        tituloMenu.setFont(new Font("ARIAL", Font.BOLD, 18));
        tituloMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        txtTextoMenu = new JTextArea("C");
        txtTextoMenu.setFont(new Font("ARIAL", Font.PLAIN, 16));
        txtTextoMenu.setEditable(false);    
        txtTextoMenu.setFocusable(false);      
        txtTextoMenu.setBackground(Color.WHITE);
        txtTextoMenu.setLineWrap(true);      
        txtTextoMenu.setWrapStyleWord(true);
        txtTextoMenu.setAlignmentX(Component.LEFT_ALIGNMENT);
  
        txtTextoMenu.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0)); 
        
        panelMenu.add(tituloMenu);
        panelMenu.add(Box.createRigidArea(new Dimension(0, 15)));
        panelMenu.add(txtTextoMenu);
        panelMenu.add(Box.createVerticalGlue());

        JPanel panelMonedero = new JPanel();
        panelMonedero.setBackground(Color.WHITE);
        panelMonedero.setLayout(new BoxLayout(panelMonedero, BoxLayout.Y_AXIS));
        
        JLabel tituloMonedero = new JLabel("Monedero");
        tituloMonedero.setFont(new Font("ARIAL", Font.BOLD, 18));
        tituloMonedero.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblTextoMonedero = new JLabel("S");
        lblTextoMonedero.setFont(new Font("ARIAL", Font.PLAIN, 14));
        lblTextoMonedero.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelMonedero.add(tituloMonedero);
        panelMonedero.add(Box.createRigidArea(new Dimension(0, 120)));
        panelMonedero.add(lblTextoMonedero);
        panelMonedero.add(Box.createVerticalGlue());

        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setForeground(Color.GRAY);
        separator.setMaximumSize(new Dimension(2, Integer.MAX_VALUE));

        body.add(panelMenu);
        body.add(Box.createHorizontalGlue());
        body.add(separator);
        body.add(Box.createRigidArea(new Dimension(20, 0)));
        
        JPanel monederoWrapper = new JPanel(new BorderLayout());
        monederoWrapper.setBackground(Color.WHITE);
        monederoWrapper.setPreferredSize(new Dimension(240, 380));
        monederoWrapper.add(panelMonedero, BorderLayout.CENTER);
        
        body.add(monederoWrapper);
        JSeparator topLine = new JSeparator(SwingConstants.HORIZONTAL);
        topLine.setForeground(Color.BLACK);

        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(topLine);
        content.add(Box.createRigidArea(new Dimension(0, 15)));
        content.add(body);

        contenedor.add(header, BorderLayout.NORTH);
        contenedor.add(content, BorderLayout.CENTER);

        panelPrincipal.add(contenedor);
        add(panelPrincipal);
    }
    public void setUsuario(String usuario) {
        lblSaludo.setText("Bienvenido, " + usuario);
    }
    public void setMenu(String textoMenu) {
        txtTextoMenu.setText(textoMenu);
    }

    public void setMonedero(String saldo) {
        lblTextoMonedero.setText(saldo + " Bs");
        lblTextoMonedero.setFont(new Font("ARIAL", Font.BOLD, 42));
    }

    public void setControlador(ActionListener ac) {
        btnSalir.addActionListener(ac);
        btnSalir.setActionCommand("logout");
    }
}