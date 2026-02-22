package vista;

import utils.menuUtils;
import modelo.Menu;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class vistaMenuUsuario extends JFrame {
    
    public JButton btnSalir;
    public JButton btnReservas;
    public JButton btnRecargar;
    private JLabel lblSaludo;
    private JPanel panelDesayunosContenido;
    private JPanel panelAlmuerzosContenido;   
    private JLabel lblTextoMonedero; 
    private java.util.List<JButton> botonesReserva = new java.util.ArrayList<>();
    private ActionListener controladorActual;

    public vistaMenuUsuario() {
        menuUtils.configurarFrame(this, "Menú Principal", 1200, 720, JFrame.EXIT_ON_CLOSE);

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
        contenedor.setPreferredSize(new Dimension(900, 500));
        return contenedor;
    }

    private JPanel crearHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        
        lblSaludo = new JLabel("Buenos días usuario");
        lblSaludo.setFont(new Font("ARIAL", Font.BOLD, 26));
        header.add(lblSaludo, BorderLayout.WEST);

        JPanel headerButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        headerButtons.setOpaque(false);

        btnReservas = menuUtils.crearBoton("Mis Reservas");
        btnReservas.setFont(new Font("ARIAL", Font.BOLD, 14));
        btnReservas.setBackground(new Color(230, 230, 230));

        btnSalir = menuUtils.crearBoton("Cerrar sesión");
        btnSalir.setFont(new Font("ARIAL", Font.BOLD, 14));
        btnSalir.setBackground(new Color(255, 100, 100));

        headerButtons.add(btnReservas);
        headerButtons.add(btnSalir);
        
        header.add(headerButtons, BorderLayout.EAST);
        return header;
    }

    private JPanel crearBody() {
        JPanel bodyContent = new JPanel();
        bodyContent.setOpaque(false);
        bodyContent.setLayout(new BoxLayout(bodyContent, BoxLayout.X_AXIS));

        //menu
        JPanel panelMenu = new JPanel(new GridLayout(1, 2, 10, 0));
        panelMenu.setOpaque(false);
        panelMenu.setPreferredSize(new Dimension(560, 380));
        
        panelDesayunosContenido = new JPanel();
        panelDesayunosContenido.setLayout(new BoxLayout(panelDesayunosContenido, BoxLayout.Y_AXIS));
        panelDesayunosContenido.setOpaque(false);
        
        panelAlmuerzosContenido = new JPanel();
        panelAlmuerzosContenido.setLayout(new BoxLayout(panelAlmuerzosContenido, BoxLayout.Y_AXIS));
        panelAlmuerzosContenido.setOpaque(false);

        panelMenu.add(crearMenu("Desayuno", panelDesayunosContenido));
        panelMenu.add(crearMenu("Almuerzo", panelAlmuerzosContenido));

        //separador
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setForeground(Color.GRAY);
        separator.setMaximumSize(new Dimension(2, Integer.MAX_VALUE));

        //monedero
        JPanel monederoWrapper = crearPanelMonedero();

        bodyContent.add(panelMenu);
        bodyContent.add(Box.createHorizontalGlue());
        bodyContent.add(Box.createRigidArea(new Dimension(20, 0)));
        bodyContent.add(separator);
        bodyContent.add(Box.createRigidArea(new Dimension(20, 0)));
        bodyContent.add(monederoWrapper);

        JPanel finalBody = new JPanel();
        finalBody.setOpaque(false);
        finalBody.setLayout(new BoxLayout(finalBody, BoxLayout.Y_AXIS));
        
        JSeparator topLine = new JSeparator(SwingConstants.HORIZONTAL);
        topLine.setForeground(Color.BLACK);
        
        finalBody.add(topLine);
        finalBody.add(Box.createRigidArea(new Dimension(0, 15)));
        finalBody.add(bodyContent);
        
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

    private JPanel crearPanelMonedero() {
        JPanel panelMonedero = new JPanel();
        panelMonedero.setOpaque(false);
        panelMonedero.setLayout(new BoxLayout(panelMonedero, BoxLayout.Y_AXIS));
        
        JLabel tituloMonedero = new JLabel("Monedero");
        tituloMonedero.setFont(new Font("ARIAL", Font.BOLD, 18));
        tituloMonedero.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblTextoMonedero = new JLabel("S");
        lblTextoMonedero.setFont(new Font("ARIAL", Font.PLAIN, 14));
        lblTextoMonedero.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnRecargar = menuUtils.crearBoton("Recargar");
        btnRecargar.setFont(new Font("ARIAL", Font.BOLD, 14));
        btnRecargar.setBackground(new Color(230, 230, 230));
        btnRecargar.setMaximumSize(new Dimension(150, 40));
        
        panelMonedero.add(tituloMonedero);
        panelMonedero.add(Box.createRigidArea(new Dimension(0, 100)));
        panelMonedero.add(lblTextoMonedero);
        panelMonedero.add(Box.createRigidArea(new Dimension(0, 140)));
        panelMonedero.add(btnRecargar);
        panelMonedero.add(Box.createVerticalGlue());
        
        JPanel monederoWrapper = new JPanel(new BorderLayout());
        monederoWrapper.setOpaque(false);
        monederoWrapper.setPreferredSize(new Dimension(240, 380));
        monederoWrapper.add(panelMonedero, BorderLayout.CENTER);
        
        return monederoWrapper;
    }

    public void setUsuario(String usuario) {
        lblSaludo.setText("Bienvenido, " + usuario);
    }
    
    public void setMenu(List<Menu> menus) {
        panelDesayunosContenido.removeAll();
        panelAlmuerzosContenido.removeAll();
        botonesReserva.clear();
        
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
        
        if (controladorActual != null) {
            for (JButton btn : botonesReserva) {
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
        
        boolean disponible = m.getCantidadRaciones() > 0;
        String textoEstado = disponible ? "Disponible" : "Agotado";
        Color colorEstado = disponible ? new Color(0, 128, 0) : Color.RED;
        
        JLabel lblEstado = new JLabel(textoEstado);
        lblEstado.setFont(new Font("ARIAL", Font.BOLD | Font.ITALIC, 11));
        lblEstado.setForeground(colorEstado);
        lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnReservar = menuUtils.crearBoton("Reservar");
        btnReservar.setFont(new Font("ARIAL", Font.BOLD, 11));
        btnReservar.setBackground(new Color(100, 200, 100)); // Green
        btnReservar.setForeground(Color.WHITE);
        btnReservar.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        if (!disponible) {
            btnReservar.setEnabled(false);
            btnReservar.setBackground(Color.GRAY);
        }

        if (m.isReservado()) {
            btnReservar.setEnabled(false);
            btnReservar.setBackground(Color.GRAY);
            btnReservar.setText("Reservado");
        }

        btnReservar.putClientProperty("menu_data", m);

        btnReservar.setActionCommand("reservar_menu");

        botonesReserva.add(btnReservar);

        card.add(txtPlato);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(lblPrecio);
        card.add(lblEstado);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(btnReservar);
        
        return card;
    }

    public String[] solicitarDatosRecarga() {
        javax.swing.JPanel panel = new javax.swing.JPanel(new java.awt.GridLayout(0, 1));
        javax.swing.JTextField montoField = new javax.swing.JTextField();
        javax.swing.JTextField refField = new javax.swing.JTextField();

        panel.add(new javax.swing.JLabel("Monto a recargar:"));
        panel.add(montoField);
        panel.add(new javax.swing.JLabel("Ultimos 4 digitos de la referencia:"));
        panel.add(refField);

        while (true) {
            int result = JOptionPane.showConfirmDialog(this, panel, "Recargar Saldo",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (result != JOptionPane.OK_OPTION) {
                return null;
            }

            String montoStr = montoField.getText();
            String refStr = refField.getText();

            if (montoStr.trim().isEmpty() || refStr.trim().isEmpty()) {
                 JOptionPane.showMessageDialog(this, "Rellena todos los campos", "Error", JOptionPane.WARNING_MESSAGE);
                 continue;
            }

            if (!refStr.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(this, "Solo 4 dígitos", "Error", JOptionPane.WARNING_MESSAGE);
                continue;
            }

            return new String[] {montoStr, refStr};
        }
    }


    public void setMonedero(String saldo) {
        lblTextoMonedero.setText(saldo + " Bs");
        lblTextoMonedero.setFont(new Font("ARIAL", Font.BOLD, 42));
    }

    public void setControlador(ActionListener ac) {
        this.controladorActual = ac;
        
        btnRecargar.addActionListener(ac);
        btnRecargar.setActionCommand("recargar");
        btnReservas.addActionListener(ac);
        btnReservas.setActionCommand("ver_reservas");
        btnSalir.addActionListener(ac);
        btnSalir.setActionCommand("logout");
        

        for (JButton btn : botonesReserva) {
            btn.addActionListener(ac);
        }
    }
}