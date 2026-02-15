package controlador;

import vista.VistaVerificacionFacialAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ControladorVerificacionFacialAdmin implements ActionListener {

    private VistaVerificacionFacialAdmin vista;

    public ControladorVerificacionFacialAdmin() {
        this.vista = new VistaVerificacionFacialAdmin();
        this.vista.setControlador(this);   
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "SUBIR_IMAGEN":
                procesarSubidaImagen();
                break;
                
            case "VERIFICAR":
                procesarVerificacion();
                break;
                
            case "VOLVER":
                volverAlDashboard();
                break;
        }
    }

    private void procesarSubidaImagen() {
        JOptionPane.showMessageDialog(vista, "Funcionalidad de subir imagen pendiente.");
    }

    private void procesarVerificacion() {
        JOptionPane.showMessageDialog(vista, "Funcionalidad de verificación pendiente.");
    }

    private void volverAlDashboard() {
        vista.dispose();
        new ControladorDashboard();
    }
}