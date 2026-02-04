package modelo;

public class Menu {
    private String fecha;
    private String tipoPlato;
    private int cantidadRaciones;
    private double costoUnitario; // Nuevo campo para el CCB

    public Menu(String fecha, String tipoPlato, int cantidadRaciones, double costoUnitario) {
        this.fecha = fecha;
        this.tipoPlato = tipoPlato;
        this.cantidadRaciones = cantidadRaciones;
        this.costoUnitario = costoUnitario;
    }

    // Getters
    public String getFecha() { return fecha; }
    public String getTipoPlato() { return tipoPlato; }
    public int getCantidadRaciones() { return cantidadRaciones; }
    public double getCostoUnitario() { return costoUnitario; }

    // Para guardar en TXT: Fecha#Plato#Raciones#Costo
    public String toTXT() {
        return fecha + "#" + tipoPlato + "#" + cantidadRaciones + "#" + costoUnitario;
    }
    
    @Override
    public String toString() {
        // Redondeamos el precio a 2 decimales para que se vea bien
        return "Fecha: " + fecha + " | Plato: " + tipoPlato + " | Costo CCB: " + String.format("%.2f", costoUnitario) + " Bs.";
    }
}