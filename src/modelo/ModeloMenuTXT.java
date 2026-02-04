package modelo;

import java.io.*;
import java.util.Scanner;

public class ModeloMenuTXT {
    private String archivo = "menus_db.txt";

    public void guardarMenu(Menu menu) {
        try (FileWriter escritor = new FileWriter(archivo, true)) {
            // Guarda: Fecha#Tipo#Raciones#Costo
            escritor.write(menu.toTXT() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String leerMenus() {
        StringBuilder sb = new StringBuilder();
        File f = new File(archivo);
        if (!f.exists()) return "No hay registros.";

        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split("#");
                
                // AHORA ESPERAMOS 4 DATOS (Incluyendo el costo)
                if (datos.length >= 4) {
                    // datos[3] es el costo. Lo parseamos para mostrarlo bonito
                    double costo = Double.parseDouble(datos[3]);
                    
                    sb.append(datos[0]) // Fecha
                      .append(" - ").append(datos[1]) // Plato
                      .append(" (").append(datos[2]).append(" rac.)")
                      .append(" -> Costo: ").append(String.format("%.2f", costo)).append(" Bs.")
                      .append("\n");
                }
            }
        } catch (Exception e) {
            return "Error al leer archivo (formato antiguo o corrupto).";
        }
        return sb.toString();
    }
}