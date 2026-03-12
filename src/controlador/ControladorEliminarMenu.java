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
            case "gestionar_menus":
                abrirGestionarMenus();
                break;
            case "cerrar_sesion":
                cerrarSesion();
                break;
            case "registrar_beneficio":
                registrarBeneficioAccion();
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

    private void abrirGestionarMenus() {
        this.vistaEliminar.dispose();
        new ControladorMenuAdmin(); 
    }

    private void cerrarSesion() {
        this.vistaEliminar.dispose();
        new controlador(); 
    }

    private void registrarBeneficioAccion() {
        String ci = JOptionPane.showInputDialog(vistaEliminar, "Ingrese la Cédula del Estudiante:");
        if (ci == null || ci.trim().isEmpty()) return;

        if (!modelo.existeUsuarioPorCedula(ci)) {
            JOptionPane.showMessageDialog(vistaEliminar, "La cédula ingresada no se encuentra registrada en el sistema de usuarios.", "Cédula no encontrada", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String rol = modelo.obtenerRol(ci);
        if (rol == null || !rol.equalsIgnoreCase("estudiante")) {
            JOptionPane.showMessageDialog(vistaEliminar, "Acción denegada. La cédula ingresada pertenece a un '" + (rol != null ? rol : "Desconocido") + "', no a un estudiante.", "Rol Inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] opciones = {"Estudiante Becario", "Estudiante Exonerado"};
        int seleccion = JOptionPane.showOptionDialog(vistaEliminar, 
            "Estudiante verificado.\nSeleccione el tipo de beneficio a otorgar:", 
            "Asignar Beneficio",
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.QUESTION_MESSAGE, 
            null, opciones, opciones[0]);

        if (seleccion == -1) return; 

        String tipoBeneficio = opciones[seleccion];
        double descuentoAdicional = 0.0;

        if (seleccion == 0) { 
            String inputDesc = JOptionPane.showInputDialog(vistaEliminar, "Ingrese el porcentaje de descuento extra (Ej: 50 para 50%):");
            if (inputDesc == null || inputDesc.trim().isEmpty()) return;
            
            try {
                descuentoAdicional = Double.parseDouble(inputDesc);
                if (descuentoAdicional < 0 || descuentoAdicional > 100) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vistaEliminar, "Porcentaje inválido. Debe ser un número entre 0 y 100.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else { 
            descuentoAdicional = 100.0; 
        }

        boolean exito = modelo.registrarBeneficio(ci, tipoBeneficio, descuentoAdicional);
        
        if (exito) {
            JOptionPane.showMessageDialog(vistaEliminar, "El beneficio de " + tipoBeneficio + " ha sido registrado exitosamente para la CI: " + ci, "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vistaEliminar, "Ocurrió un error al intentar guardar el beneficio.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}