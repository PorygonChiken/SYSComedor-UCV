package modelo;

public class CalculadoraCostos {

    public enum TipoUsuario {
        ESTUDIANTE, PROFESOR, EMPLEADO
    }

   
    private static final double TARIFA_ESTUDIANTE = 0.30; // 30%
    private static final double TARIFA_PROFESOR = 0.90;   // 90%
    private static final double TARIFA_EMPLEADO = 1.10;   // 110%

    public double calcularCCB(double cf, double cv, double nb, double mermaDecimal) {
        if (nb == 0) {
            throw new IllegalArgumentException("El número de bandejas proyectadas o servidas no puede ser 0");
        }
        
        double costosTotales = cf + cv;
        double costoBase = costosTotales / nb;
        double factorMerma = 1.0 + mermaDecimal;

        return costoBase * factorMerma;
    }


    public double calcularTarifa(double ccb, TipoUsuario tipo) {
        switch (tipo) {
            case ESTUDIANTE:
                return ccb * TARIFA_ESTUDIANTE;
                
            case PROFESOR:
                return ccb * TARIFA_PROFESOR;
                
            case EMPLEADO:
                return ccb * TARIFA_EMPLEADO;
                
            default:
                throw new IllegalArgumentException("Tipo de usuario no válido");
        }
    }
}