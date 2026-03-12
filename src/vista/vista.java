package vista;

import utils.menuUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class vista extends JFrame {

    public JTextField txtUsuario;
    public JPasswordField txtContrasena;
    public JButton btnLogin;
    public JButton btnRegis;

    public vista() {
        menuUtils.configurarFrame(this, "Comedor UCV", 1200, 720, JFrame.EXIT_ON_CLOSE);

        JPanel panelPrincipal = menuUtils.crearPanelPrincipal();
        JPanel login = menuUtils.crearPanelCaja(new Dimension(500, 280));

        login.add(Box.createRigidArea(menuUtils.ESPACIO_20));

        login.add(menuUtils.crearTitulo("SYSComedorUCV"));

        login.add(Box.createRigidArea(menuUtils.ESPACIO_30));

        txtUsuario = new JTextField(20);
        txtContrasena = new JPasswordField(20);

        login.add(Box.createRigidArea(menuUtils.ESPACIO_10));

        login.add(menuUtils.crearFila("Usuario: ", txtUsuario));
        login.add(Box.createRigidArea(menuUtils.ESPACIO_10));
        
        login.add(menuUtils.crearFila("Contraseña: ", txtContrasena));
        login.add(Box.createRigidArea(menuUtils.ESPACIO_30));

        btnLogin = menuUtils.crearBoton("Iniciar sesión");
        login.add(btnLogin);

        login.add(Box.createRigidArea(menuUtils.ESPACIO_10));

        btnRegis = menuUtils.crearBoton("Registrarse");
        btnRegis.setFont(new Font("ARIAL", Font.PLAIN, 12));
        btnRegis.setBorderPainted(false);
        btnRegis.setContentAreaFilled(false);
        btnRegis.setForeground(Color.BLUE);
        btnRegis.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRegis.setFocusPainted(false);
        login.add(btnRegis);

        login.add(Box.createRigidArea(menuUtils.ESPACIO_30));

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