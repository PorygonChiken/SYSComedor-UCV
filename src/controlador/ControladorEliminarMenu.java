package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import modelo.Menu;
import modelo.modelo;
import vista.vistaEliminarMenu;
import modelo.ModeloEliminarMenu;
import modelo.CalculadoraCostos; 

public class ControladorEliminarMenu implements ActionListener {
    
    private vistaEliminarMenu vistaEliminar;
    private modelo modelo;

    public ControladorEliminarMenu() {
        this.vistaEliminar = new vistaEliminarMenu();
        this.modelo = new modelo();
        
        this.vistaEliminar.setControlador(this);
        
        cargarMenus();
        this.vistaEliminar.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "volver_dashboard":
                volverAlDashboard();
                break;
            case "eliminar_menu":
                eliminarMenuAccion(e);
                break;
            case "editar_menu":
                editarMenuAccion(e);
                break;
        }
    }

    private void cargarMenus() {
        List<Menu> menus = this.modelo.obtenerMenusDisponibles();
        this.vistaEliminar.setMenu(menus);
    }

    private void eliminarMenuAccion(ActionEvent e) {
        if (!(e.getSource() instanceof JButton)) return;
        
        Menu m = (Menu) ((JButton) e.getSource()).getClientProperty("menu_data");
        if (m == null) return; 
        int confirm = JOptionPane.showConfirmDialog(vistaEliminar, 
            "¿Eliminar el menú: " + m.getTipoPlato() + "?\nSe borrarán sus reservas.",
            "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
        if (confirm != JOptionPane.YES_OPTION) return; 
        ModeloEliminarMenu modeloEliminar = new ModeloEliminarMenu();
        
        if (modeloEliminar.eliminarMenu(m.getFecha(), m.getTipoComida(), m.getTipoPlato())) {
            modeloEliminar.eliminarReservasPorMenu(m.getTipoComida(), m.getTipoPlato());
            JOptionPane.showMessageDialog(vistaEliminar, "Menú eliminado con éxito.");
            cargarMenus(); 
        } else {
            JOptionPane.showMessageDialog(vistaEliminar, "Error al eliminar menú.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarMenuAccion(ActionEvent e) {
        if (!(e.getSource() instanceof JButton)) return;
        Menu m = (Menu) ((JButton) e.getSource()).getClientProperty("menu_data");
        if (m == null) return;

        String[] opciones = {"Nombre del Plato", "Número de Raciones", "Precio (Costo CCB)"};
        String seleccion = (String) JOptionPane.showInputDialog(
            vistaEliminar, "¿Qué parámetro del menú deseas editar?", "Editar Menú",
            JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]
        );

        if (seleccion == null) return; 

        String nuevoNombre = null;
        int nuevasRaciones = -1;
        double nuevoCosto = -1.0;

        if (seleccion.equals("Nombre del Plato")) {
            nuevoNombre = JOptionPane.showInputDialog(vistaEliminar, "Ingrese el nuevo nombre del plato:", m.getTipoPlato());
            if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) return;

        } else if (seleccion.equals("Número de Raciones")) {
            String input = JOptionPane.showInputDialog(vistaEliminar, "Ingrese la nueva cantidad de raciones:", m.getCantidadRaciones());
            if (input == null || input.trim().isEmpty()) return;
            try {
                nuevasRaciones = Integer.parseInt(input);
                if(nuevasRaciones < 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaEliminar, "Por favor ingrese un número entero válido mayor o igual a 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

        } else if (seleccion.equals("Precio (Costo CCB)")) {
            javax.swing.JPanel panelCostos = new javax.swing.JPanel(new java.awt.GridLayout(0, 1, 0, 5));
            javax.swing.JTextField txtCostoFijo = new javax.swing.JTextField();
            javax.swing.JTextField txtCostoVar = new javax.swing.JTextField();
            javax.swing.JTextField txtMerma = new javax.swing.JTextField();

            panelCostos.add(new javax.swing.JLabel("Costo Fijo (Bs):"));
            panelCostos.add(txtCostoFijo);
            panelCostos.add(new javax.swing.JLabel("Costo Variable (Bs):"));
            panelCostos.add(txtCostoVar);
            panelCostos.add(new javax.swing.JLabel("% Merma (Ej. 10):"));
            panelCostos.add(txtMerma);

            int result = JOptionPane.showConfirmDialog(vistaEliminar, panelCostos, "Calcular Nuevo CCB", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (result == JOptionPane.OK_OPTION) {
                try {
                    double costoFijo = Double.parseDouble(txtCostoFijo.getText());
                    double costoVar = Double.parseDouble(txtCostoVar.getText());
                    double mermaPorcentaje = Double.parseDouble(txtMerma.getText());
        
                    CalculadoraCostos calc = new CalculadoraCostos();
                    double mermaDecimal = mermaPorcentaje / 100.0; 
                    nuevoCosto = calc.calcularCCB(costoFijo, costoVar, m.getCantidadRaciones(), mermaDecimal);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vistaEliminar, "Solo se permiten valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(vistaEliminar, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                return; 
            }
        }

        ModeloEliminarMenu modeloEliminar = new ModeloEliminarMenu();
        boolean exito = modeloEliminar.editarMenu(m.getFecha(), m.getTipoComida(), m.getTipoPlato(), nuevoNombre, nuevasRaciones, nuevoCosto);

        if (exito) {
            if (seleccion.equals("Nombre del Plato") && nuevoNombre != null) {
                modeloEliminar.actualizarNombreReserva(m.getFecha(), m.getTipoComida(), m.getTipoPlato(), nuevoNombre);
            }
            
            JOptionPane.showMessageDialog(vistaEliminar, "El menú ha sido actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cargarMenus(); 
        } else {
            JOptionPane.showMessageDialog(vistaEliminar, "Ocurrió un error al intentar editar el menú.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volverAlDashboard() {
        this.vistaEliminar.dispose();
        new ControladorDashboard(); 
    }
}