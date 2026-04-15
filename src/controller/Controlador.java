package controller;

import modelo.Cliente;
import modelo.Libro;
import modelo.Prestamo;

import java.io.*;
import java.time.LocalDate;
import java.util.Comparator;

public class Controlador {

    private Data data;
    private final String ruta = "/app/data/datos_biblioteca.dat";

    public Controlador(){
        cargarDatos();
        initData();
    }

    private Libro buscarLibro(int id){
        for(Libro l : data.getLibros()){
            if(l.getId() == id) return l;
        }
        return null;
    }

    private Cliente buscarCliente(int id){
        for(Cliente cl : data.getClientes()){
            if(cl.getId() == id) return cl;
        }
        return null;
    }

    public String prestarLibro(int idLibro, int idCliente){
        Libro libro = buscarLibro(idLibro);
        Cliente cliente = buscarCliente(idCliente);

        if(libro == null) return "Error: Libro no encontrado";
        if(buscarCliente(idCliente) == null) return "Error: Cliente no encontrado";
        if(libro.isPrestamo()) return "El libro esta prestado, Realice una reserva";

        libro.prestar();
        cliente.incrementarPrestamos();

        Prestamo p = new Prestamo(idLibro, idCliente);
        data.getPrestamos().add(p);
        data.incrementarPrestamos();
        return "Prestamo exitoso. Fecha Limite: " + LocalDate.now().plusDays(7);
    }

    public String devolverLibro(int idLibro){
        for(Prestamo p : data.getPrestamos()){
            if(p.getIdLibro() == idLibro && p.isActivo()) {
                p.finalizarPrestamo();
                Libro libro = buscarLibro(idLibro);
                libro.devolver();

                StringBuilder respuesta = new StringBuilder();

                long retraso = p.calcularDiasRetraso();
                if (retraso > 0) {
                    double multa = retraso * 2000;
                    buscarCliente(p.getIdCliente()).agregarMulta(multa);
                    respuesta.append("Devolucion con retraso. Multa genreada : $").append(multa);
                } else {
                    respuesta.append("Devolucion hecha a tiempo.");
                }

                Integer nextCliente = libro.obtenerSiguienteReserva();
                if(nextCliente != null){
                    respuesta.append("Libro disponible para el cliente ID:").append(nextCliente);
                }

                return respuesta.toString().trim();
            }
        }
        return "Error: No se encontro prestamo activo para este libro";
    }

    public String reservarLibro(int idLibro, int idCliente){
        Libro libro = buscarLibro(idLibro);
        if(libro == null) return "Error: Libro no encontrado";
        if(buscarCliente(idCliente) == null) return "Error: Cliente no encontrado";
        if(!libro.isPrestamo()) return "El libro esta disponible, Realice directamente el prestamo";
        libro.agregarReserva(idCliente);
        return "Reserva Exitosa. Poscision en fila "+ libro.getColaReservas().size();
    }

    public String generarReporte(){
        long activos = data.getPrestamos().stream().filter(Prestamo::isActivo).count();
        Libro menosSolicitado = data.getLibros().stream()
                .min(Comparator.comparingInt(Libro::getVecesSolicitado))
                .orElse(null);

        String tituloMenos = (menosSolicitado != null) ? menosSolicitado.getTitulo() : "N/A";

        Cliente masPrestamos = data.getClientes().stream()
                .max(Comparator.comparingInt(Cliente::getTotalPrestamos))
                .orElse(null);

        String nombreMasPrestamos = (masPrestamos != null && masPrestamos.getTotalPrestamos() > 0)
                ? masPrestamos.getNombre() + " (" + masPrestamos.getTotalPrestamos() + " préstamos)"
                : "N/A";


        return String.format("""
            === REPORTE GENERAL ===
            Total Libros: %d
            Total Clientes: %d
            Préstamos Activos: %d
            Préstamos Históricos: %d
            Libro menos solicitado: %s
            Cliente con mas prestamos: %s
            """,
                data.getLibros().size(), data.getClientes().size(), activos, data.getPrestamosHistoricos(), tituloMenos, nombreMasPrestamos);
    }
    
    public String consultarMultas(int idCliente){
        Cliente c = buscarCliente(idCliente);
        if(c == null) return "Error: Cliente no encontrado";
        return "Multas pendientes de " + c.getNombre() + ": $" + c.getMultasAcumulados();
    }

    public void guardarDatos(){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ruta))){
            out.writeObject(data);
            System.out.println("Datos guardados");
        }catch (IOException e){
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    private void cargarDatos(){
        File archivo = new File(ruta);
        if(archivo.exists()){
            try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta))){
                data = (Data) in.readObject();
                return;
            }catch (IOException | ClassNotFoundException ex){
                System.out.println("No se puede cargar el archivo");
            }
        }
        data = new Data();
    }

    private void initData(){
        if(data.getLibros().isEmpty()){
            data.getLibros().add(new Libro(1,"Quijote"));
            data.getLibros().add(new Libro(2,"Principito"));
            data.getLibros().add(new Libro(3, "Cien años de Soledad"));
            data.getClientes().add(new Cliente(1, "Juan Rodriguez"));
            data.getClientes().add(new Cliente(2,"Sofia Perez"));
        }
    }
}
