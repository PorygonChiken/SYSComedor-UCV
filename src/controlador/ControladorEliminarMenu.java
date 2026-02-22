package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import modelo.Menu;
import modelo.modelo;
import modelo.ModeloMenuTXT; 
import vista.vistaEliminarMenu;

public class ControladorEliminarMenu implements ActionListener {
    
    private vistaEliminarMenu vistaEliminar;
    private modelo modelo;
    private ModeloMenuTXT modeloMenuTxt;

    public ControladorEliminarMenu() {
        this.vistaEliminar = new vistaEliminarMenu();
        this.modelo = new modelo();
        this.modeloMenuTxt = new ModeloMenuTXT();
        
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
        }
    }

    private void cargarMenus() {
        List<Menu> menus = this.modelo.obtenerMenusDisponibles();
        this.vistaEliminar.setMenu(menus);
    }

    private void eliminarMenuAccion(ActionEvent e) {
        Object source = e.getSource();
        
        if (source instanceof JButton) {
            JButton btn = (JButton) source;
            Menu m = (Menu) btn.getClientProperty("menu_data");
            
            if (m != null) {
                int confirm = JOptionPane.showConfirmDialog(vistaEliminar, 
                    "¿Está seguro que desea eliminar el menú: " + m.getTipoPlato() + "?\nEsta acción no se puede deshacer.",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                    
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean exito = this.modeloMenuTxt.eliminarMenu(m.getFecha(), m.getTipoComida(), m.getTipoPlato()); 
                    
                    if (exito) {
                        JOptionPane.showMessageDialog(vistaEliminar, "Menú eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        cargarMenus(); 
                    } else {
                        JOptionPane.showMessageDialog(vistaEliminar, "No se encontró el menú para eliminar o hubo un error.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    private void volverAlDashboard() {
        this.vistaEliminar.dispose();
        new ControladorDashboard(); 
    }
}