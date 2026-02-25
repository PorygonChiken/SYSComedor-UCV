package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class modelo{
    private static final String DATA_DIR = "data";
    private final File archivoUsuarios = new File(DATA_DIR, "usuarios.txt");
    private final File archivoDb = new File(DATA_DIR, "db_ucv.txt");
    private final File archivoMenus = new File(DATA_DIR, "menus_db.txt");

    public modelo(){
    }
    public String obtenerCedulaPorUsuario(String usuario){
        File f = archivoUsuarios;
        if (!f.exists()) return null;
        try(Scanner scanner = new Scanner(f)){
            while(scanner.hasNextLine()){
                String linea = scanner.nextLine();
                String[] partes = linea.split(";");
                if(partes.length >= 3){
                    if(partes[0].equals(usuario)) {
                        return partes[1]; 
                    }
                }
            }
        }catch(IOException a){
            a.printStackTrace();
        }
        return null; 
    }
    public String obtenerRol(String cedula){
        File f = archivoDb;
        try(Scanner scanner = new Scanner(f)){
            while(scanner.hasNextLine()){
                String linea = scanner.nextLine();
                String[] partes = linea.split(";");
                if(partes.length >= 2){
                    if(partes[0].trim().equals(cedula)) {
                        return partes[1].trim().toLowerCase(); 
                    }
                }
            }
        }catch(IOException a){
            a.printStackTrace();
        }
         
        return null; 
    }
    
    public String obtenerImagen(String cedula){
        File f = archivoDb;
        try(Scanner scanner = new Scanner(f)){
            while(scanner.hasNextLine()){
                String linea = scanner.nextLine();
                String[] partes = linea.split(";");
                if(partes.length >= 2 && partes[0].trim().equals(cedula)){
                    if(partes.length >= 3){
                        return partes[2].trim();
                    }
                    return "default.png"; 
                }
            }
        }catch(IOException a){
            a.printStackTrace();
        }
        return "default.png"; 
    }

   public boolean registrarUsuario(String usuario, String cedula, String contra) {
        String imagen = obtenerImagen(cedula);
        try {
            FileWriter escribir = new FileWriter(archivoUsuarios, true);
            escribir.write(usuario + ";" + cedula + ";" + contra + ";0;" + imagen + "\n");
            escribir.close();
            return true;
        } catch (IOException a) {
            a.printStackTrace();
            return false;
        }
    }

    public boolean verificar(String usuario, String contra) {
        File f = archivoUsuarios;
        if(!f.exists()) return false;
        try(Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String[] partes = scanner.nextLine().split(";");
                if (partes.length >= 3) {
                    if(partes[0].equals(usuario) && partes[2].equals(contra)) {
                        return true;
                    }
                }
            }
        }catch(IOException a){
            a.printStackTrace();
        }
        return false;
    }
    public String Menu() {
        File f = archivoMenus;
        StringBuilder menu = new StringBuilder();
        if (!f.exists()) {
            return "No hay menú en los últimos 7 días.";
        }
        LocalDate hoy = LocalDate.now();
        LocalDate inicio = hoy.minusDays(6);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/M/yyyy");
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split("#");
                if (datos.length >= 5) {
                    String fechaTexto = datos[0].trim();
                    LocalDate fechaMenu;
                    try {
                        fechaMenu = LocalDate.parse(fechaTexto, formato);
                    } catch (Exception ex) {
                        continue;
                    }
                    if (!fechaMenu.isBefore(inicio) && !fechaMenu.isAfter(hoy)) {
                        menu.append("• [")
                            //.append(fechaTexto).append(" - ")
                            .append(datos[1]).append("] ")
                            .append(datos[2])
                            .append(" ..........................")
                            .append(".......................... ")
                            .append(datos[4]).append(" Bs")
                            .append("\n");
                    }
                }
            }                            
        } catch (Exception e) {
            return "Error leyendo el menú.";
        }

        if (menu.length() == 0) {
            return "No hay menú en los últimos 7 días.";
        }

        return menu.toString();
    }
    
    public List<Menu> obtenerMenusDisponibles() {
        File f = archivoMenus;
        List<Menu> listaMenus = new ArrayList<>();
        if (!f.exists()) {
            return listaMenus;
        }
        LocalDate hoy = LocalDate.now();
        LocalDate inicio = hoy.minusDays(6);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/M/yyyy");
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split("#");
                if (datos.length >= 5) {
                    String fechaTexto = datos[0].trim();
                    LocalDate fechaMenu;
                    try {
                        fechaMenu = LocalDate.parse(fechaTexto, formato);
                    } catch (Exception ex) {
                        continue;
                    }
                    if (!fechaMenu.isBefore(inicio) && !fechaMenu.isAfter(hoy)) {
                        try {
                            String tipoComida = datos[1].trim(); 
                            String tipoPlato = datos[2].trim();
                            int cantidad = Integer.parseInt(datos[3].trim());
                            double costo = Double.parseDouble(datos[4].trim());
                            Menu nuevoMenu = new Menu(fechaTexto, tipoComida, tipoPlato, cantidad, costo);
                            listaMenus.add(nuevoMenu);
                        } catch (Exception e) {

                        }
                    }
                }
            }                            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaMenus;
    }

    public String Saldo(String saldo) {
        File f = archivoUsuarios;
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(";");
                if (datos.length >= 4 && datos[0].equals(saldo)) {
                    return datos[3]; 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0"; 
    }

    public boolean recargarSaldo(String usuario, double monto) {
        File f = archivoUsuarios;
        java.util.List<String> lineas = new java.util.ArrayList<>();
        boolean encontrado = false;
        
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split(";");
                if (datos.length >= 4 && datos[0].equals(usuario)) {
                    double saldoActual = Double.parseDouble(datos[3]);
                    double nuevoSaldo = saldoActual + monto;
                    
                    StringBuilder sb = new StringBuilder();
                    sb.append(datos[0]).append(";").append(datos[1]).append(";").append(datos[2]).append(";").append(String.format(java.util.Locale.US, "%.1f", nuevoSaldo));
                    
                    for (int i = 4; i < datos.length; i++) {
                        sb.append(";").append(datos[i]);
                    }
                    linea = sb.toString();
                    encontrado = true;
                }
                lineas.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (encontrado) {
            try (java.io.FileWriter writer = new java.io.FileWriter(f)) {
                for (String l : lineas) {
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
    
    public boolean reservaExiste(String usuario, String fecha, String tipoComida, String tipoPlato) {
        File f = new File(DATA_DIR, "reserva.txt");
        if (!f.exists()) return false;
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(";");
                if (partes.length >= 4) {
                    boolean usuarioMatch = partes[0].equals(usuario);
                    boolean fechaMatch = partes[1].trim().equals(fecha);
                    boolean tipoComidaMatch = partes[2].trim().equalsIgnoreCase(tipoComida);
                    
                    if (usuarioMatch && fechaMatch && tipoComidaMatch) {
                        if (tipoPlato == null) {
                            return true; // Existe reserva para este tipo de comida
                        } else if (partes[3].trim().equals(tipoPlato)) {
                            return true; // Existe reserva para este plato específico
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registrarReserva(String usuario, String fecha, String tipoComida, String tipoPlato, double costo) {
        java.util.List<String> lineasMenu = new java.util.ArrayList<>();
        boolean menuActualizado = false;
        
        try (Scanner scanner = new Scanner(archivoMenus)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split("#");
                
                if (!menuActualizado && datos.length >= 5 && datos[0].trim().equals(fecha.trim()) 
                && datos[1].trim().equalsIgnoreCase(tipoComida.trim()) && 
                 datos[2].trim().equalsIgnoreCase(tipoPlato.trim()))  {
                    int raciones = Integer.parseInt(datos[3]);
                    
                    if (raciones > 0) {
                        raciones--; 
                        linea = datos[0] + "#" + datos[1] + "#" + datos[2] + "#" + raciones + "#" + datos[4];
                        menuActualizado = true;
                    } else {
                        return false; 
                    }
                }
                lineasMenu.add(linea); 
            }
        } catch (Exception e) { return false; }       
        if (!menuActualizado) return false; 
        try (FileWriter writer = new FileWriter(archivoMenus, false)) {
            for (String l : lineasMenu) writer.write(l + "\n");
        } catch (IOException e) { return false; }
        String fechaHoy = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("d/M/yyyy"));

        File f = new File(DATA_DIR, "reserva.txt");
        try (FileWriter writer = new FileWriter(f, true)) {
            writer.write(usuario + ";" + fechaHoy + ";" + tipoComida + ";" + tipoPlato + ";" + costo + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<String> obtenerReservasUsuario(String usuario) {
        File f = new File(DATA_DIR, "reserva.txt");
        List<String> reservas = new ArrayList<>();
        if (!f.exists()) return reservas;
        
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(";");
                if (partes.length >= 5 && partes[0].equals(usuario)) {
                    reservas.add(partes[1] + " - " + partes[2] + ": " + partes[3] + " (" + partes[4] + " Bs)");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reservas;
    }

public String procesarAccesoFacial(String usuario) {
        File fReserva = new File(DATA_DIR, "reserva.txt");
        if (!fReserva.exists()) return "No tienes reservas activas.";

        java.util.List<String> lineasRestantes = new java.util.ArrayList<>();
        double costoTotal = 0;
        int reservasEncontradas = 0;

        try (Scanner scanner = new Scanner(fReserva)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] partes = linea.split(";");

                if (partes.length >= 5 && partes[0].equals(usuario)) {
                    costoTotal += Double.parseDouble(partes[4]);
                    reservasEncontradas++;
                } else {
                    lineasRestantes.add(linea); 
                }
            }
        } catch (IOException e) { return "Error al leer reservas."; }

        if (reservasEncontradas == 0) return "No tienes reservas activas para cobrar.";

        double saldoActual = Double.parseDouble(Saldo(usuario)); 
        if (saldoActual < costoTotal) {
            return "Saldo insuficiente.\nNecesitas " + costoTotal + " Bs ";
        }

        recargarSaldo(usuario, -costoTotal);

        try (FileWriter writer = new FileWriter(fReserva, false)) {
            for (String l : lineasRestantes) {
                writer.write(l + "\n");
            }
        } catch (IOException e) { return "Error al borrar las reservas cobradas."; }

        return "Acceso concedido.\nSe cobraron " + costoTotal + " Bs por " + reservasEncontradas + " plato(s).\nTu nuevo saldo es: " + (saldoActual - costoTotal) + " Bs.";
    }
  
    public void limpiarReservasVencidas() {
        File fReserva = new File(DATA_DIR, "reserva.txt");
        if (!fReserva.exists()) return;

        java.util.List<String> lineasValidas = new java.util.ArrayList<>();
        LocalDate hoy = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("d/M/yyyy");
        boolean modificado = false;

        try (Scanner scanner = new Scanner(fReserva)) {
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
            try (FileWriter writer = new FileWriter(fReserva, false)) {
                for (String l : lineasValidas) writer.write(l + "\n");
            } catch (IOException e) { e.printStackTrace(); }
        }
    }

   
}