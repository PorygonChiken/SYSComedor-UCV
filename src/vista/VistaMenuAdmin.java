package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaMenuAdmin extends JFrame {

    // Campos de texto (Inputs)
    private JTextField txtFecha, txtPlato, txtRaciones;
    private JTextField txtCostoFijo, txtCostoVariable, txtMerma;
    private JTextArea areaReporte; // Lo dejo oculto o pequeño por simplicidad
    
    // Botones
    private JButton btnGuardar, btnVer, btnSalir;

    public VistaMenuAdmin() {
        setTitle("Administrador - Comedor");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. FONDO AZUL (Panel Principal)
        // Usamos GridBagLayout para centrar la caja blanca automáticamente
        JPanel panelFondo = new JPanel(new GridBagLayout());
        panelFondo.setBackground(new Color(176, 224, 230)); // Azul clarito (PowderBlue)

        // 2. CAJA BLANCA CENTRAL
        JPanel panelCaja = new JPanel();
        panelCaja.setLayout(new BoxLayout(panelCaja, BoxLayout.Y_AXIS));
        panelCaja.setBackground(Color.WHITE);
        // Borde Negro de 2 pixeles
        panelCaja.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(20, 40, 20, 40) // Margen interno
        ));

        // --- CONTENIDO DE LA CAJA ---

        // Título
        JLabel lblTitulo = new JLabel("Configuración de Menú");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Campos (Creamos una grilla pequeña para que se vea ordenado dentro de la caja)
        JPanel panelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        panelFormulario.setBackground(Color.WHITE);
        
        txtFecha = new JTextField();
        txtPlato = new JTextField();
        txtRaciones = new JTextField();
        txtCostoFijo = new JTextField();
        txtCostoVariable = new JTextField();
        txtMerma = new JTextField();

        // Agregamos etiquetas y campos
        panelFormulario.add(new JLabel("Fecha:"));           panelFormulario.add(txtFecha);
        panelFormulario.add(new JLabel("Plato Principal:")); panelFormulario.add(txtPlato);
        panelFormulario.add(new JLabel("N° Raciones:"));     panelFormulario.add(txtRaciones);
        panelFormulario.add(new JLabel("Costo Fijo:"));      panelFormulario.add(txtCostoFijo);
        panelFormulario.add(new JLabel("Costo Variable:"));  panelFormulario.add(txtCostoVariable);
        panelFormulario.add(new JLabel("% Merma (ej: 10):"));panelFormulario.add(txtMerma);

        // Área de reporte pequeña (para ver que guardaste)
        areaReporte = new JTextArea(3, 20);
        areaReporte.setVisible(false); // La ocultamos para que se vea limpio como la foto, o true si quieres ver logs

        // Botones estilo "Windows Clásico" o web simple
        btnGuardar = new JButton("Guardar y Calcular");
        btnGuardar.setBackground(new Color(240, 240, 240));
        
        btnVer = new JButton("Ver Lista");
        
        btnSalir = new JButton("Cerrar Sesión");
        btnSalir.setBackground(new Color(255, 102, 102)); // Rojo suave
        btnSalir.setForeground(Color.WHITE);

        // Panel de botones para alinearlos
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBackground(Color.WHITE);
        panelBotones.add(btnVer);
        panelBotones.add(btnSalir);

        // --- ARMAR LA CAJA ---
        panelCaja.add(lblTitulo);
        panelCaja.add(Box.createVerticalStrut(20)); // Espacio
        panelCaja.add(panelFormulario);
        panelCaja.add(Box.createVerticalStrut(20)); // Espacio
        
        // Botón principal grande
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCaja.add(btnGuardar);
        panelCaja.add(Box.createVerticalStrut(10));
        panelCaja.add(panelBotones);

        // Agregar la caja al fondo azul
        panelFondo.add(panelCaja);

        // Agregar el fondo a la ventana
        add(panelFondo);
    }

    // --- GETTERS (Necesarios para que el Controlador funcione) ---
    public String getFecha() { return txtFecha.getText(); }
    public String getTipoPlato() { return txtPlato.getText(); }
    public String getCantidad() { return txtRaciones.getText(); }
    public String getCostoFijo() { return txtCostoFijo.getText(); }
    public String getCostoVariable() { return txtCostoVariable.getText(); }
    public String getMerma() { return txtMerma.getText(); }

    public void setAreaReporte(String texto) { 
        // Opcional: Si quieres mostrar mensajes emergentes en lugar de texto
        JOptionPane.showMessageDialog(this, texto);
    }

    public void limpiarCampos() {
        txtFecha.setText(""); txtPlato.setText(""); txtRaciones.setText("");
        txtCostoFijo.setText(""); txtCostoVariable.setText(""); txtMerma.setText("");
    }

    // --- CONEXIÓN CON CONTROLADOR ---
    public void setControlador(ActionListener c) {
        btnGuardar.addActionListener(c);
        btnGuardar.setActionCommand("GUARDAR");
        
        btnVer.addActionListener(c);
        btnVer.setActionCommand("VER");
        
        btnSalir.addActionListener(c);
        btnSalir.setActionCommand("SALIR");
    }
}