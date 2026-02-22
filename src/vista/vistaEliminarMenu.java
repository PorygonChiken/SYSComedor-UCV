package vista;

import utils.menuUtils;
import modelo.Menu;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class vistaEliminarMenu extends JFrame {
    
    public JButton btnVolver;
    private JLabel lblSaludo;
    private JPanel panelDesayunosContenido;
    private JPanel panelAlmuerzosContenido;   
    private java.util.List<JButton> botonesEliminar = new java.util.ArrayList<>();
    private ActionListener controladorActual;

    public vistaEliminarMenu() {
        menuUtils.configurarFrame(this, "Gestión de Menús - Eliminar", 1000, 720, JFrame.EXIT_ON_CLOSE);

        JPanel panelPrincipal = menuUtils.crearPanelPrincipal();
        JPanel contenedor = crearPanel();
        JPanel header = crearHeader();
        JPanel body = crearBody();
        
        contenedor.add(header, BorderLayout.NORTH);
        contenedor.add(body, BorderLayout.CENTER);

        panelPrincipal.add(contenedor);
        add(panelPrincipal);
    }
    
    private JPanel crearPanel() {
        JPanel contenedor = new JPanel(new BorderLayout(0, 20));
        contenedor.setBackground(Color.WHITE);
        contenedor.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        contenedor.setPreferredSize(new Dimension(850, 500));
        return contenedor;
    }

    private JPanel crearHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        
        lblSaludo = new JLabel("Administrador"); 
        lblSaludo.setFont(new Font("ARIAL", Font.BOLD, 26));
        header.add(lblSaludo, BorderLayout.WEST);

        JPanel headerButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        headerButtons.setBackground(Color.WHITE);

        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("ARIAL", Font.BOLD, 14));
        btnVolver.setBackground(new Color(230, 230, 230));
        btnVolver.setFocusPainted(false);
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));

        headerButtons.add(btnVolver);
        
        header.add(headerButtons, BorderLayout.EAST);
        return header;
    }

   private JPanel crearBody() {

        JPanel panelMenu = new JPanel(new GridLayout(1, 2, 20, 0));
        panelMenu.setBackground(Color.WHITE);
        
        panelDesayunosContenido = new JPanel();
        panelDesayunosContenido.setLayout(new BoxLayout(panelDesayunosContenido, BoxLayout.Y_AXIS));
        panelDesayunosContenido.setBackground(Color.WHITE);
        
        panelAlmuerzosContenido = new JPanel();
        panelAlmuerzosContenido.setLayout(new BoxLayout(panelAlmuerzosContenido, BoxLayout.Y_AXIS));
        panelAlmuerzosContenido.setBackground(Color.WHITE);

        panelMenu.add(crearMenu("Desayunos", panelDesayunosContenido));
        panelMenu.add(crearMenu("Almuerzos", panelAlmuerzosContenido));

        JPanel finalBody = new JPanel(new BorderLayout());
        finalBody.setBackground(Color.WHITE);
        
        JPanel topSection = new JPanel();
        topSection.setLayout(new BoxLayout(topSection, BoxLayout.Y_AXIS));
        topSection.setBackground(Color.WHITE);
        
        JSeparator topLine = new JSeparator(SwingConstants.HORIZONTAL);
        topLine.setForeground(Color.BLACK);
        
        topSection.add(topLine);
        topSection.add(Box.createRigidArea(new Dimension(0, 15)));
        
        finalBody.add(topSection, BorderLayout.NORTH);
        finalBody.add(panelMenu, BorderLayout.CENTER);
        
        return finalBody;
    }

    private JPanel crearMenu(String title, JPanel contentPanel) {
        JPanel col = new JPanel(new BorderLayout());
        col.setBackground(Color.WHITE);

        JLabel titulo = new JLabel(title);
        titulo.setFont(new Font("ARIAL", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        col.add(titulo, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(contentPanel);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        
        col.add(scroll, BorderLayout.CENTER);
        return col;
    }

    public void setMenu(List<Menu> menus) {
        panelDesayunosContenido.removeAll();
        panelAlmuerzosContenido.removeAll();
        botonesEliminar.clear();
        
        for (Menu m : menus) {
            JPanel card = crearTarjeta(m);
            if (m.getTipoComida().equalsIgnoreCase("Desayuno")) {
                panelDesayunosContenido.add(card);
                panelDesayunosContenido.add(Box.createRigidArea(new Dimension(0, 5)));
            } else {
                panelAlmuerzosContenido.add(card);
                panelAlmuerzosContenido.add(Box.createRigidArea(new Dimension(0, 5)));
            }
        }
        
        panelDesayunosContenido.add(Box.createVerticalGlue());
        panelAlmuerzosContenido.add(Box.createVerticalGlue());

        if (controladorActual != null) {
            for (JButton btn : botonesEliminar) {
                boolean hasListener = false;
                for (ActionListener al : btn.getActionListeners()) {
                    if (al == controladorActual) {
                        hasListener = true;
                        break;
                    }
                }
                if (!hasListener) {
                    btn.addActionListener(controladorActual);
                }
            }
        }
        
        panelDesayunosContenido.revalidate();
        panelDesayunosContenido.repaint();
        panelAlmuerzosContenido.revalidate();
        panelAlmuerzosContenido.repaint();
    }
    
    private JPanel crearTarjeta(Menu m) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(245, 245, 245));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));

        JTextArea txtPlato = new JTextArea(m.getTipoPlato());
        txtPlato.setFont(new Font("ARIAL", Font.BOLD, 14));
        txtPlato.setLineWrap(true);
        txtPlato.setWrapStyleWord(true);
        txtPlato.setEditable(false);
        txtPlato.setFocusable(false);
        txtPlato.setOpaque(false);
        txtPlato.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel lblPrecio = new JLabel(m.getCostoUnitario() + " Bs");
        lblPrecio.setFont(new Font("ARIAL", Font.PLAIN, 12));
        lblPrecio.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("ARIAL", Font.BOLD, 11));
        btnEliminar.setBackground(new Color(220, 53, 69)); 
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminar.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        btnEliminar.putClientProperty("menu_data", m);
        btnEliminar.setActionCommand("eliminar_menu");

        botonesEliminar.add(btnEliminar);

        card.add(txtPlato);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(lblPrecio);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(btnEliminar);
        
        return card;
    }

    public void setControlador(ActionListener ac) {
        this.controladorActual = ac;
        
        btnVolver.addActionListener(ac);
        btnVolver.setActionCommand("volver_dashboard");
        
        for (JButton btn : botonesEliminar) {
            btn.addActionListener(ac);
        }
    }
}