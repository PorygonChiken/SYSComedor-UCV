import controlador.ControladorVerificacionFacialAdmin;

public class MainCajero {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {  public void run() {
                new ControladorVerificacionFacialAdmin();
            }
        });
    }
}