package modelo;

public class CalculadoraCostos {

    public enum TipoUsuario {
        ESTUDIANTE, PROFESOR, EMPLEADO
    }

    private static final double TARIFA_ESTUDIANTE = 0.30; 
    private static final double TARIFA_PROFESOR = 0.90;   
    private static final double TARIFA_EMPLEADO = 1.10;   

    public double calcularCCB(double cf, double cv, double nb, double mermaDecimal) {
        if (nb == 0) {
            throw new IllegalArgumentException("El número de bandejas proyectadas o servidas no puede ser 0");
        }
        
        double costosTotales = cf + cv;
        double costoBase = costosTotales / nb;
        double factorMerma = 1.0 + mermaDecimal;
        double resultado = costoBase * factorMerma;

        return Math.round(resultado * 100.0) / 100.0;
    }


    public double calcularTarifa(double ccb, TipoUsuario tipo) {
        double resultado = 0;
        switch (tipo) {
            case ESTUDIANTE:
                resultado = ccb * TARIFA_ESTUDIANTE;
                break;
                
            case PROFESOR:
                resultado = ccb * TARIFA_PROFESOR;
                break;
            case EMPLEADO:
                resultado = ccb * TARIFA_EMPLEADO;
                break;
            default:
               throw new IllegalArgumentException("Tipo de usuario no válido");
        }
      return Math.round(resultado * 100.0) / 100.0;
    }
}