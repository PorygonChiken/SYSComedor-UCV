package vista;

import javax.swing.*;
import java.awt.*;
    
public class menuUtils extends JFrame {
    public JPanel crearFila(String textoLabel, JTextField campo) {
        JPanel fila = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fila.setBackground(Color.WHITE);
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

    public JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("ARIAL", Font.BOLD, 16));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setFocusPainted(false);
        return boton;
    }
}