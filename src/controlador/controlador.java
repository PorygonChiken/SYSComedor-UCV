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
        String cedula = vista.getUsuario(); 
        String cntr = vista.getContra();
        boolean datos = this.modelo.verificar(cedula, cntr);
        if(cedula.matches("[0-7]+")){
            JOptionPane.showMessageDialog(vista, "solo numeross", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(datos){
            vista.dispose(); 
            if (this.modelo.Admin(cedula)) {
                JOptionPane.showMessageDialog(null, "hola admin");
                new ControladorMenuAdmin();
            } else {
                JOptionPane.showMessageDialog(null, "Bienvenido Estudiante: "+cedula);
                vistaMenu.setVisible(true); 
            }

        }else{
            JOptionPane.showMessageDialog(vista, "cédula o contraseña incorrectos", "error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void Registro(){
        String cedula = vista.getUsuario(); 
        String cntr = vista.getContra();
        if(cedula.isEmpty() || cntr.isEmpty()){
            JOptionPane.showMessageDialog(vista, "Se tienen que llenar todos los datos");
            return;
        }
        if(!cedula.matches("[0-7]+")){
            JOptionPane.showMessageDialog(vista, "solo numeross", "error", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        boolean guardado = this.modelo.registrarUsuario(cedula, cntr);
        if (guardado) {
            JOptionPane.showMessageDialog(vista, "usuario registrado");
            vista.limpiar();
        } else {
            JOptionPane.showMessageDialog(vista, "error al registrarse", "error", JOptionPane.WARNING_MESSAGE);
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