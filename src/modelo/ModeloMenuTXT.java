package modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public String leerMenus() {
        StringBuilder sb = new StringBuilder();
        if (!archivo.exists()) return "No hay registros.";

        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split("#");

                if (datos.length >= 5) {                 
                    double costo = Double.parseDouble(datos[4]);     
                    sb.append(datos[0]) 
                      .append(" [").append(datos[1]).append("] ") 
                      .append("- ").append(datos[2])              
                      .append(" (").append(datos[3]).append(" rac.)")
                      .append(" -> Costo: ").append(String.format("%.2f", costo)).append(" Bs.")
                      .append("\n");
                }
            }
        } catch (Exception e) {
            return "Error al leer archivo (formato antiguo o corrupto).";
        }
        return sb.toString();
    }

    public boolean eliminarMenu(String fecha, String tipoComida, String tipoPlato) {
        if (!archivo.exists()) return false;

        List<String> lineasRestantes = new ArrayList<>();
        boolean eliminado = false;

        try (Scanner scanner = new Scanner(archivo)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split("#");
                
                if (datos.length >= 5) {
                    String fechaMenu = datos[0].trim();
                    String tipoComidaMenu = datos[1].trim();
                    String tipoPlatoMenu = datos[2].trim();

                    if (fechaMenu.equals(fecha) && 
                        tipoComidaMenu.equalsIgnoreCase(tipoComida) && 
                        tipoPlatoMenu.equals(tipoPlato)) {
                        
                        eliminado = true; 
                        continue;         
                    }
                }

                lineasRestantes.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (eliminado) {
            try (FileWriter writer = new FileWriter(archivo)) {
                for (String l : lineasRestantes) {
                    writer.write(l + "\n");
                }
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        return false; 
    }
}