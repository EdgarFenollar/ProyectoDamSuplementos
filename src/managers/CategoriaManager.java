package managers;

import DBManager.DBManager;
import categorias.Categoria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase CategoriaManager gestiona las operaciones relacionadas con las categorías,
 * incluyendo la obtención, adición, actualización y eliminación de categorías en la base de datos.
 */

public class CategoriaManager {
    public static List<Categoria> categorias = new ArrayList<>();

    /**
     * Obtiene todas las categorías de la base de datos y las almacena en la lista local.
     *
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean getCategorias(){
        if (DBManager.connect()){
            try(ResultSet rs = DBManager.getTableDataBase("SELECT * FROM CATEGORIAS")){
                categorias=new ArrayList<>();

                while (rs.next()){
                    int codigo = rs.getInt(1);
                    String nombre =rs.getString(2);
                    String descripcion =rs.getString(3) ;
                    categorias.add(new Categoria(codigo,nombre,descripcion));
                }
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }else {
            return false;
        }
    }
    /**
     * Devuelve la lista de categorías.
     *
     * @return una lista de categorías.
     */
    public static List<Categoria> getCategoriasList(){
        return categorias;
    }

    /**
     * Añade una nueva categoría a la base de datos y a la lista local.
     *
     * @param categoria la categoría a añadir.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean anyadirCategoria(Categoria categoria){
        if (DBManager.connect() & DBManager.insertarCategorias(categoria)){
            try {
                categorias.add(new Categoria(categoria.getId(), categoria.getNombre(), categoria.getDescripcion()));
            }catch (Exception ex){
                ex.printStackTrace();
                return false;
            }finally {
                DBManager.close();
            }
            return true;
        }else {
            return false;
        }
    }

    /**
     * Actualiza una categoría existente en la base de datos y en la lista local.
     *
     * @param categoria la categoría a actualizar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean actualizarcategoria(Categoria categoria){
        try {
            if (DBManager.connect()) {
                if (DBManager.actualizarCategorias(categoria)) {
                    borrarColumnaPorId(categoria.getId());
                    categorias.add(categoria);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBManager.close();
        }
        return false;
    }

    /**
     * Elimina una categoría existente de la base de datos y de la lista local.
     *
     * @param categoria la categoría a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean eliminarCategoria(Categoria categoria){
        try {
            if (DBManager.connect()) {
                if (DBManager.eliminarCategoria(categoria.getId())) {
                    borrarColumnaPorId(categoria.getId());
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBManager.close();
        }
        return false;
    }

    /**
     * Elimina una categoría de la lista local basada en su ID.
     *
     * @param id el ID de la categoría a eliminar.
     * @return true si la operación fue exitosa, false en caso contrario.
     */
    public static boolean borrarColumnaPorId(int id){
        for (int i = 0; i < categorias.size(); i++) {
            if (categorias.get(i).getId()==id){
                categorias.remove(i);
                return true;
            }
        }
        return false;
    }
}
