package modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraCostosTest {

    // Instancia de la clase que vamos a probar
    private CalculadoraCostos calculadora = new CalculadoraCostos();

    // ==========================================
    // PRUEBAS PARA calcularCCB
    // ==========================================

    @Test
    public void testCalcularCCB_ValoresNormales() {
        // 1. ARRANGE (Preparar): Configuramos los datos de entrada
        double costoFijo = 100.0;
        double costoVariable = 50.0;
        int numeroBandejas = 10;
        double mermaDecimal = 0.10; // Equivale al 10%
        double resultadoEsperado = 16.5; // (150 / 10) * 1.10 = 16.5
        
        // 2. ACT (Actuar): Ejecutamos el método que queremos probar
        double resultadoObtenido = calculadora.calcularCCB(costoFijo, costoVariable, numeroBandejas, mermaDecimal);
        
        // 3. ASSERT (Afirmar): Comprobamos que el resultado obtenido sea igual al esperado
        assertEquals(resultadoEsperado, resultadoObtenido, 0.01, "El CCB calculado no es correcto");
    }

    @Test
    public void testCalcularCCB_CeroBandejas_DebeLanzarExcepcion() {
        // 1. ARRANGE (Preparar): Preparamos los datos, forzando las 0 bandejas
        double costoFijo = 100.0;
        double costoVariable = 50.0;
        int numeroBandejas = 0; // Aquí está el dato que causará el error
        double mermaDecimal = 0.10;
        
        // 2. ACT & 3. ASSERT: En pruebas de excepciones (errores) se suelen combinar.
        // Actuamos llamando al método y afirmamos que debe lanzar la excepción "IllegalArgumentException".
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calcularCCB(costoFijo, costoVariable, numeroBandejas, mermaDecimal);
        });
        
        // 3. ASSERT (Afirmar extra): Confirmamos que el mensaje del error sea exactamente el que programaste
        String mensajeEsperado = "El número de bandejas proyectadas o servidas no puede ser 0";
        assertEquals(mensajeEsperado, exception.getMessage());
    }

    // ==========================================
    // PRUEBAS PARA calcularTarifa
    // ==========================================

    @Test
    public void testCalcularTarifa_Estudiante() {
        // 1. ARRANGE (Preparar): Definimos un CCB base y el tipo de usuario
        double ccbBase = 100.0;
        CalculadoraCostos.TipoUsuario tipo = CalculadoraCostos.TipoUsuario.ESTUDIANTE;
        double tarifaEsperada = 30.0; // El estudiante paga el 30%
        
        // 2. ACT (Actuar): Llamamos al método de calcular la tarifa
        double tarifaObtenida = calculadora.calcularTarifa(ccbBase, tipo);
        
        // 3. ASSERT (Afirmar): Verificamos que se haya calculado bien el 30%
        assertEquals(tarifaEsperada, tarifaObtenida, 0.01, "La tarifa de estudiante debe ser 30.0");
    }

    @Test
    public void testCalcularTarifa_Profesor() {
        // 1. ARRANGE (Preparar)
        double ccbBase = 100.0;
        CalculadoraCostos.TipoUsuario tipo = CalculadoraCostos.TipoUsuario.PROFESOR;
        double tarifaEsperada = 90.0; // El profesor paga el 90%
        
        // 2. ACT (Actuar)
        double tarifaObtenida = calculadora.calcularTarifa(ccbBase, tipo);
        
        // 3. ASSERT (Afirmar)
        assertEquals(tarifaEsperada, tarifaObtenida, 0.01, "La tarifa de profesor debe ser 90.0");
    }

    @Test
    public void testCalcularTarifa_Empleado() {
        // 1. ARRANGE (Preparar)
        double ccbBase = 100.0;
        CalculadoraCostos.TipoUsuario tipo = CalculadoraCostos.TipoUsuario.EMPLEADO;
        double tarifaEsperada = 110.0; // El empleado paga el 110%
        
        // 2. ACT (Actuar)
        double tarifaObtenida = calculadora.calcularTarifa(ccbBase, tipo);
        
        // 3. ASSERT (Afirmar)
        assertEquals(tarifaEsperada, tarifaObtenida, 0.01, "La tarifa de empleado debe ser 110.0");
    }
}