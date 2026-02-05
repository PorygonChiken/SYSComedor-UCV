package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class vistaReg extends JFrame {

    public JTextField txtNuevoUsuario;
    public JPasswordField txtNuevaContrasena;
    public JTextField txtCedula;
    public JButton btnRegistrar;

    public vistaReg() {
        setTitle("Registro Comedor UCV");
        setSize(1200, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(181, 246, 255));

        JPanel registro = new JPanel();
        registro.setLayout(new BoxLayout(registro, BoxLayout.Y_AXIS));
        registro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        registro.setBackground(Color.WHITE);
        registro.setPreferredSize(new Dimension(500, 280));

        Dimension espacio10 = new Dimension(0, 10);
        Dimension espacio30 = new Dimension(0,30);

        registro.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel titulo = new JLabel("Registrarse");
        titulo.setFont(new Font("IMPACT", Font.PLAIN, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        registro.add(titulo);

        registro.add(Box.createRigidArea(espacio30));

        txtNuevoUsuario = new JTextField(20);
        txtNuevaContrasena = new JPasswordField(20);
        txtCedula = new JTextField(20);

        registro.add(crearFila("Usuario: ", txtNuevoUsuario));
        registro.add(Box.createRigidArea(espacio10));

        registro.add(crearFila("Cédula: ", txtCedula));
        registro.add(Box.createRigidArea(espacio10));
        
        registro.add(crearFila("Contraseña: ", txtNuevaContrasena));
        registro.add(Box.createRigidArea(espacio30));

        btnRegistrar = crearBoton("Registrar");
        registro.add(btnRegistrar);

        registro.add(Box.createRigidArea(espacio30));

        panelPrincipal.add(registro);
        add(panelPrincipal);
    }

    private JPanel crearFila(String textoLabel, JTextField campo) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fila.setBackground(Color.WHITE);
        fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JLabel label = new JLabel(textoLabel);
        label.setPreferredSize(new Dimension(100, 30));
        label.setFont(new Font("ARIAL", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.LEFT);

        campo.setPreferredSize(new Dimension(200, 30));

        fila.add(label);
        fila.add(campo);

        return fila;
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("ARIAL", Font.BOLD, 16));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setFocusPainted(false);
        return boton;
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
    }
}