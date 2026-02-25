package modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraCostosTest {


    private CalculadoraCostos calculadora = new CalculadoraCostos();


    @Test
    public void testCalcularCCB_ValoresNormales() {
 
        double costoFijo = 100.0;
        double costoVariable = 50.0;
        int numeroBandejas = 10;
        double mermaDecimal = 0.10; 
        double resultadoEsperado = 16.5; 

        double resultadoObtenido = calculadora.calcularCCB(costoFijo, costoVariable, numeroBandejas, mermaDecimal);
        
        assertEquals(resultadoEsperado, resultadoObtenido, 0.01, "El CCB calculado no es correcto");
    }

    @Test
    public void testCalcularCCB_CeroBandejas_DebeLanzarExcepcion() {
        double costoFijo = 100.0;
        double costoVariable = 50.0;
        int numeroBandejas = 0; 
        double mermaDecimal = 0.10;
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calcularCCB(costoFijo, costoVariable, numeroBandejas, mermaDecimal);
        });
        
        String mensajeEsperado = "El número de bandejas proyectadas o servidas no puede ser 0";
        assertEquals(mensajeEsperado, exception.getMessage());
    }


    @Test
    public void testCalcularTarifa_Estudiante() {
        double ccbBase = 100.0;
        CalculadoraCostos.TipoUsuario tipo = CalculadoraCostos.TipoUsuario.ESTUDIANTE;
        double tarifaEsperada = 30.0; 
        
        double tarifaObtenida = calculadora.calcularTarifa(ccbBase, tipo);
        
        assertEquals(tarifaEsperada, tarifaObtenida, 0.01, "La tarifa de estudiante debe ser 30.0");
    }

    @Test
    public void testCalcularTarifa_Profesor() {
        double ccbBase = 100.0;
        CalculadoraCostos.TipoUsuario tipo = CalculadoraCostos.TipoUsuario.PROFESOR;
        double tarifaEsperada = 90.0; 
        
        double tarifaObtenida = calculadora.calcularTarifa(ccbBase, tipo);
        
        assertEquals(tarifaEsperada, tarifaObtenida, 0.01, "La tarifa de profesor debe ser 90.0");
    }

    @Test
    public void testCalcularTarifa_Empleado() {
        double ccbBase = 100.0;
        CalculadoraCostos.TipoUsuario tipo = CalculadoraCostos.TipoUsuario.EMPLEADO;
        double tarifaEsperada = 110.0; 
        
        double tarifaObtenida = calculadora.calcularTarifa(ccbBase, tipo);
        
        assertEquals(tarifaEsperada, tarifaObtenida, 0.01, "La tarifa de empleado debe ser 110.0");
    }
}