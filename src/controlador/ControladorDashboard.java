package controlador;

import vista.VistaAdminDashboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorDashboard implements ActionListener {

    private VistaAdminDashboard vista;

    public ControladorDashboard() {
        this.vista = new VistaAdminDashboard(); 
        
        this.vista.btnGestionarMenus.addActionListener(this);
        this.vista.btnVerificacion.addActionListener(this);
        this.vista.btnVerMenus.addActionListener(this); 
        this.vista.btnCerrarSesion.addActionListener(this);
     
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnGestionarMenus) {
            vista.dispose(); 
            new ControladorMenuAdmin(); 
        } 
        else if (e.getSource() == vista.btnVerificacion) { 
            vista.dispose();
            new ControladorVerificacionFacialAdmin(); 
        }
        else if (e.getSource() == vista.btnVerMenus) {
            vista.dispose();
            new ControladorEliminarMenu(); 
        }
        else if (e.getSource() == vista.btnCerrarSesion) {
            vista.dispose();
            new controlador(); 
        }
    }
}