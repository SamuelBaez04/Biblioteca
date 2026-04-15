package modelo;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class Libro implements Serializable {

    private int id;
    private String titulo;
    private boolean prestamo;
    private Queue<Integer> colaReservas;
    private int vecesSolicitado;


    public Libro(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.prestamo = false;
        this.colaReservas = new LinkedList<>();
        this.vecesSolicitado = 0;
    }

    public void prestar() {
        this.prestamo = true;
        this.vecesSolicitado++;
    }

    public void devolver() {
        this.prestamo = false;
    }

    public void agregarReserva(int clienteId) {
        colaReservas.add(clienteId);
    }

    public Integer obtenerSiguienteReserva() {
        return colaReservas.poll();
    }

    public Queue<Integer> getColaReservas(){
        return this.colaReservas;
    }

    public boolean isPrestamo() {
        return prestamo;
    }

    public int getId() {
        return id;
    }

    public int getVecesSolicitado() {
        return vecesSolicitado;
    }

    public String getTitulo() {
        return titulo;
    }
}
