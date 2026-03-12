package modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BeneficiosTest {

    @Test
    public void testCamino1_EstudianteRegular_SinBeneficio() {
 
        CalculadoraCostos calc = new CalculadoraCostos();
        double costoCCB = 100.0; 
        double descuentoAdicional = 0.0; 
        
        double precioFinal = calc.calcularTarifa(costoCCB, CalculadoraCostos.TipoUsuario.ESTUDIANTE);
        
        if (descuentoAdicional > 0) {
            precioFinal = precioFinal - (precioFinal * (descuentoAdicional / 100.0));
            precioFinal = Math.round(precioFinal * 100.0) / 100.0;
        }

        assertEquals(30.0, precioFinal, "El precio de un estudiante regular debe ser el 30% del costo original");
    }

    @Test
    public void testCamino2_EstudianteBecario_DescuentoParcial() {

        CalculadoraCostos calc = new CalculadoraCostos();
        double costoCCB = 100.0; 
        double descuentoAdicional = 50.0; 
        
        double precioFinal = calc.calcularTarifa(costoCCB, CalculadoraCostos.TipoUsuario.ESTUDIANTE);
        
        if (descuentoAdicional > 0) {
            precioFinal = precioFinal - (precioFinal * (descuentoAdicional / 100.0));
            precioFinal = Math.round(precioFinal * 100.0) / 100.0;
        }

        assertEquals(15.0, precioFinal, "El precio del becario al 50% debe ser la mitad de la tarifa estudiantil");
    }

    @Test
    public void testCamino3_EstudianteExonerado_CostoCero() {

        CalculadoraCostos calc = new CalculadoraCostos();
        double costoCCB = 100.0; 
        double descuentoAdicional = 100.0; 
        
        double precioFinal = calc.calcularTarifa(costoCCB, CalculadoraCostos.TipoUsuario.ESTUDIANTE);
        
        if (descuentoAdicional > 0) {
            precioFinal = precioFinal - (precioFinal * (descuentoAdicional / 100.0));
            precioFinal = Math.round(precioFinal * 100.0) / 100.0;
        }

        assertEquals(0.0, precioFinal, "El precio de un exonerado debe ser 0.0 Bs");
    }
}