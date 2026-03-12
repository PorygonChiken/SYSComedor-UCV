package modelo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.*;

public class ModeloAsistenciaTest {

    private final String RUTA_ARCHIVO = "data/asistencia.txt";
    private final String RUTA_BACKUP = "data/asistencia_backup.txt";
    
    private File archivoTest;
    private File archivoBackup;

    @Before
    public void setUp() {
        File directorio = new File("data");
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        archivoTest = new File(RUTA_ARCHIVO);
        archivoBackup = new File(RUTA_BACKUP);

        if (archivoTest.exists()) {
            archivoTest.renameTo(archivoBackup);
        }

        try (FileWriter fw = new FileWriter(archivoTest, false)) { 
            fw.write("12/3/2026;Almuerzo;Pedro;profesor;N/A\n");
            fw.write("12/3/2026;Almuerzo;Juan;empleado;N/A\n");
            fw.write("12/3/2026;Desayuno;Maria;empleado;N/A\n");
            fw.write("12/3/2026;Almuerzo;Luis;estudiante;regular\n");
            fw.write("12/3/2026;Almuerzo;Ana;estudiante;becario\n");
            fw.write("12/3/2026;Almuerzo;Alejandro;estudiante;exonerado\n");
        } catch (IOException e) {
            fail("Error al preparar el archivo de prueba: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        if (archivoTest.exists()) {
            archivoTest.delete();
        }

        if (archivoBackup.exists()) {
            archivoBackup.renameTo(archivoTest);
        }
    }

    @Test
    public void testGenerarReporteAsistencia_ConteoCorrecto() {
        modelo miModelo = new modelo();
        
        StringBuilder esperado = new StringBuilder();
        esperado.append("Total general de comensales: 6\n");
        esperado.append("------------------------------------------\n");
        esperado.append("  - Profesores: 1\n");
        esperado.append("  - Empleados: 2\n");
        esperado.append("  - Estudiantes (Total): 3\n");
        esperado.append("      * Regulares: 1\n");
        esperado.append("      * Becarios: 1\n");
        esperado.append("      * Exonerados: 1\n");

        String reporteObtenido = miModelo.generarReporteAsistencia();

        assertEquals("El reporte generado no coincide con los cálculos esperados", esperado.toString(), reporteObtenido);
    }
}