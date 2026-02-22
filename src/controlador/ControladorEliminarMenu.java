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
            JOptionPane.showMessageDialog(vistaEliminar, "Menú eliminados con éxito.");
            cargarMenus(); 
        } else {
            JOptionPane.showMessageDialog(vistaEliminar, "Error al eliminar menú.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void volverAlDashboard() {
        this.vistaEliminar.dispose();
        new ControladorDashboard(); 
    }
}