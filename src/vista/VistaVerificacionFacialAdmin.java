package vista;

import utils.menuUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaVerificacionFacialAdmin extends JFrame {

    private JButton btnSubirImagen;
    private JButton btnVerificar;
    private JButton btnVolver;
    private JLabel lblPrevisualizacion; 

    public VistaVerificacionFacialAdmin() {
        menuUtils.configurarFrame(this, "Verificación Facial - Admin", 1000, 720, JFrame.EXIT_ON_CLOSE);

        JPanel panelPrincipal = menuUtils.crearPanelPrincipal();
        JPanel contenidoPanel = menuUtils.crearPanelCaja(new Dimension(600, 550));

        contenidoPanel.add(Box.createRigidArea(menuUtils.ESPACIO_20));
        contenidoPanel.add(menuUtils.crearTitulo("VERIFICACIÓN FACIAL"));
        contenidoPanel.add(Box.createRigidArea(menuUtils.ESPACIO_30));

        lblPrevisualizacion = new JLabel("...", SwingConstants.CENTER);
        lblPrevisualizacion.setPreferredSize(new Dimension(300, 300));
        lblPrevisualizacion.setMaximumSize(new Dimension(300, 300));
        lblPrevisualizacion.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        lblPrevisualizacion.setAlignmentX(Component.CENTER_ALIGNMENT);
        contenidoPanel.add(lblPrevisualizacion);
        
        contenidoPanel.add(Box.createRigidArea(menuUtils.ESPACIO_30));

        Dimension dimBoton = new Dimension(300, 40);

        btnSubirImagen = menuUtils.crearBoton("Subir Imagen ");
        btnSubirImagen.setMaximumSize(dimBoton);
        btnSubirImagen.setBackground(new Color(220, 235, 245));
        contenidoPanel.add(btnSubirImagen);

        contenidoPanel.add(Box.createRigidArea(menuUtils.ESPACIO_10));

        btnVerificar = menuUtils.crearBoton("Verificar");
        btnVerificar.setMaximumSize(dimBoton);
        btnVerificar.setBackground(new Color(200, 255, 200));
        contenidoPanel.add(btnVerificar);

        contenidoPanel.add(Box.createRigidArea(menuUtils.ESPACIO_30));

        btnVolver = menuUtils.crearBoton("salir");
        btnVolver.setMaximumSize(dimBoton);
        btnVolver.setBackground(new Color(255, 100, 100));
        btnVolver.setForeground(Color.WHITE);
        contenidoPanel.add(btnVolver);

        panelPrincipal.add(contenidoPanel);
        add(panelPrincipal);
    }

    public void setControlador(ActionListener c) {
        btnSubirImagen.addActionListener(c);
        btnSubirImagen.setActionCommand("SUBIR_IMAGEN"); 

        btnVerificar.addActionListener(c);
        btnVerificar.setActionCommand("VERIFICAR");     

        btnVolver.addActionListener(c);
        btnVolver.setActionCommand("VOLVER");       
    }
  
    public void setPrevisualizacionTexto(String texto) {
        lblPrevisualizacion.setText(texto);
    }
}