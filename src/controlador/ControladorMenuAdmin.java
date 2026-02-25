package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.modelo;
import modelo.ModeloMenuTXT;
import vista.VistaMenuAdmin;

public class ControladorMenuAdmin implements ActionListener {
    private VistaMenuAdmin vista;
    private ModeloMenuTXT modelo;
    private modelo modeloLectura;

    public ControladorMenuAdmin() {
        this.vista = new VistaMenuAdmin();
        this.modelo = new ModeloMenuTXT();
        this.modeloLectura = new modelo();
        this.vista.setControlador(this);
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "GUARDAR":
                guardarMenuConCalculo();
                break;
            case "SALIR":
                salirdelmenu();
                break;
        }
    }

    private void guardarMenuConCalculo() {
        String fecha = vista.getFecha();
        String tipoComida = vista.getTipoComida();
        String tipo = vista.getTipoPlato();
        String cantStr = vista.getCantidad();
        String cfStr = vista.getCostoFijo();
        String cvStr = vista.getCostoVariable();
        String mermaStr = vista.getMerma();

        if(fecha.isEmpty() || tipo.isEmpty() || cantStr.isEmpty() || cfStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor llene todos los campos.");
            return;
        }

        try {
            double ccb = modelo.procesarYGuardarMenu(fecha, tipoComida, tipo, cantStr, cfStr, cvStr, mermaStr);

            JOptionPane.showMessageDialog(vista, "Guardado.\nCosto Bandeja (CCB): " + String.format("%.2f", ccb) + " Bs.");
            
            vista.limpiarCampos();
            mostrarMenus(); 

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Error numérico: Verifique que costos y cantidades sean números válidos.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error inesperado: " + ex.getMessage());
        }
    }

    private void mostrarMenus() {
        String listado = modeloLectura.Menu();
        vista.setAreaReporte(listado);
    }

    private void salirdelmenu() {
        vista.dispose(); 
        new ControladorDashboard();   
    }
}