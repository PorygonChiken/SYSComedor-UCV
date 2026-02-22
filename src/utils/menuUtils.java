package utils;

import javax.swing.*;
import java.awt.*;

public class menuUtils {
    public static final Dimension ESPACIO_10 = new Dimension(0, 10);
    public static final Dimension ESPACIO_20 = new Dimension(0, 20);
    public static final Dimension ESPACIO_30 = new Dimension(0, 30);
    public static final Color BLANCO_TRANSLUCIDO = new Color(255, 255, 255, 180);

    public static void configurarFrame(JFrame frame, String titulo, int ancho, int alto, int closeOperation) {
        frame.setTitle(titulo);
        frame.setSize(ancho, alto);
        frame.setDefaultCloseOperation(closeOperation);
        frame.setLocationRelativeTo(null);
    }

    public static JPanel crearPanelPrincipal() {
        JPanel panel = new JPanel(new GridBagLayout()) {
            private Image backgroundImage = new ImageIcon("data/img/bg/bg.png").getImage();
            
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        return panel;
    }

    public static JPanel crearPanelCaja(Dimension preferredSize) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
            }
        };
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BLANCO_TRANSLUCIDO);
        if (preferredSize != null) {
            panel.setPreferredSize(preferredSize);
        }
        return panel;
    }

    public static JPanel crearPanelCajaPadding(Dimension preferredSize, Insets padding) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                super.paintComponent(g);
            }
        };
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BLANCO_TRANSLUCIDO);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(padding.top, padding.left, padding.bottom, padding.right),
             BorderFactory.createEmptyBorder()
        ));
        if (preferredSize != null) {
            panel.setPreferredSize(preferredSize);
        }
        return panel;
    }

    public static JLabel crearTitulo(String texto) {
        JLabel titulo = new JLabel(texto);
        titulo.setFont(new Font("IMPACT", Font.PLAIN, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        return titulo;
    }

    public static JPanel crearFila(String textoLabel, JTextField campo) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fila.setOpaque(false);
        fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JLabel label = new JLabel(textoLabel);
        label.setPreferredSize(new Dimension(100, 30));
        label.setFont(new Font("ARIAL", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.LEFT);

        campo.setPreferredSize(new Dimension(200, 30));

        fila.add(label);
        fila.add(campo);

        return fila;
    }
    public static JPanel crearFila(String textoLabel, JComboBox<String> combo) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fila.setOpaque(false);
        fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JLabel label = new JLabel(textoLabel);
        label.setPreferredSize(new Dimension(100, 30));
        label.setFont(new Font("ARIAL", Font.BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.LEFT);

        combo.setPreferredSize(new Dimension(200, 30));
        
        fila.add(label);
        fila.add(combo);

        return fila;
    }

    public static JButton crearBoton(String texto) {
        JButton boton = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
                super.paintComponent(g);
            }
        };
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setFont(new Font("ARIAL", Font.BOLD, 16));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setFocusPainted(false);
        return boton;
    }

    public static JButton crearBoton(String texto, Dimension maxSize) {
        JButton boton = crearBoton(texto);
        boton.setMaximumSize(maxSize);
        return boton;
    }
}
