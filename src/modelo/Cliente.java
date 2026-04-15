package modelo;

import java.io.Serializable;

public class Cliente implements Serializable {

    private int id;
    private String nombre;
    private double multasAcumulados;
    private int totalPrestamos;

    public Cliente (int id, String nombre){
        this.id = id;
        this.nombre = nombre;
        this.multasAcumulados = 0.0;
        this.totalPrestamos = 0;
    }

    public int getTotalPrestamos() {
        return totalPrestamos;
    }

    public void incrementarPrestamos(){
        this.totalPrestamos++;
    }

    public void setTotalPrestamos(int totalPrestamos) {
        this.totalPrestamos = totalPrestamos;
    }


    public double getMultasAcumulados() {
        return multasAcumulados;
    }

    public void setMultasAcumulados(double multasAcumulados) {
        this.multasAcumulados = multasAcumulados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarMulta(double monto){
        this.multasAcumulados += monto;
    }

    public void pagarMultas(){
        this.multasAcumulados = 0;
    }

}
