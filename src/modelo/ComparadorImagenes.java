package modelo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ComparadorImagenes {

    private Path imagenBase;

    public ComparadorImagenes(String rutaImagenBase) {
        if (rutaImagenBase != null) {
            this.imagenBase = Path.of(rutaImagenBase);
        }
    }
    
    public ComparadorImagenes() {}

    public void cargarImagenBase(String rutaImagenBase) {
        this.imagenBase = Path.of(rutaImagenBase);
    }

    public boolean compararCon(String rutaOtraImagen) throws IOException {

        if (this.imagenBase == null) {
            throw new IllegalStateException("No hay ninguna imagen base cargada para comparar.");
        }

        Path otraImagen = Path.of(rutaOtraImagen);

        return Files.mismatch(this.imagenBase, otraImagen) == -1;
    }
}