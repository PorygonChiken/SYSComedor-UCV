package modelo;

import org.junit.Test;

import static org.junit.Assert.*;

public class ModeloMenuTXTTest {

    @Test
    public void testProcesarYGuardarMenu_CalculoCorrecto() {

        ModeloMenuTXT modeloMenu = new ModeloMenuTXT();

        String fecha = "24/02/2026";
        String tipoComida = "Almuerzo";
        String tipoPlato = "Pabellón Criollo";
        String racionesStr = "100";  
        String costoFijoStr = "5000"; 
        String costoVariableStr = "3000"; 
        String mermaStr = "10";     

        double resultadoEsperado = 88.0;

        double resultadoObtenido = modeloMenu.procesarYGuardarMenu(
            fecha, tipoComida, tipoPlato, racionesStr, costoFijoStr, costoVariableStr, mermaStr
        );

        assertEquals("El CCB calculado no es correcto", resultadoEsperado, resultadoObtenido, 0.01);
    }
    
    @Test(expected = NumberFormatException.class)
    public void testProcesarYGuardarMenu_ConLetrasLanzaExcepcion() {

        ModeloMenuTXT modeloMenu = new ModeloMenuTXT();
        String racionesMalas = "cien"; 

        modeloMenu.procesarYGuardarMenu(
            "24/02/2026", "Desayuno", "Arepas", racionesMalas, "5000", "3000", "10"
        );
      
    }
}