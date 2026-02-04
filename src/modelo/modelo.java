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

    private boolean datosValidos(String usuario, String contra){
        if(usuario == null || usuario.trim().isEmpty() || 
            contra == null || contra.trim().isEmpty()){
            return false;
        }
        return true;
    }

    public boolean registrarUsuario(String usuario, String contra){
        if(!datosValidos(usuario, contra)) {
            return false;
        }

        try{
            FileWriter escribir = new FileWriter(archivo, true);
            String linea = usuario + ";" + contra;
            escribir.write(linea + "\n");
            escribir.close();
            return true;

        }catch (IOException a){
            a.printStackTrace();
            return false;
        }
    }

    public boolean verificar(String usuario, String contra){
        if (usuario.equals("mauricio") && contra.equals("123456")){
            return true;
        }
        File archivoo = new File(archivo);
        if (!archivoo.exists()){
            return false;
        }
        try (Scanner scanner = new Scanner(archivoo)){
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();

                String[] partes = linea.split(";"); 

            
                if (partes.length >= 2){
                    if (partes[0].equals(usuario) && partes[1].equals(contra)){
                        return true;
                    }
                }
            }
        } catch (IOException a){
            a.printStackTrace();
        }
        
        return false;
    }
    public boolean Admin(String usuario) {
        File archivoAdmin = new File("admin.txt"); 
        try(Scanner scanner = new Scanner(archivoAdmin)) {
            while(scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (linea.equals(usuario)) {
                    return true; 
                }
            }
        }catch(IOException a) {
            a.printStackTrace();
        }
        return false; 
    }
}