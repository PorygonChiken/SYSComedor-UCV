package controlador;

import modelo.CalculadoraCostos;

public class GestionCostosControlador {

    private CalculadoraCostos modeloCostos;

    public GestionCostosControlador() {
        this.modeloCostos = new CalculadoraCostos();
    }

    public void procesarCalculo(double cf, double cv, double nb, double mermaEntera) {

    
            double mermaDecimal = mermaEntera / 100.0;
            double resultado = modeloCostos.calcularCCB(cf, cv, nb, mermaDecimal);
            double resultadoFinal = Math.round(resultado * 100.0) / 100.0;
            System.out.println("CCB: " + resultadoFinal + " Bs.");
    }
}