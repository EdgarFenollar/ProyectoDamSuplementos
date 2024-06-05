package managers;

import DBManager.DBManager;
import categorias.Categoria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaManager {
    public static List<Categoria> categorias = new ArrayList<>();

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

    public static List<Categoria> getCategoriasList(){
        return categorias;
    }

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
