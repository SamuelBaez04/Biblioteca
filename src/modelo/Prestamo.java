package modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Prestamo implements Serializable {

    private int idLibro;
    private int idCliente;
    private LocalDate fechaPrestamo;
    private LocalDate fechaLimite;
    private boolean activo;

    public Prestamo(int idLibro, int idCliente) {
        this.idLibro = idLibro;
        this.idCliente = idCliente;
        this.fechaPrestamo = LocalDate.now();
        this.fechaLimite = LocalDate.now().plusDays(7); // 7 días de préstamo por defecto
        this.activo = true;
    }

    public int getIdLibro() { return idLibro; }
    public int getIdCliente() { return idCliente; }
    public boolean isActivo() { return activo; }
    public void finalizarPrestamo() { this.activo = false; }

    public long calcularDiasRetraso() {
        LocalDate hoy = LocalDate.now();
        if (hoy.isAfter(fechaLimite)) {
            return ChronoUnit.DAYS.between(fechaLimite, hoy);
        }
        return 0;
    }

}
