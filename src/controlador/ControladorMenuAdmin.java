package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import modelo.CalculadoraCostos;
import modelo.Menu;
import modelo.ModeloMenuTXT;
import vista.VistaMenuAdmin;

public class ControladorMenuAdmin implements ActionListener {
    private VistaMenuAdmin vista;
    private ModeloMenuTXT modelo;
    private CalculadoraCostos calculadora; // Instancia de tu calculadora

    public ControladorMenuAdmin() {
        this.vista = new VistaMenuAdmin();
        this.modelo = new ModeloMenuTXT();
        this.calculadora = new CalculadoraCostos(); // Inicializamos
        
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
                System.exit(0);
                break;
        }
    }

    private void guardarMenuConCalculo() {
        try {
            // 1. Obtener Datos Básicos
            String fecha = vista.getFecha();
            String tipo = vista.getTipoPlato();
            String cantStr = vista.getCantidad();
            
            // 2. Obtener Datos Financieros
            String cfStr = vista.getCostoFijo();
            String cvStr = vista.getCostoVariable();
            String mermaStr = vista.getMerma();

            // 3. Validaciones
            if(fecha.isEmpty() || tipo.isEmpty() || cantStr.isEmpty() || cfStr.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Por favor llene todos los campos.");
                return;
            }

            // 4. Conversión de números
            int raciones = Integer.parseInt(cantStr); // nb (Número de bandejas)
            double cf = Double.parseDouble(cfStr);
            double cv = Double.parseDouble(cvStr);
            double mermaEntera = Double.parseDouble(mermaStr); // El usuario mete 10 para 10%
            
            // 5. USAR TU CALCULADORA (Lógica del archivo CalculadoraCostos.java)
            double mermaDecimal = mermaEntera / 100.0;
            double ccb = calculadora.calcularCCB(cf, cv, raciones, mermaDecimal);

            // 6. Crear Objeto Menu con el precio calculado
            Menu nuevoMenu = new Menu(fecha, tipo, raciones, ccb);
            
            // 7. Guardar
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
        String listado = modelo.leerMenus();
        vista.setAreaReporte(listado);
    }
}