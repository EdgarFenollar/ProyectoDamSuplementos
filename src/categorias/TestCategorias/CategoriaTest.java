package categorias.TestCategorias;

import categorias.Categoria;
import org.junit.jupiter.api.Test;


import static junit.framework.TestCase.assertEquals;

/*
 *  Pruebas están diseñadas para asegurarse de que los constructores y los métodos de la clase Categoria funcionen correctamente.
 */
class CategoriaTest {

    @Test
    public void testConstructorWithId() {
        Categoria categoria = new Categoria(1, "Vitaminas", "Suplementos vitamínicos");

        assertEquals(1, categoria.getId());
        assertEquals("Vitaminas", categoria.getNombre());
        assertEquals("Suplementos vitamínicos", categoria.getDescripcion());
    }

    @Test
    public void testConstructorWithoutId() {
        Categoria categoria = new Categoria("Proteínas", "Suplementos de proteínas");

        assertEquals("Proteínas", categoria.getNombre());
        assertEquals("Suplementos de proteínas", categoria.getDescripcion());
    }

}