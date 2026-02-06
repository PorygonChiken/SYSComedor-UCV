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
        menuUtils utils = new menuUtils();
        setTitle("Registro Comedor UCV");
        setSize(1200, 720);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(181, 246, 255));

        JPanel registro = new JPanel();
        registro.setLayout(new BoxLayout(registro, BoxLayout.Y_AXIS));
        registro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        registro.setBackground(Color.WHITE);
        registro.setPreferredSize(new Dimension(500, 320));

        registro.add(Box.createRigidArea(utils.espacio20));

        JLabel titulo = new JLabel("Registrarse");
        titulo.setFont(new Font("IMPACT", Font.PLAIN, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        registro.add(titulo);

        registro.add(Box.createRigidArea(utils.espacio30));

        txtNuevoUsuario = new JTextField(20);
        txtNuevaContrasena = new JPasswordField(20);
        txtCedula = new JTextField(20);

        registro.add(utils.crearFila("Usuario: ", txtNuevoUsuario));
        registro.add(Box.createRigidArea(utils.espacio10));

        registro.add(utils.crearFila("Cédula: ", txtCedula));
        registro.add(Box.createRigidArea(utils.espacio10));
        
        registro.add(utils.crearFila("Contraseña: ", txtNuevaContrasena));
        registro.add(Box.createRigidArea(utils.espacio30));

        btnRegistrar = utils.crearBoton("Registrar");
        registro.add(btnRegistrar);

        registro.add(Box.createRigidArea(utils.espacio10));
        btnVolver = utils.crearBoton("Volver"); 
        btnVolver.setBackground(new Color(255, 100, 100)); 
        btnVolver.setForeground(Color.WHITE);
        registro.add(btnVolver);
        registro.add(Box.createRigidArea(utils.espacio20));
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