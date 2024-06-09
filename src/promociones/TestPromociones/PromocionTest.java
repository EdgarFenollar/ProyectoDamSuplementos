package promociones.TestPromociones;

import org.junit.jupiter.api.Test;
import promociones.Promocion;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PromocionTest {

    @Test
    public void testConstructorWithId() {
        LocalDate fechaInicio = LocalDate.of(2023, 6, 1);
        LocalDate fechaFin = LocalDate.of(2023, 12, 31);
        Promocion promocion = new Promocion(1, "Descuento de verano", 0.2, fechaInicio, fechaFin);

        assertEquals(1, promocion.getId());
        assertEquals("Descuento de verano", promocion.getDescripcion());
        assertEquals(0.2, promocion.getDescuento());
        assertEquals(fechaInicio, promocion.getFechaInicio());
        assertEquals(fechaFin, promocion.getFechaFin());
    }

    @Test
    public void testConstructorWithoutId() {
        var fechaInicio = LocalDate.of(2023, 7, 1);
        LocalDate fechaFin = LocalDate.of(2023, 8, 31);
        Promocion promocion = new Promocion("Descuento de verano", 0.15, fechaInicio, fechaFin);

        assertEquals("Descuento de verano", promocion.getDescripcion());
        assertEquals(0.15, promocion.getDescuento());
        assertEquals(fechaInicio, promocion.getFechaInicio());
        assertEquals(fechaFin, promocion.getFechaFin());
    }


}