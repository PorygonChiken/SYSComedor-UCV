package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class vista extends JFrame {

    public JTextField txtUsuario;
    public JPasswordField txtContrasena;
    public JButton btnLogin;
    public JButton btnRegis;

    public vista() {
        menuUtils utils = new menuUtils();
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

        Dimension espacio10 = new Dimension(0, 10);
        Dimension espacio30 = new Dimension(0,30);

        login.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel titulo = new JLabel("SYSComedorUCV");
        titulo.setFont(new Font("IMPACT", Font.PLAIN, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.add(titulo);

        login.add(Box.createRigidArea(espacio30));

        txtUsuario = new JTextField(20);
        txtContrasena = new JPasswordField(20);

        login.add(Box.createRigidArea(espacio10));

        login.add(utils.crearFila("Usuario: ", txtUsuario));
        login.add(Box.createRigidArea(espacio10));
        
        login.add(utils.crearFila("Contraseña: ", txtContrasena));
        login.add(Box.createRigidArea(espacio30));

        btnLogin = utils.crearBoton("Iniciar sesión");
        login.add(btnLogin);

        login.add(Box.createRigidArea(espacio10));

        btnRegis = utils.crearBoton("Registrarse");
        btnRegis.setFont(new Font("ARIAL", Font.PLAIN, 12));
        btnRegis.setBorderPainted(false);
        btnRegis.setContentAreaFilled(false);
        btnRegis.setForeground(Color.BLUE);
        btnRegis.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegis.setFocusPainted(false);
        login.add(btnRegis);

        login.add(Box.createRigidArea(espacio30));

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