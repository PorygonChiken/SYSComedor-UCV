package vista;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaMenuAdmin extends JFrame {
    
    // Componentes del Menú
    private CampoTextoRedondo txtFecha, txtTipoPlato, txtCantidad;
    // Componentes de Costos (Nuevos)
    private CampoTextoRedondo txtCostoFijo, txtCostoVariable, txtMerma;
    
    private JTextArea areaReporte;
    private BotonRedondo btnGuardar, btnVerMenus, btnSalir;

    // Colores
    private final Color COLOR_FONDO = Color.WHITE;
    private final Color COLOR_BOTON = Color.BLACK;
    private final Color COLOR_TEXTO_BOTON = Color.WHITE;
    private final Color COLOR_BORDE = new Color(200, 200, 200);

    public VistaMenuAdmin() {
        setTitle("App Comedor - Cálculo CCB");
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setResizable(true);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(COLOR_FONDO);
        mainPanel.setBorder(new EmptyBorder(30, 30, 30, 30)); 

        // --- ENCABEZADO ---
        JLabel titleLabel = new JLabel("Planificación y Costos");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // --- LISTA ---
        areaReporte = new JTextArea(8, 40);
        areaReporte.setEditable(false);
        areaReporte.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaReporte.setBackground(new Color(250, 250, 250));
        
        Border dashed = BorderFactory.createDashedBorder(COLOR_BORDE, 2, 5, 3, true);
        JScrollPane scroll = new JScrollPane(areaReporte);
        scroll.setBorder(new CompoundBorder(dashed, new EmptyBorder(10,10,10,10)));
        scroll.getViewport().setBackground(new Color(250, 250, 250));
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        scroll.setMaximumSize(new Dimension(800, 200));

        // --- FORMULARIO ---
        // Usaremos un panel Grid para organizar los inputs en 2 columnas
        JPanel formGrid = new JPanel(new GridLayout(3, 2, 20, 10)); // 3 filas, 2 columnas
        formGrid.setBackground(COLOR_FONDO);
        formGrid.setMaximumSize(new Dimension(800, 180));
        formGrid.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Columna 1: Datos del Menú
        txtFecha = new CampoTextoRedondo("Fecha (DD/MM/AAAA)", 350);
        txtTipoPlato = new CampoTextoRedondo("Tipo de Plato", 350);
        txtCantidad = new CampoTextoRedondo("Raciones (Bandejas)", 350);

        // Columna 2: Datos Financieros
        txtCostoFijo = new CampoTextoRedondo("Costo Fijo Total (Bs)", 350);
        txtCostoVariable = new CampoTextoRedondo("Costo Variable Total (Bs)", 350);
        txtMerma = new CampoTextoRedondo("Merma (%) Ej: 10", 350);

        // Agregamos en orden de lectura
        formGrid.add(crearPanelCampo("Fecha:", txtFecha));
        formGrid.add(crearPanelCampo("Costos Fijos:", txtCostoFijo));
        
        formGrid.add(crearPanelCampo("Plato:", txtTipoPlato));
        formGrid.add(crearPanelCampo("Costos Variables:", txtCostoVariable));
        
        formGrid.add(crearPanelCampo("Raciones:", txtCantidad));
        formGrid.add(crearPanelCampo("Porcentaje Merma:", txtMerma));

        // --- BOTONES ---
        btnGuardar = new BotonRedondo("CALCULAR Y GUARDAR", COLOR_BOTON, COLOR_TEXTO_BOTON, 400);
        
        JPanel panelSecundario = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        panelSecundario.setBackground(COLOR_FONDO);
        
        btnVerMenus = new BotonRedondo("Ver Lista", Color.LIGHT_GRAY, Color.BLACK, 150);
        btnSalir = new BotonRedondo("Salir", Color.WHITE, Color.RED, 100);
        btnSalir.setBorder(null);

        panelSecundario.add(btnVerMenus);
        panelSecundario.add(btnSalir);

        // --- ARMAR PANTALLA ---
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(scroll);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(formGrid); // Insertamos la cuadrícula
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(btnGuardar);
        mainPanel.add(Box.createVerticalStrut(15));
        mainPanel.add(panelSecundario);
        mainPanel.add(Box.createVerticalGlue());

        add(mainPanel);
    }

    // Helper para empaquetar Label + Input
    private JPanel crearPanelCampo(String titulo, JComponent campo) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(Color.WHITE);
        JLabel l = new JLabel(titulo);
        l.setFont(new Font("SansSerif", Font.BOLD, 12));
        l.setAlignmentX(Component.LEFT_ALIGNMENT);
        campo.setAlignmentX(Component.LEFT_ALIGNMENT);
        p.add(l);
        p.add(Box.createVerticalStrut(5));
        p.add(campo);
        return p;
    }

    // --- GETTERS ---
    public String getFecha() { return txtFecha.getText(); }
    public String getTipoPlato() { return txtTipoPlato.getText(); }
    public String getCantidad() { return txtCantidad.getText(); }
    // Nuevos Getters
    public String getCostoFijo() { return txtCostoFijo.getText(); }
    public String getCostoVariable() { return txtCostoVariable.getText(); }
    public String getMerma() { return txtMerma.getText(); }

    public void setAreaReporte(String texto) { areaReporte.setText(texto); }
    
    public void limpiarCampos() {
        txtFecha.setText(""); txtTipoPlato.setText(""); txtCantidad.setText("");
        txtCostoFijo.setText(""); txtCostoVariable.setText(""); txtMerma.setText("");
    }

    public void setControlador(ActionListener c) {
        btnGuardar.addActionListener(c);
        btnGuardar.setActionCommand("GUARDAR");
        btnVerMenus.addActionListener(c);
        btnVerMenus.setActionCommand("VER");
        btnSalir.addActionListener(c);
        btnSalir.setActionCommand("SALIR");
    }

    // Clases internas de diseño (Igual que antes)
    class BotonRedondo extends JButton {
        private Color colorFondo, colorTexto;
        public BotonRedondo(String texto, Color fondo, Color textoColor, int ancho) {
            super(texto);
            this.colorFondo = fondo; this.colorTexto = textoColor;
            setContentAreaFilled(false); setFocusPainted(false); setBorderPainted(false);
            setForeground(textoColor); setFont(new Font("SansSerif", Font.BOLD, 14));
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setMaximumSize(new Dimension(ancho, 45)); setPreferredSize(new Dimension(ancho, 45));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(220,220,220)); g2.fillRoundRect(2, 2, getWidth()-2, getHeight()-2, 20, 20);
            g2.setColor(colorFondo); g2.fillRoundRect(0, 0, getWidth()-3, getHeight()-3, 20, 20);
            g2.dispose(); super.paintComponent(g);
        }
    }
    class CampoTextoRedondo extends JTextField {
        public CampoTextoRedondo(String ph, int ancho) {
            setOpaque(false); setBorder(new EmptyBorder(5, 15, 5, 15));
            setFont(new Font("SansSerif", Font.PLAIN, 14));
            setAlignmentX(Component.CENTER_ALIGNMENT);
            setMaximumSize(new Dimension(ancho, 40)); setPreferredSize(new Dimension(ancho, 40));
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE); g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            g2.setColor(new Color(200, 200, 200)); g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            g2.dispose(); super.paintComponent(g);
        }
    }
}