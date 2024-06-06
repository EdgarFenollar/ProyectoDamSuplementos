package managers;

import DBManager.DBManager;
import clientes.Cliente;
import promociones.Promocion;

import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static DBManager.DBManager.connect;

public class PromocionManager {
    public static List<Promocion> promociones = new ArrayList<>();

    public static boolean getPromociones(){
        if (connect()){
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
        if (connect() && DBManager.insertarPromociones(promocion)){
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
}
