package modelo;

import java.io.*;
import java.util.Scanner;

public class ModeloMenuTXT {
    private String archivo = "menus_db.txt";

    public void guardarMenu(Menu menu) {
        try (FileWriter escritor = new FileWriter(archivo, true)) {
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

                if (datos.length >= 4) {                 
                    double costo = Double.parseDouble(datos[3]);     
                    sb.append(datos[0]) 
                      .append(" - ").append(datos[1]) 
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