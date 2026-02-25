package modelo;

import java.io.*;

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

}