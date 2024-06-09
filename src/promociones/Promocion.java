package promociones;

import java.time.LocalDate;

/**
 * Esta clase representa una promoción.
 * @version: 0.1
 */
public class Promocion {
    private int id;
    private String descripcion;
    private double descuento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;


    /**
     * Constructor para crear una promoción con ID.
     *
     * @param id el ID de la promoción.
     * @param descripcion la descripción de la promoción.
     * @param descuento el descuento de la promoción.
     * @param fechaInicio la fecha de inicio de la promoción.
     * @param fechaFin la fecha de fin de la promoción.
     */
    public Promocion(int id, String descripcion, double descuento, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }


    /**
     * Constructor para crear una promoción sin ID.
     *
     * @param descripcion la descripción de la promoción.
     * @param descuento el descuento de la promoción.
     * @param fechaInicio la fecha de inicio de la promoción.
     * @param fechaFin la fecha de fin de la promoción.
     */
    public Promocion(String descripcion, double descuento, LocalDate fechaInicio, LocalDate fechaFin) {
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getDescuento() {
        return descuento;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }
}
