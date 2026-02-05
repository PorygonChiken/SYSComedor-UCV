package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.modelo;
import vista.vista;
import vista.vistaMenuUsuario;
import controlador.ControladorMenuAdmin;

public class controlador implements ActionListener {
    private vista vista;
    private modelo modelo;
    private vistaMenuUsuario vistaMenu; 

    public controlador() {
        this.vista = new vista();
        this.modelo = new modelo();
        this.vistaMenu = new vistaMenuUsuario();

        this.vista.setControlador(this);
        this.vistaMenu.setControlador(this); 
        
        this.vista.setVisible(true); 
    }
    
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
            case "logout":
                cerrarSesion(); 
                break;
            case "ver_menu":
                String menudia = "Menú de hoy\n\n"+
                "Sopa: Crema de Zanahoria\n"+
                "Comida: Pollo con arroz y tajadas\n"+
                "Jugo: Jugo de naranja\n"+
                "horario: 11:30am - 1:00pm";      
                JOptionPane.showMessageDialog(vistaMenu, menudia, "Menú del Comedor", JOptionPane.INFORMATION_MESSAGE);
                break;
            case "ver_saldo":
                JOptionPane.showMessageDialog(vistaMenu, "Saldo actual: 1,000 bs", "Mi Monedero", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }

    private void login(){
        String usuario = vista.getUsuario();
        String cntr = vista.getContra();
        boolean v = this.modelo.verificar(usuario, cntr);

        if(v){
            vista.dispose(); 
            if(this.modelo.Admin(usuario)){
                JOptionPane.showMessageDialog(null, "Hola admin " + usuario);
                new ControladorMenuAdmin();
            }else{
                JOptionPane.showMessageDialog(null, "Hola " + usuario);
                vistaMenu.setVisible(true); 
            }
        }else{
            JOptionPane.showMessageDialog(vista, "Datos incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void Registro(){
        String usuario = vista.getUsuario();
        String cntr = vista.getContra();
        if(usuario.isEmpty() || cntr.isEmpty()){
            JOptionPane.showMessageDialog(vista, "Debe completar todos los campos para poder registrarse");
            return;
        }
        boolean guardado = this.modelo.registrarUsuario(usuario, cntr);
        if (guardado) {
            JOptionPane.showMessageDialog(vista, "Usuario registrado");
            vista.limpiar();
        } else {
            JOptionPane.showMessageDialog(vista, "Error al registrarse", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void cerrarSesion(){
        int confirm = JOptionPane.showConfirmDialog(vistaMenu, "¿Seguro que desea cerrar sesión?", "Salir", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION){
            vistaMenu.setVisible(false); 
            vista.limpiar();             
            vista.setVisible(true);    
        }
    }
}