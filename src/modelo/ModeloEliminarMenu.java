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

    public boolean editarMenu(String fecha, String tipoComida, String tipoPlatoAntiguo, String nuevoPlato, int nuevasRaciones, double nuevoCosto) {
        if (!archivoMenus.exists()) return false;
        
        List<String> lineasValidas = new ArrayList<>();
        boolean modificado = false;

        try (Scanner scanner = new Scanner(archivoMenus)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split("#");
                
                if (datos.length >= 5 && datos[0].trim().equals(fecha.trim()) && 
                    datos[1].trim().equalsIgnoreCase(tipoComida.trim()) && 
                    datos[2].trim().equalsIgnoreCase(tipoPlatoAntiguo.trim())) {
                    
                    String platoFinal = (nuevoPlato != null && !nuevoPlato.isEmpty()) ? nuevoPlato : datos[2];
                    int racionesFinales = (nuevasRaciones != -1) ? nuevasRaciones : Integer.parseInt(datos[3]);
                    double costoFinal = (nuevoCosto != -1.0) ? nuevoCosto : Double.parseDouble(datos[4]);

                    linea = datos[0] + "#" + datos[1] + "#" + platoFinal + "#" + racionesFinales + "#" + costoFinal;
                    modificado = true; 
                }
                lineasValidas.add(linea);
            }
        } catch (Exception e) { return false; }

        if (modificado) {
            try (FileWriter writer = new FileWriter(archivoMenus, false)) {
                for (String l : lineasValidas) writer.write(l + "\n");
            } catch (IOException e) { return false; }
        }
        return modificado;
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

    public void actualizarNombreReserva(String fecha, String tipoComida, String platoAntiguo, String nuevoPlato) {
        if (!archivoReservas.exists()) return;

        List<String> lineasValidas = new ArrayList<>();
        boolean modificado = false;

        try (Scanner scanner = new Scanner(archivoReservas)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(";");
                
                if (partes.length >= 5) {
                    if (partes[1].trim().equals(fecha.trim()) && 
                        partes[2].trim().equalsIgnoreCase(tipoComida.trim()) && 
                        partes[3].trim().equalsIgnoreCase(platoAntiguo.trim())) {
                        
                        linea = partes[0] + ";" + partes[1] + ";" + partes[2] + ";" + nuevoPlato + ";" + partes[4];
                        modificado = true; 
                    }
                }
                lineasValidas.add(linea);
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
                            devolverRacionMenu(partes[2], partes[3]);                           
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

    private void devolverRacionMenu(String tipoComida, String tipoPlato) {
        if (!archivoMenus.exists()) return;
        List<String> lineasMenu = new ArrayList<>();
        boolean actualizado = false;

        try (Scanner scanner = new Scanner(archivoMenus)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split("#");
                
                if (!actualizado && datos.length >= 5 && 
                    datos[1].trim().equalsIgnoreCase(tipoComida.trim()) && 
                    datos[2].trim().equalsIgnoreCase(tipoPlato.trim())) {
                    
                    int raciones = Integer.parseInt(datos[3].trim());
                    raciones++; 
                    
                    linea = datos[0] + "#" + datos[1] + "#" + datos[2] + "#" + raciones + "#" + datos[4];
                    actualizado = true; 
                }
                lineasMenu.add(linea);
            }
        } catch (Exception e) { return; }

        if (actualizado) {
            try (FileWriter writer = new FileWriter(archivoMenus, false)) {
                for (String l : lineasMenu) writer.write(l + "\n");
            } catch (IOException e) { e.printStackTrace(); }
        }
    }
}