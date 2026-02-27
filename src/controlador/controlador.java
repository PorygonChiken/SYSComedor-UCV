package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Menu;
import modelo.modelo;
import modelo.CalculadoraCostos;
import vista.vista;
import vista.vistaMenuUsuario;
import vista.vistaReg;
import modelo.ModeloEliminarMenu;

public class controlador implements ActionListener{
    private vista vista;
    private modelo modelo;
    private vistaMenuUsuario vistaMenu; 
    private vistaReg vistaRegi;    
    private String usuarioActual;
    
    public controlador(){
        ModeloEliminarMenu limpiador = new ModeloEliminarMenu();
        limpiador.limpiarReservasVencidas();
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
            case "reservar_menu":
                reservarMenu(a);
                break;
            case "ver_reservas":
                mostrarReservas();
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
                List<Menu> menus = this.modelo.obtenerMenusDisponibles();
        
                CalculadoraCostos calc = new CalculadoraCostos();
                CalculadoraCostos.TipoUsuario tipoEnum = null;
                switch(rol) {
                    case "estudiante": 
                        tipoEnum = CalculadoraCostos.TipoUsuario.ESTUDIANTE; 
                        break;
                    case "profesor": 
                        tipoEnum = CalculadoraCostos.TipoUsuario.PROFESOR; 
                        break;
                    case "empleado": 
                        tipoEnum = CalculadoraCostos.TipoUsuario.EMPLEADO; 
                        break;
                }

                if(tipoEnum != null) {
                    for(Menu m : menus){
                        double precioFinal = calc.calcularTarifa(m.getCostoUnitario(), tipoEnum);
                        m.setCostoUnitario(precioFinal);
                    }
                }
                
                String fechaHoy = LocalDate.now().format(DateTimeFormatter.ofPattern("d/M/yyyy"));
                
                for(Menu m : menus){
                    if(this.modelo.reservaExiste(usuario, fechaHoy, m.getTipoComida(), m.getTipoPlato())){
                        m.setReservado(true);
                    }
                }
           
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

        String[] datos = vistaMenu.solicitarDatosRecarga();
        if (datos == null) return;

        String montoStr = datos[0];
    
        try {
            double monto = Double.parseDouble(montoStr);
            if (monto > 0) {
                boolean exito = this.modelo.recargarSaldo(this.usuarioActual, monto);
                if (exito) {
                    JOptionPane.showMessageDialog(vistaMenu, "Recarga exitosa.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    String nuevoSaldo = this.modelo.Saldo(this.usuarioActual);
                    vistaMenu.setMonedero(nuevoSaldo);
                } else {
                    JOptionPane.showMessageDialog(vistaMenu, "Error al recargar saldo.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(vistaMenu, "El monto debe ser positivo.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vistaMenu, "Monto inválido. Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
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

    private void mostrarReservas() {
        if (this.usuarioActual == null) return;
        
        List<String> reservas = this.modelo.obtenerReservasUsuario(this.usuarioActual);
        if (reservas == null || reservas.isEmpty()) {
            JOptionPane.showMessageDialog(vistaMenu, "No tienes reservas registradas.", "Mis Reservas", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (String r : reservas) {
            sb.append(r).append("\n");
        }
        
        javax.swing.JTextArea textArea = new javax.swing.JTextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 300));
        
        JOptionPane.showMessageDialog(vistaMenu, scrollPane, "Mis Reservas", JOptionPane.PLAIN_MESSAGE);
    }

    private void reservarMenu(ActionEvent e) {
        if (this.usuarioActual == null) return;
        
        Object source = e.getSource();
        if (source instanceof javax.swing.JButton) {
            javax.swing.JButton btn = (javax.swing.JButton) source;
            Menu m = (Menu) btn.getClientProperty("menu_data");
            
            if (m != null) {
                String fechaMenu = m.getFecha();
                String fechaHoy = LocalDate.now().format(DateTimeFormatter.ofPattern("d/M/yyyy"));
                
                if (this.modelo.reservaExiste(this.usuarioActual, fechaHoy, m.getTipoComida(), null)) {
                    JOptionPane.showMessageDialog(vistaMenu, 
                        "Ya has realizado una reserva para " + m.getTipoComida() + " en esta fecha.\nSolo se permite una reserva por comida al día.", 
                        "Límite de Reservas", 
                        JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                int confirm = JOptionPane.showConfirmDialog(vistaMenu, 
                    "¿Desea reservar " + m.getTipoPlato() + " por " + m.getCostoUnitario() + " Bs?",
                    "Confirmar Reserva",
                    JOptionPane.YES_NO_OPTION);
                    
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean exito = this.modelo.registrarReserva(
                        this.usuarioActual, 
                        fechaMenu,
                        m.getTipoComida(), 
                        m.getTipoPlato(),
                        m.getCostoUnitario()
                    );
                
                    if (exito) {
                        JOptionPane.showMessageDialog(vistaMenu, "Reserva realizada con exito");
                        m.setReservado(true);
                        btn.setEnabled(false);
                        btn.setBackground(java.awt.Color.GRAY);
                        btn.setText("Reservado");
                    } else {
                        JOptionPane.showMessageDialog(vistaMenu, "Error al guardar reserva.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }
}