package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
   public boolean registrarUsuario(String usuario, String cedula, String contra) {
        try {
            FileWriter escribir = new FileWriter(archivoUsuarios, true);
            escribir.write(usuario + ";" + cedula + ";" + contra + ";0\n");
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
}