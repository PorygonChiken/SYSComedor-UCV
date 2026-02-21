package vista;

import utils.menuUtils;
import modelo.Menu;
import java.util.List;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class vistaMenuUsuario extends JFrame {
    
    public JButton btnSalir;
    public JButton btnRecargar;
    private JLabel lblSaludo;
    private JPanel panelDesayunosContenido;
    private JPanel panelAlmuerzosContenido;   
    private JLabel lblTextoMonedero; 

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
        JPanel contenedor = new JPanel(new BorderLayout(0, 20));
        contenedor.setBackground(Color.WHITE);
        contenedor.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        contenedor.setPreferredSize(new Dimension(900, 500));
        return contenedor;
    }

    private JPanel crearHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        
        lblSaludo = new JLabel("Buenos días usuario");
        lblSaludo.setFont(new Font("ARIAL", Font.BOLD, 26));
        header.add(lblSaludo, BorderLayout.WEST);

        btnSalir = new JButton("Cerrar sesión");
        btnSalir.setFont(new Font("ARIAL", Font.BOLD, 14));
        btnSalir.setBackground(new Color(230, 230, 230));
        btnSalir.setFocusPainted(false);
        btnSalir.setCursor(new Cursor(Cursor.HAND_CURSOR));
        header.add(btnSalir, BorderLayout.EAST);
        return header;
    }

    private JPanel crearBody() {
        JPanel bodyContent = new JPanel();
        bodyContent.setBackground(Color.WHITE);
        bodyContent.setLayout(new BoxLayout(bodyContent, BoxLayout.X_AXIS));

        //menu
        JPanel panelMenu = new JPanel(new GridLayout(1, 2, 10, 0));
        panelMenu.setBackground(Color.WHITE);
        panelMenu.setPreferredSize(new Dimension(560, 380));
        
        panelDesayunosContenido = new JPanel();
        panelDesayunosContenido.setLayout(new BoxLayout(panelDesayunosContenido, BoxLayout.Y_AXIS));
        panelDesayunosContenido.setBackground(Color.WHITE);
        
        panelAlmuerzosContenido = new JPanel();
        panelAlmuerzosContenido.setLayout(new BoxLayout(panelAlmuerzosContenido, BoxLayout.Y_AXIS));
        panelAlmuerzosContenido.setBackground(Color.WHITE);

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
        finalBody.setBackground(Color.WHITE);
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

    private JPanel crearPanelMonedero() {
        JPanel panelMonedero = new JPanel();
        panelMonedero.setBackground(Color.WHITE);
        panelMonedero.setLayout(new BoxLayout(panelMonedero, BoxLayout.Y_AXIS));
        
        JLabel tituloMonedero = new JLabel("Monedero");
        tituloMonedero.setFont(new Font("ARIAL", Font.BOLD, 18));
        tituloMonedero.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        lblTextoMonedero = new JLabel("S");
        lblTextoMonedero.setFont(new Font("ARIAL", Font.PLAIN, 14));
        lblTextoMonedero.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnRecargar = new JButton("Recargar");
        btnRecargar.setFont(new Font("ARIAL", Font.BOLD, 14));
        btnRecargar.setBackground(new Color(230, 230, 230));
        btnRecargar.setFocusPainted(false);
        btnRecargar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnRecargar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRecargar.setMaximumSize(new Dimension(150, 40));
        
        panelMonedero.add(tituloMonedero);
        panelMonedero.add(Box.createRigidArea(new Dimension(0, 100)));
        panelMonedero.add(lblTextoMonedero);
        panelMonedero.add(Box.createRigidArea(new Dimension(0, 180)));
        panelMonedero.add(btnRecargar);
        panelMonedero.add(Box.createVerticalGlue());
        
        JPanel monederoWrapper = new JPanel(new BorderLayout());
        monederoWrapper.setBackground(Color.WHITE);
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
        
        boolean disponible = m.getCantidadRaciones() > 0;
        String textoEstado = disponible ? "Disponible" : "Agotado";
        Color colorEstado = disponible ? new Color(0, 128, 0) : Color.RED;
        
        JLabel lblEstado = new JLabel(textoEstado);
        lblEstado.setFont(new Font("ARIAL", Font.BOLD | Font.ITALIC, 11));
        lblEstado.setForeground(colorEstado);
        lblEstado.setAlignmentX(Component.LEFT_ALIGNMENT);

        JButton btnReservar = new JButton("Reservar");
        btnReservar.setFont(new Font("ARIAL", Font.BOLD, 11));
        btnReservar.setBackground(new Color(100, 200, 100)); // Green
        btnReservar.setForeground(Color.WHITE);
        btnReservar.setFocusPainted(false);
        btnReservar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnReservar.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        if (!disponible) {
            btnReservar.setEnabled(false);
            btnReservar.setBackground(Color.GRAY);
        }

        btnReservar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "aun no pije esperate");
        });

        card.add(txtPlato);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(lblPrecio);
        card.add(lblEstado);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(btnReservar);
        
        return card;
    }

    public void setMonedero(String saldo) {
        lblTextoMonedero.setText(saldo + " Bs");
        lblTextoMonedero.setFont(new Font("ARIAL", Font.BOLD, 42));
    }

    public void setControlador(ActionListener ac) {
        btnRecargar.addActionListener(ac);
        btnRecargar.setActionCommand("recargar");
        btnSalir.addActionListener(ac);
        btnSalir.setActionCommand("logout");
    }
}