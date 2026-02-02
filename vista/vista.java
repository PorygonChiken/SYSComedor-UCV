package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
    
public class vista extends JFrame {

    public JTextField txtUsuario;
    public JPasswordField txtContrasena;
    public JButton btnLogin;

    public JButton btnRegis;

    public vista () {
        setTitle("Comedor UCV");
        setSize(1200, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(181, 246, 255));
        
        JPanel login = new JPanel();
        login.setLayout(new BoxLayout(login, BoxLayout.Y_AXIS));
        login.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        login.setBackground(Color.WHITE);
        login.setPreferredSize(new Dimension(500, 280));

        login.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel titulo = new JLabel("SYSComedorUCV");
        titulo.setFont(new Font("IMPACT", Font.PLAIN, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.add(titulo);

        login.add(Box.createRigidArea(new Dimension(0, 30)));

        Dimension labelSize = new Dimension(100, 30);
        Font fuente = new Font("ARIAL", Font.BOLD, 16);
        //subpanel usuario
        JPanel filaUsuario = new JPanel();
        filaUsuario.setLayout(new FlowLayout(FlowLayout.CENTER));
        filaUsuario.setBackground(Color.WHITE);
        filaUsuario.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); 

        JLabel lbUsuario = new JLabel("Usuario: ");
        lbUsuario.setPreferredSize(labelSize);
        lbUsuario.setFont(fuente);
        lbUsuario.setHorizontalAlignment(SwingConstants.LEFT);
        
        txtUsuario = new JTextField(20);
        txtUsuario.setPreferredSize(new Dimension(200, 30));

        filaUsuario.add(lbUsuario);
        filaUsuario.add(txtUsuario);
        login.add(filaUsuario);

        login.add(Box.createRigidArea(new Dimension(0, 10))); 
        //subpanel contrasena
        JPanel filaContrasena = new JPanel();
        filaContrasena.setLayout(new FlowLayout(FlowLayout.CENTER));
        filaContrasena.setBackground(Color.WHITE);
        filaContrasena.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JLabel lbContrasena = new JLabel("Contraseña: ");
        lbContrasena.setPreferredSize(labelSize);
        lbContrasena.setFont(fuente);
        lbContrasena.setHorizontalAlignment(SwingConstants.LEFT);

        txtContrasena = new JPasswordField(20);
        txtContrasena.setPreferredSize(new Dimension(200, 30));

        filaContrasena.add(lbContrasena);
        filaContrasena.add(txtContrasena);
        login.add(filaContrasena);

        login.add(Box.createRigidArea(new Dimension(0, 30)));

        btnLogin = new JButton("Iniciar sesión");
        btnLogin.setFont(fuente);
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.add(btnLogin);

        login.add(Box.createRigidArea(new Dimension(0, 10)));

        btnRegis = new JButton("Registrarse");
        btnRegis.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegis.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login.add(btnRegis);

        panelPrincipal.add(login);
        add(panelPrincipal);
    }
    
    public String getUsuario() {
        return txtUsuario.getText();
    }

    public String getContra() {
        return new String(txtContrasena.getPassword());
    }

    public void limpiar() {
        txtUsuario.setText("");
        txtContrasena.setText("");
    }

    public void setControlador(ActionListener a) {
        btnLogin.addActionListener(a);
        btnLogin.setActionCommand("login");
        btnRegis.addActionListener(a);
        btnRegis.setActionCommand("registrar"); 
    }
}