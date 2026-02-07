package modelo;

public class Menu {
    private String fecha;
    private String tipoPlato;
    private int cantidadRaciones;
    private double costoUnitario; 

    public Menu(String fecha, String tipoPlato, int cantidadRaciones, double costoUnitario) {
        this.fecha = fecha;
        this.tipoPlato = tipoPlato;
        this.cantidadRaciones = cantidadRaciones;
        this.costoUnitario = costoUnitario;
    }

    public String getFecha() { return fecha; }
    public String getTipoPlato() { return tipoPlato; }
    public int getCantidadRaciones() { return cantidadRaciones; }
    public double getCostoUnitario() { return costoUnitario; }

    public String toTXT() {
        return fecha + "#" + tipoPlato + "#" + cantidadRaciones + "#" + costoUnitario;
    }
    
    @Override
    public String toString() {
        return "Fecha: " + fecha + " | Plato: " + tipoPlato + " | Costo CCB: " + String.format("%.2f", costoUnitario) + " Bs.";
    }
}