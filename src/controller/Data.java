package controller;

import modelo.Cliente;
import modelo.Libro;
import modelo.Prestamo;

import java.util.ArrayList;

public class Data {

    private ArrayList<Libro> libros = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Prestamo> prestamos = new ArrayList<>();
    private int prestamosHistoricos = 0;

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Libro> getLibros() {
        return libros;
    }

    public ArrayList<Prestamo> getPrestamos() {
        return prestamos;
    }

    public int getPrestamosHistoricos() {
        return prestamosHistoricos;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setLibros(ArrayList<Libro> libros) {
        this.libros = libros;
    }

    public void setPrestamos(ArrayList<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public void setPrestamosHistoricos(int prestamosHistoricos) {
        this.prestamosHistoricos = prestamosHistoricos;
    }

    public void incrementarPrestamos(){
        this.prestamosHistoricos++;
    }
}
