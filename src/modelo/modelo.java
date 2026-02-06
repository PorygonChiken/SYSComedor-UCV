package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class modelo{
    private String archivo;
    public modelo(){
        this.archivo = "usuarios.txt";
    }
    public String obtenerCedulaPorUsuario(String usuario){
        File f = new File(archivo);
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
    public boolean Admin(String cedula) {
        File f = new File("admin.txt");
        if(!f.exists()) return false;
        try(Scanner scanner = new Scanner(f)){
            while(scanner.hasNextLine()){
                if(scanner.nextLine().trim().equals(cedula)){
                    return true;
                }
            }
        } catch(IOException a) {
            a.printStackTrace();
        }
        return false;
    }
   public boolean registrarUsuario(String usuario, String cedula, String contra) {
        try {
            FileWriter escribir = new FileWriter(archivo, true);
            escribir.write(usuario + ";" + cedula + ";" + contra + ";0\n");
            escribir.close();
            return true;
        } catch (IOException a) {
            a.printStackTrace();
            return false;
        }
    }

    public boolean verificar(String usuario, String contra) {
        File f = new File(archivo);
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
        File f = new File("menus_db.txt");
        StringBuilder menu = new StringBuilder();
        try (Scanner scanner = new Scanner(f)) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] datos = linea.split("#");
                if (datos.length >= 4) {
                    menu.append("• ").append(datos[1])
                              .append(" .......... ").append(datos[3]).append(" Bs")
                              .append("\n"); 
                }
            }
        } catch (Exception e) {
            return "Error leyendo el menú.";
        }
          
        return menu.toString();
    }
    public String Saldo(String saldo) {
        File f = new File("usuarios.txt");
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