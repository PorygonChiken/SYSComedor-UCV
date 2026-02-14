package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.modelo;
import modelo.CalculadoraCostos;
import modelo.Menu;
import modelo.ModeloMenuTXT;
import vista.VistaMenuAdmin;

public class ControladorMenuAdmin implements ActionListener {
    private VistaMenuAdmin vista;
    private ModeloMenuTXT modelo;
    private modelo modeloLectura;
    private CalculadoraCostos calculadora; 

    public ControladorMenuAdmin() {
        this.vista = new VistaMenuAdmin();
        this.modelo = new ModeloMenuTXT();
        this.modeloLectura = new modelo();
        this.calculadora = new CalculadoraCostos(); 
        this.vista.setControlador(this);
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "GUARDAR":
                guardarMenuConCalculo();
                break;
            case "VER":
                mostrarMenus();
                break;
            case "SALIR":
                salirdelmenu();
                break;
        }
    }

    private void guardarMenuConCalculo() {
        try {
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

            int raciones = Integer.parseInt(cantStr); 
            double cf = Double.parseDouble(cfStr);
            double cv = Double.parseDouble(cvStr);
            double mermaEntera = Double.parseDouble(mermaStr); 
            double mermaDecimal = mermaEntera / 100.0;
            double ccb = calculadora.calcularCCB(cf, cv, raciones, mermaDecimal);

            Menu nuevoMenu = new Menu(fecha,tipoComida, tipo, raciones, ccb);
            modelo.guardarMenu(nuevoMenu);
            
            JOptionPane.showMessageDialog(vista, "Calculado y Guardado.\nCosto Bandeja (CCB): " + String.format("%.2f", ccb) + " Bs.");
            
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