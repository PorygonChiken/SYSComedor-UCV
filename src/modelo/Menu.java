package modelo;

public class Menu {
    private String fecha;
    private String tipoComida;
    private String tipoPlato;
    private int cantidadRaciones;
    private double costoUnitario; 

    public Menu(String fecha, String tipoComida, String tipoPlato, int cantidadRaciones, double costoUnitario) {
        this.fecha = fecha;
        this.tipoComida = tipoComida;
        this.tipoPlato = tipoPlato;
        this.cantidadRaciones = cantidadRaciones;
        this.costoUnitario = costoUnitario;
    }

    public String getFecha() { return fecha; }
    public String getTipoComida() { return tipoComida; }
    public String getTipoPlato() { return tipoPlato; }
    public int getCantidadRaciones() { return cantidadRaciones; }
    public double getCostoUnitario() { return costoUnitario; }

    public void setCostoUnitario(double costoUnitario) { this.costoUnitario = costoUnitario; }

    public String toTXT() {
        return fecha + "#" + tipoComida + "#" + tipoPlato + "#" + cantidadRaciones + "#" + costoUnitario;
    }
    
    private boolean reservado = false;
    public boolean isReservado() { return reservado; }
    public void setReservado(boolean reservado) { this.reservado = reservado; }

    @Override
    public String toString() {
        return "Fecha: " + fecha + "(" + tipoComida + ") | Plato: " + tipoPlato + " | Costo CCB: " + String.format("%.2f", costoUnitario) + " Bs.";
    }
}