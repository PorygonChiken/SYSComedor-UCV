package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ModeloEliminarMenu {
    private static final String DATA_DIR = "data";
    private final File archivoMenus = new File(DATA_DIR, "menus_db.txt");
    private final File archivoReservas = new File(DATA_DIR, "reserva.txt");

    public boolean eliminarMenu(String fecha, String tipoComida, String tipoPlato) {
        if (!archivoMenus.exists()) return false;

        List<String> lineasValidas = new ArrayList<>();
        boolean eliminado = false;

        try (Scanner scanner = new Scanner(archivoMenus)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split("#");
                
                if (datos.length >= 5) {
                    if (datos[0].trim().equals(fecha.trim()) && 
                        datos[1].trim().equalsIgnoreCase(tipoComida.trim()) && 
                        datos[2].trim().equalsIgnoreCase(tipoPlato.trim())) {
                        eliminado = true; 
                    } else {
                        lineasValidas.add(linea);
                    }
                } else {
                    lineasValidas.add(linea);
                }
            }
        } catch (Exception e) { return false; }

        if (eliminado) {
            try (FileWriter writer = new FileWriter(archivoMenus, false)) {
                for (String l : lineasValidas) writer.write(l + "\n");
            } catch (IOException e) { return false; }
        }
        return eliminado;
    }

    public void eliminarReservasPorMenu(String tipoComida, String tipoPlato) {
        if (!archivoReservas.exists()) return;

        List<String> lineasValidas = new ArrayList<>();
        boolean modificado = false;

        try (Scanner scanner = new Scanner(archivoReservas)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(";");
                
                if (partes.length >= 5) {
                    if (partes[2].trim().equalsIgnoreCase(tipoComida.trim()) && 
                        partes[3].trim().equalsIgnoreCase(tipoPlato.trim())) {
                        modificado = true; 
                    } else {
                        lineasValidas.add(linea);
                    }
                } else {
                    lineasValidas.add(linea);
                }
            }
        } catch (Exception e) { return; }

        if (modificado) {
            try (FileWriter writer = new FileWriter(archivoReservas, false)) {
                for (String l : lineasValidas) writer.write(l + "\n");
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

    public void limpiarReservasVencidas() {
        if (!archivoReservas.exists()) return;

        List<String> lineasValidas = new ArrayList<>();
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/M/yyyy");
        boolean modificado = false;

        try (Scanner scanner = new Scanner(archivoReservas)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(";");
                
                if (partes.length >= 5) {
                    try {
                        LocalDate fechaReserva = LocalDate.parse(partes[1].trim(), formato);
                        if (fechaReserva.isBefore(hoy)) {
                            modificado = true; 
                        } else {
                            lineasValidas.add(linea);
                        }
                    } catch (Exception e) {
                        lineasValidas.add(linea); 
                    }
                } else {
                    lineasValidas.add(linea);
                }
            }
        } catch (Exception e) { return; }

        if (modificado) {
            try (FileWriter writer = new FileWriter(archivoReservas, false)) {
                for (String l : lineasValidas) writer.write(l + "\n");
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
}