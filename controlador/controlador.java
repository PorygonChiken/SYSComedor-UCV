package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import vista.vista;
import modelo.modelo;

public class controlador implements ActionListener{
    private vista vista;
    private modelo modelo;

    public controlador() {
        this.vista = new vista();
        this.modelo = new modelo();

        this.vista.setControlador(this);
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent a) {
        String z = a.getActionCommand();

        switch (z) {
            case "login":
                login();
                break;
            case "registrar":
                Registro();
                break;
            case "salir":
                System.exit(0);
                break;
        }
    }

    private void login() {
        String usuario = vista.getUsuario();
        String cntr = vista.getContra();

        boolean v = this.modelo.verificar(usuario, cntr);

        if (v) {
            JOptionPane.showMessageDialog(vista, "hola " + usuario);
        } else {
            JOptionPane.showMessageDialog(vista, "error: usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Registro() {
        String usuario = vista.getUsuario();
        String cntr = vista.getContra();

        if(usuario.isEmpty() || cntr.isEmpty()){
            JOptionPane.showMessageDialog(vista, "se tiene que llenar todos los datos para registrarse");
            return;
        }
        
        boolean guardado = this.modelo.registrarUsuario(usuario, cntr);

        if (guardado) {
            JOptionPane.showMessageDialog(vista, "usuario registrado");
            vista.limpiar();
        } else {
            JOptionPane.showMessageDialog(vista, "error al registrarse", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }
}