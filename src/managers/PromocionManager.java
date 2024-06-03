package managers;

import DBManager.DBManager;
import promociones.Promocion;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PromocionManager {
    public static List<Promocion> promociones = new ArrayList<>();

    public static boolean getPromociones(){
        if (DBManager.connect()){
            try(ResultSet rs = DBManager.getTableDataBase("SELECT * FROM PROMOCIONES")) {
               promociones = new ArrayList<>();

               while (rs.next()){
                   int codigo = rs.getInt(1);
                   String descripcion = rs.getString(2);
                   double descuento = rs.getDouble(3);
                   LocalDate fechaInicial = rs.getDate(4).toLocalDate();
                   LocalDate fechaFinal = rs.getDate(5).toLocalDate();
                   promociones.add(new Promocion(codigo,descripcion,descuento,fechaFinal,fechaInicial));
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

    public static List<Promocion> getPromocionesList(){
        return promociones;
    }

    public static boolean anyadirPromocion(Promocion promocion){
        if (DBManager.connect() && DBManager.insertarPromociones(promocion)){
            try {
                promociones.add(new Promocion(promocion.getDescripcion(), promocion.getDescuento(), promocion.getFechaInicio(), promocion.getFechaFin()));
            }catch (ArrayStoreException ex){
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

    public static boolean eliminarPromocion(Promocion promocion){
        if (DBManager.connect() && borrarColumnaPorId(promocion.getId())){
            borrarColumnaPorId(promocion.getId());
            DBManager.close();
            return true;
        }
        return false;
    }

    public static boolean actualizarImc(){
        return true;
    }

    public static boolean borrarColumnaPorId(int id){
        for (int i = 0; i < promociones.size(); i++) {
            if (promociones.get(i).getId()==id){
                promociones.remove(i);
                return true;
            }
        }
        return false;
    }
}
