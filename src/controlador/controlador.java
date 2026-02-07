package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import modelo.modelo;
import vista.vista;
import vista.vistaMenuUsuario;
import vista.vistaReg;

public class controlador implements ActionListener{
    private vista vista;
    private modelo modelo;
    private vistaMenuUsuario vistaMenu; 
    private vistaReg vistaRegi;    
    
    public controlador(){
        this.vista = new vista();
        this.modelo = new modelo();
        this.vistaMenu = new vistaMenuUsuario();
        this.vistaRegi = new vistaReg(); 
        this.vista.setControlador(this);
        this.vistaMenu.setControlador(this); 
        this.vistaRegi.setControlador(this); 
        
        this.vista.setVisible(true); 
    }
    
    public void actionPerformed(ActionEvent a) {
        String z = a.getActionCommand();

        switch (z) {
            case "login":
                login();
                break;
            case "registrar":
                vistaRegi.setVisible(true);
                vista.setVisible(false);
                break;
            case "guardar_registro":
                guardarNuevoUsuario();
                break;
            case "volver":
                vista.setVisible(true); 
                vistaRegi.limpiar();
                vistaRegi.setVisible(false);
                break;
            case "salir":
                System.exit(0);
                break;
            case "logout":
                cerrarSesion(); 
                break;
        }
    }

    private void login(){
        String usuario = vista.getUsuario(); 
        String cntr = vista.getContra();
        boolean datos = this.modelo.verificar(usuario, cntr);
        if(datos){
            String cedula = this.modelo.obtenerCedulaPorUsuario(usuario);
            if(cedula != null && this.modelo.Admin(cedula)){
                new ControladorDashboard();
                vista.dispose();
            }else{
                vistaMenu.setUsuario(usuario);
                String menu = this.modelo.Menu();
                vistaMenu.setMenu(menu);
                String saldo = this.modelo.Saldo(usuario);
                vistaMenu.setMonedero(saldo);
                vistaMenu.setVisible(true);
                vista.dispose();
            }
        }else{
            JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void guardarNuevoUsuario(){
        String user = vistaRegi.getUsuario(); 
        String cedula = vistaRegi.getCedula();
        String cntr = vistaRegi.getContra();
        if(user.isEmpty() || cedula.isEmpty() || cntr.isEmpty()){
            JOptionPane.showMessageDialog(vistaRegi, "Se deben llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!cedula.matches("[0-9]+")){
            JOptionPane.showMessageDialog(vistaRegi, "Solo números", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        boolean guardar = this.modelo.registrarUsuario(user, cedula, cntr);

        if (guardar) {
            JOptionPane.showMessageDialog(vistaRegi, "Usuario registrado con éxito");
            vistaRegi.limpiar();
            vistaRegi.dispose(); 
            vista.setVisible(true); 
        }else{
            JOptionPane.showMessageDialog(vistaRegi, "Error al registrarse", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void cerrarSesion(){
        int confirm = JOptionPane.showConfirmDialog(vistaMenu, "Desea cerrar sesión?", "Salir", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION){
            vistaMenu.setVisible(false); 
            vistaMenu.setUsuario("");
            vista.limpiar();             
            vista.setVisible(true);    
        }
    }
}