package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class vistaReg extends JFrame {

    public JTextField txtNuevoUsuario;
    public JPasswordField txtNuevaContrasena;
    public JTextField txtCedula;
    public JButton btnRegistrar;
    public JButton btnVolver;

    public vistaReg() {
        menuUtils.configurarFrame(this, "Registro Comedor UCV", 1200, 720, JFrame.DO_NOTHING_ON_CLOSE);

        JPanel panelPrincipal = menuUtils.crearPanelPrincipal();
        JPanel registro = menuUtils.crearPanelCaja(new Dimension(500, 320));

        registro.add(Box.createRigidArea(menuUtils.ESPACIO_20));

        registro.add(menuUtils.crearTitulo("Registrarse"));

        registro.add(Box.createRigidArea(menuUtils.ESPACIO_30));

        txtNuevoUsuario = new JTextField(20);
        txtNuevaContrasena = new JPasswordField(20);
        txtCedula = new JTextField(20);

        registro.add(menuUtils.crearFila("Usuario: ", txtNuevoUsuario));
        registro.add(Box.createRigidArea(menuUtils.ESPACIO_10));

        registro.add(menuUtils.crearFila("Cédula: ", txtCedula));
        registro.add(Box.createRigidArea(menuUtils.ESPACIO_10));
        
        registro.add(menuUtils.crearFila("Contraseña: ", txtNuevaContrasena));
        registro.add(Box.createRigidArea(menuUtils.ESPACIO_30));

        btnRegistrar = menuUtils.crearBoton("Registrar");
        registro.add(btnRegistrar);

        registro.add(Box.createRigidArea(menuUtils.ESPACIO_10));
        btnVolver = menuUtils.crearBoton("Volver"); 
        btnVolver.setBackground(new Color(255, 100, 100)); 
        btnVolver.setForeground(Color.WHITE);
        registro.add(btnVolver);
        registro.add(Box.createRigidArea(menuUtils.ESPACIO_20));
        panelPrincipal.add(registro);
        add(panelPrincipal);
    }

    public String getUsuario() { return txtNuevoUsuario.getText(); }
    public String getCedula() { return txtCedula.getText(); }
    public String getContra() { return new String(txtNuevaContrasena.getPassword()); }
    
    public void limpiar(){
        txtNuevoUsuario.setText("");
        txtCedula.setText("");
        txtNuevaContrasena.setText("");
    }
    public void setControlador(ActionListener ac){
        btnRegistrar.addActionListener(ac);
        btnRegistrar.setActionCommand("guardar_registro");
        btnVolver.addActionListener(ac);
        btnVolver.setActionCommand("volver");
    }
}