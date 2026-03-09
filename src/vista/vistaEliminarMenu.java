package vista;

import utils.menuUtils;
import modelo.Menu;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class vistaEliminarMenu extends JFrame {
    
    public JButton btnGestionarMenus;
    public JButton btnCerrarSesion;
    public JButton btnRegistrarBeneficio;
    private JLabel lblSaludo;
    private JPanel panelDesayunosContenido;
    private JPanel panelAlmuerzosContenido;   
    private java.util.List<JButton> botonesEliminar = new java.util.ArrayList<>();
    private java.util.List<JButton> botonesEditar = new java.util.ArrayList<>();
    private ActionListener controladorActual;

    public vistaEliminarMenu() {
        menuUtils.configurarFrame(this, "Gestión de Menús - Administrador", 1000, 720, JFrame.EXIT_ON_CLOSE);

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
        JPanel contenedor = new JPanel(new BorderLayout(0, 20)) {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
            }
        };
        contenedor.setOpaque(false);
        contenedor.setBackground(menuUtils.BLANCO_TRANSLUCIDO);
        contenedor.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        contenedor.setPreferredSize(new Dimension(850, 500));
        return contenedor;
    }

    private JPanel crearHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        
        lblSaludo = new JLabel("Administrador"); 
        lblSaludo.setFont(new Font("ARIAL", Font.BOLD, 26));
        header.add(lblSaludo, BorderLayout.WEST);

        JPanel headerButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        headerButtons.setOpaque(false);

        btnRegistrarBeneficio = menuUtils.crearBoton("Registrar Beneficio");
        btnRegistrarBeneficio.setFont(new Font("ARIAL", Font.BOLD, 14));
        btnRegistrarBeneficio.setBackground(new Color(230, 230, 230)); 

        btnGestionarMenus = menuUtils.crearBoton("Gestionar Menús");
        btnGestionarMenus.setFont(new Font("ARIAL", Font.BOLD, 14));
        btnGestionarMenus.setBackground(new Color(230, 230, 230)); 

        btnCerrarSesion = menuUtils.crearBoton("Cerrar Sesión");
        btnCerrarSesion.setFont(new Font("ARIAL", Font.BOLD, 14));
        btnCerrarSesion.setBackground(new Color(255, 100, 100)); 

        headerButtons.add(btnRegistrarBeneficio);
        headerButtons.add(btnGestionarMenus);
        headerButtons.add(btnCerrarSesion);
        
        header.add(headerButtons, BorderLayout.EAST);
        return header;
    }

   private JPanel crearBody() {
        JPanel panelMenu = new JPanel(new GridLayout(1, 2, 20, 0));
        panelMenu.setOpaque(false);
        
        panelDesayunosContenido = new JPanel();
        panelDesayunosContenido.setLayout(new BoxLayout(panelDesayunosContenido, BoxLayout.Y_AXIS));
        panelDesayunosContenido.setOpaque(false);
        
        panelAlmuerzosContenido = new JPanel();
        panelAlmuerzosContenido.setLayout(new BoxLayout(panelAlmuerzosContenido, BoxLayout.Y_AXIS));
        panelAlmuerzosContenido.setOpaque(false);

        panelMenu.add(crearMenu("Desayunos", panelDesayunosContenido));
        panelMenu.add(crearMenu("Almuerzos", panelAlmuerzosContenido));

        JPanel finalBody = new JPanel(new BorderLayout());
        finalBody.setOpaque(false);
        
        JPanel topSection = new JPanel();
        topSection.setLayout(new BoxLayout(topSection, BoxLayout.Y_AXIS));
        topSection.setOpaque(false);
        
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
        col.setOpaque(false);

        JLabel titulo = new JLabel(title);
        titulo.setFont(new Font("ARIAL", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        col.add(titulo, BorderLayout.NORTH);

        JScrollPane scroll = new JScrollPane(contentPanel);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
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
        botonesEditar.clear();
        
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
            
            for (JButton btn : botonesEditar) {
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
        JPanel card = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
                super.paintComponent(g);
            }
        };
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(255, 255, 255, 150));
        card.setOpaque(false);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5),
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

        JButton btnEditar = menuUtils.crearBoton("Editar");
        btnEditar.setFont(new Font("ARIAL", Font.BOLD, 11));
        btnEditar.setBackground(new Color(100, 200, 100)); 
        btnEditar.setForeground(Color.WHITE); 
        btnEditar.putClientProperty("menu_data", m);
        btnEditar.setActionCommand("editar_menu");
        botonesEditar.add(btnEditar);

        JButton btnEliminar = menuUtils.crearBoton("Eliminar");
        btnEliminar.setFont(new Font("ARIAL", Font.BOLD, 11));
        btnEliminar.setBackground(new Color(255, 100, 100)); 
        btnEliminar.setForeground(Color.BLACK); 
        btnEliminar.putClientProperty("menu_data", m);
        btnEliminar.setActionCommand("eliminar_menu");
        botonesEliminar.add(btnEliminar);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.X_AXIS));
        panelBotones.setOpaque(false);
        panelBotones.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelBotones.add(btnEditar);
        panelBotones.add(Box.createRigidArea(new Dimension(10, 0)));
        panelBotones.add(btnEliminar);

        card.add(txtPlato);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(lblPrecio);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(panelBotones);
        
        return card;
    }

    public void setControlador(ActionListener ac) {
        this.controladorActual = ac;
        
        btnGestionarMenus.addActionListener(ac);
        btnGestionarMenus.setActionCommand("gestionar_menus");
        
        btnCerrarSesion.addActionListener(ac);
        btnCerrarSesion.setActionCommand("cerrar_sesion");

        btnRegistrarBeneficio.addActionListener(ac);
        btnRegistrarBeneficio.setActionCommand("registrar_beneficio");
        
        for (JButton btn : botonesEliminar) {
            btn.addActionListener(ac);
        }
        
        for (JButton btn : botonesEditar) {
            btn.addActionListener(ac);
        }
    }
}