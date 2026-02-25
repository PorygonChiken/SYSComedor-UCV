package modelo;

import java.io.*;

public class ModeloMenuTXT {
    private static final String DATA_DIR = "data";
    private final File archivo = new File(DATA_DIR, "menus_db.txt");

    public void guardarMenu(Menu menu) {
        try (FileWriter escritor = new FileWriter(archivo, true)) {
            escritor.write(menu.toTXT() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double procesarYGuardarMenu(String fecha, String tipoComida, String tipoPlato, 
                                       String cantStr, String cfStr, String cvStr, String mermaStr) 
                                       throws NumberFormatException, IllegalArgumentException {

        int raciones = Integer.parseInt(cantStr); 
        double cf = Double.parseDouble(cfStr);
        double cv = Double.parseDouble(cvStr);
        double mermaEntera = Double.parseDouble(mermaStr); 
        
        double mermaDecimal = mermaEntera / 100.0;
        CalculadoraCostos calculadora = new CalculadoraCostos();
        double ccb = calculadora.calcularCCB(cf, cv, raciones, mermaDecimal);

        Menu nuevoMenu = new Menu(fecha, tipoComida, tipoPlato, raciones, ccb);
        guardarMenu(nuevoMenu);

        return ccb; 
    }
}