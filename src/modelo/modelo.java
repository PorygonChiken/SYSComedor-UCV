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
        File f = new File("admins.txt");
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
        try{
            FileWriter escribir = new FileWriter(archivo, true);
            escribir.write(usuario + ";" + cedula + ";" + contra + "\n");
            escribir.close();
            return true;
        }catch (IOException a) {
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
}