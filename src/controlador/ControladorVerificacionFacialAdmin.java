package controlador;

import modelo.ComparadorImagenes;
import vista.VistaVerificacionFacialAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ControladorVerificacionFacialAdmin implements ActionListener {

    private VistaVerificacionFacialAdmin vista;
    private String rutaImagenActual = null; 

    public ControladorVerificacionFacialAdmin() {
        this.vista = new VistaVerificacionFacialAdmin();
        this.vista.setControlador(this);   
        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "SUBIR_IMAGEN":
                procesarSubidaImagen();
                break;
                
            case "VERIFICAR":
                procesarVerificacion();
                break;
                
            case "VOLVER":
                CerrarCajero();
                break;
        }
    }

    private void procesarSubidaImagen() {
        JFileChooser chooser = new JFileChooser();
        int seleccion = chooser.showOpenDialog(vista);
        
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = chooser.getSelectedFile();
            rutaImagenActual = archivoSeleccionado.getAbsolutePath();
            
            vista.setPrevisualizacionTexto(archivoSeleccionado.getName());
        }
    }

    private void procesarVerificacion() {
      
        if (rutaImagenActual == null) {
            JOptionPane.showMessageDialog(vista, "Por favor, suba una imagen primero.");
            return;
        }

        try {
            ComparadorImagenes comparador = new ComparadorImagenes(rutaImagenActual);
            File carpetaImg = new File("data/img");
            
            if (!carpetaImg.exists() || !carpetaImg.isDirectory()) {
                JOptionPane.showMessageDialog(vista, "Error: La carpeta 'data/img' no existe.");
                return;
            }

            File[] archivosEnCarpeta = carpetaImg.listFiles();
            String nombreAsociado = null;

            if (archivosEnCarpeta != null) {
                for (File imgGuardada : archivosEnCarpeta) {
                    if (imgGuardada.isFile() && comparador.compararCon(imgGuardada.getAbsolutePath())) {
                        
                        String nombreCompleto = imgGuardada.getName();
                        int indicePunto = nombreCompleto.lastIndexOf('.');
                        nombreAsociado = (indicePunto == -1) ? nombreCompleto : nombreCompleto.substring(0, indicePunto);
                        break; 
                    }
                }
            }

            if (nombreAsociado != null) {
                File archivoUsuarios = new File("data/usuarios.txt");
                boolean usuarioEncontrado = false;
                
                try (Scanner scanner = new Scanner(archivoUsuarios)) {
                    while (scanner.hasNextLine()) {
                        String[] datos = scanner.nextLine().split(";");

                             if (datos.length >= 5 && datos[4].trim().equals(nombreAsociado)) {
                             String nombreUsuario = datos[0]; 
                               modelo.modelo miModelo = new modelo.modelo(); 
                               String resultadoCobro = miModelo.procesarAccesoFacial(nombreUsuario);
    
                               
                              JOptionPane.showMessageDialog(vista, "Verificación Facial: Hola " + nombreUsuario + "\n\n" + resultadoCobro);
                              usuarioEncontrado = true;
                               break;
                              }
                    }
                }
                
                if (!usuarioEncontrado) {
                    JOptionPane.showMessageDialog(vista, "Imagen reconocida , pero no tiene usuario asignado en usuarios.txt.");
                }
                
            } else {
                JOptionPane.showMessageDialog(vista, "Acceso Denegado: La imagen no coincide con nuestra base de datos.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error durante la verificación: " + ex.getMessage());
        }
    }

  private void CerrarCajero() {
        int confirm = JOptionPane.showConfirmDialog(vista, 
            "¿Desea apagar el sistema de verificación?", 
            "Apagar Cajero", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            vista.dispose();
            System.exit(0); 
        }
    }
}