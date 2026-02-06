package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class vistaMenuUsuario extends JFrame {
    public JButton btnSalir;
    private JLabel lblSaludo;

    public vistaMenuUsuario() {
        menuUtils utils = new menuUtils();
        setTitle("Menú Principal");
        setSize(1200, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        panelPrincipal.setBackground(new Color(181, 246, 255));

        JPanel contenedor = new JPanel(new BorderLayout(0, 20));
        contenedor.setBackground(Color.WHITE);
        contenedor.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.BLACK, 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        contenedor.setPreferredSize(new Dimension(900, 500));

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        lblSaludo = new JLabel("Buenos días usuario");
        lblSaludo.setFont(new Font("ARIAL", Font.BOLD, 26));
        header.add(lblSaludo, BorderLayout.WEST);

        btnSalir = utils.crearBoton("Cerrar sesión");
        btnSalir.setBackground(new Color(230, 230, 230));
        header.add(btnSalir, BorderLayout.EAST);

        JPanel body = new JPanel();
        body.setBackground(Color.WHITE);
        body.setLayout(new BoxLayout(body, BoxLayout.X_AXIS));

        JPanel panelMenu = crearSeccion(
            "Menú del día",
            "Placeholder",
            false
        );

        JPanel panelMonedero = crearSeccion(
            "Monedero",
            "Placeholder",
            true
        );

        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setForeground(Color.BLACK);
        separator.setMaximumSize(new Dimension(2, Integer.MAX_VALUE));

        panelMenu.setPreferredSize(new Dimension(560, 380));
        panelMenu.setMaximumSize(new Dimension(560, Integer.MAX_VALUE));

        JPanel monederoWrapper = new JPanel(new BorderLayout());
        monederoWrapper.setBackground(Color.WHITE);
        monederoWrapper.setPreferredSize(new Dimension(240, 380));
        monederoWrapper.setMaximumSize(new Dimension(240, Integer.MAX_VALUE));
        monederoWrapper.add(panelMonedero, BorderLayout.CENTER);

        body.add(panelMenu);
        body.add(Box.createHorizontalGlue());
        body.add(separator);
        body.add(Box.createRigidArea(new Dimension(20, 0)));
        body.add(monederoWrapper);
        body.add(Box.createHorizontalGlue());

        JSeparator topLine = new JSeparator(SwingConstants.HORIZONTAL);
        topLine.setForeground(Color.BLACK);

        JPanel content = new JPanel();
        content.setBackground(Color.WHITE);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.add(topLine);
        content.add(Box.createRigidArea(new Dimension(0, 15)));
        content.add(body);

        contenedor.add(header, BorderLayout.NORTH);
        contenedor.add(content, BorderLayout.CENTER);

        panelPrincipal.add(contenedor);
        add(panelPrincipal);
    }

    private JPanel crearSeccion(String titulo, String placeholder, boolean centered) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("ARIAL", Font.BOLD, 18));
        lblTitulo.setAlignmentX(centered ? Component.CENTER_ALIGNMENT : Component.LEFT_ALIGNMENT);

        JLabel lblPlaceholder = new JLabel(placeholder);
        lblPlaceholder.setFont(new Font("ARIAL", Font.ITALIC, 14));
        lblPlaceholder.setAlignmentX(centered ? Component.CENTER_ALIGNMENT : Component.LEFT_ALIGNMENT);

        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(lblTitulo);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(lblPlaceholder);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        panel.add(Box.createVerticalGlue());

        return panel;
    }

    public void setUsuario(String usuario) {
        lblSaludo.setText("Bienvenido, " + usuario);
    }

    /*public void setMenu() {
       
    }*/

    /*public void setMonedero() {
        
    }*/

    public void setControlador(ActionListener ac) {
        btnSalir.addActionListener(ac);
        btnSalir.setActionCommand("logout");
    }
}