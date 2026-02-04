package modelo;

public class CalculadoraCostos {

    public double calcularCCB(double cf, double cv, double nb, double mermaDecimal) {
        
        if (nb == 0) {
            throw new IllegalArgumentException("El número de bandejas no puede ser 0");
        }

        double costosTotales = cf + cv;
        double costoBase = costosTotales / nb;
        double factorMerma = 1.0 + mermaDecimal;

        return costoBase * factorMerma;
    }
}