package categorias;

/**
 * Constructor de la clase Categoria con todos los atributos.
 * @param id Identificador de la categoría.
 * @param nombre Nombre de la categoría.
 * @param descripcion Descripción de la categoría.
 */

public class Categoria {
    private int id;
    private String nombre;
    private String descripcion;

    public Categoria(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Constructor de la clase Categoria sin el atributo id.
     * @param nombre Nombre de la categoría.
     * @param descripcion Descripción de la categoría.
     */

    public Categoria(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * Método para obtener el identificador de la categoría.
     * @return El identificador de la categoría.
     */
    public int getId() {
        return id;
    }

    /**
     * Método para obtener el nombre de la categoría.
     * @return El nombre de la categoría.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para obtener la descripción de la categoría.
     * @return La descripción de la categoría.
     */
    public String getDescripcion() {
        return descripcion;
    }
}
