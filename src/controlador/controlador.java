 package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;
import javax.swing.JOptionPane;
import modelo.Menu;
import modelo.modelo;
import vista.vista;
import vista.vistaMenuUsuario;
import vista.vistaReg;

public class controlador implements ActionListener{
    private vista vista;
    private modelo modelo;
    private vistaMenuUsuario vistaMenu; 
    private vistaReg vistaRegi;    
    private String usuarioActual;
    
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
            case "recargar":
                recargarSaldo();
                break;
        }
    }

    private void login(){
        String usuario = vista.getUsuario(); 
        String cntr = vista.getContra();
        boolean datos = this.modelo.verificar(usuario, cntr);
        if(datos){
            String cedula = this.modelo.obtenerCedulaPorUsuario(usuario);
            if (cedula == null || cedula.isEmpty()) {
                JOptionPane.showMessageDialog(
                    vista,
                    "No se encontro la cedula del usuario.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            String rol = this.modelo.obtenerRol(cedula);
            if (rol == null) {
                JOptionPane.showMessageDialog(
                    vista,
                    "No se encontro el rol en la base de datos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            if(rol.equals("admin")){
                new ControladorDashboard();
                vista.dispose();
            }else{
                this.usuarioActual = usuario;
                vistaMenu.setUsuario(usuario + " (" + rol + ")");
                // Versión actualizada para usar List<Menu>
                List<Menu> menus = this.modelo.obtenerMenusDisponibles();
                vistaMenu.setMenu(menus);
                String saldo = this.modelo.Saldo(usuario);
                vistaMenu.setMonedero(saldo);
                vistaMenu.setVisible(true);
                vista.dispose();
            }
        }else{
            JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void recargarSaldo() {
        if (this.usuarioActual == null || this.usuarioActual.isEmpty()) {
            JOptionPane.showMessageDialog(vistaMenu, "Error: Usuario no identificado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String input = JOptionPane.showInputDialog(vistaMenu, "Ingrese monto a recargar (Bs):", "Recargar Saldo", JOptionPane.QUESTION_MESSAGE);
        
        if (input != null && !input.trim().isEmpty()) {
            try {
                double monto = Double.parseDouble(input);
                if (monto > 0) {
                    boolean exito = this.modelo.recargarSaldo(this.usuarioActual, monto);
                    if (exito) {
                        JOptionPane.showMessageDialog(vistaMenu, "Recarga exitosa!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        String nuevoSaldo = this.modelo.Saldo(this.usuarioActual);
                        vistaMenu.setMonedero(nuevoSaldo);
                    } else {
                        JOptionPane.showMessageDialog(vistaMenu, "Error al recargar saldo.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(vistaMenu, "El monto debe ser positivo.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaMenu, "Monto inválido. Ingrese un número.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
            this.usuarioActual = null;
            vistaMenu.setVisible(false); 
            vistaMenu.setUsuario("");
            vista.limpiar();             
            vista.setVisible(true);    
        }
    }
}