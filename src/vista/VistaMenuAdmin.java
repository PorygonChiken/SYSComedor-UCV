package vista;

import utils.menuUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VistaMenuAdmin extends JFrame {

    private JTextField txtFecha;
    private JTextField txtPlato;
    private JTextField txtRaciones;
    private JTextField txtCostoFijo;  
    private JTextField txtCostoVariable;
    private JTextField txtMerma;
    private JButton btnGuardar;
    private JButton btnSalir;
    private JComboBox<String> cmbTipoComida;
    
    public VistaMenuAdmin() {
        menuUtils.configurarFrame(this, "Administración - Comedor UCV", 1000, 720, JFrame.EXIT_ON_CLOSE);

        JPanel panelPrincipal = menuUtils.crearPanelPrincipal();
        
        JPanel menuPanel = menuUtils.crearPanelCaja(new Dimension(500, 650));

        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_20));
        menuPanel.add(menuUtils.crearTitulo("GESTIÓN DE MENÚ"));
        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_20));

        this.txtFecha = new JTextField();
        this.txtPlato = new JTextField();
        this.txtRaciones = new JTextField();
        this.txtCostoFijo = new JTextField();
        this.txtCostoVariable = new JTextField();
        this.txtMerma = new JTextField();
        this.cmbTipoComida = new JComboBox<>(new String[]{"Desayuno", "Almuerzo"});
        this.cmbTipoComida.setBackground(Color.WHITE);

        menuPanel.add(menuUtils.crearFila("Fecha:", this.txtFecha));
        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_10));

        menuPanel.add(menuUtils.crearFila("Plato :", this.txtPlato));
        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_10));

        menuPanel.add(menuUtils.crearFila("N° Raciones:", this.txtRaciones));
        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_10));

        menuPanel.add(menuUtils.crearFila("Costo Fijo:", this.txtCostoFijo));
        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_10));

        menuPanel.add(menuUtils.crearFila("Costo Var.:", this.txtCostoVariable));
        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_10));

        menuPanel.add(menuUtils.crearFila("% Merma:", this.txtMerma));
        
        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_30));
        Dimension dimBoton = new Dimension(300, 40);

        menuPanel.add(menuUtils.crearFila("Tipo:", this.cmbTipoComida));
        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_10));

        this.btnGuardar = menuUtils.crearBoton("Calcular y Guardar");
        this.btnGuardar.setMaximumSize(dimBoton); 
        this.btnGuardar.setBackground(new Color(220, 235, 245));
        menuPanel.add(this.btnGuardar);
        
        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_10));
        
        menuPanel.add(Box.createRigidArea(menuUtils.ESPACIO_20));

        this.btnSalir = menuUtils.crearBoton("salir");
        this.btnSalir.setMaximumSize(dimBoton);
        this.btnSalir.setBackground(new Color(255, 100, 100));
        this.btnSalir.setForeground(Color.WHITE);
        menuPanel.add(this.btnSalir);

        panelPrincipal.add(menuPanel);
        add(panelPrincipal);
    }

    public String getFecha() { return txtFecha.getText(); }
    public String getTipoPlato() { return txtPlato.getText(); }
    public String getTipoComida() { return (String) cmbTipoComida.getSelectedItem();}
    public String getCantidad() { return txtRaciones.getText(); }
    public String getCostoFijo() { return txtCostoFijo.getText(); }
    public String getCostoVariable() { return txtCostoVariable.getText(); }
    public String getMerma() { return txtMerma.getText(); }

    public void limpiarCampos() {
        txtFecha.setText(""); 
        txtPlato.setText(""); 
        txtRaciones.setText("");
        txtCostoFijo.setText(""); 
        txtCostoVariable.setText(""); 
        txtMerma.setText("");
    }
    
    public void setAreaReporte(String texto) {
        JTextArea area = new JTextArea(texto);
        area.setEditable(false);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new java.awt.Dimension(400, 300)); 
        JOptionPane.showMessageDialog(this, scroll, "Reporte de Menús", JOptionPane.INFORMATION_MESSAGE);
    }

    public void setControlador(ActionListener c) {
        btnGuardar.addActionListener(c);
        btnGuardar.setActionCommand("GUARDAR");
        
        btnSalir.addActionListener(c);
        btnSalir.setActionCommand("SALIR");
    }
}