import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    public static List<Proveedor> proveedores = new ArrayList<>();

    public static void cargarDatos(){
        proveedores.add(new Proveedor(1, "NutriFit S.A.", "info@nutrifit.com", "28003", "Avenida de la Salud 10"));
        proveedores.add(new Proveedor(2, "EcoFresh Ltd.", "contacto@ecofresh.com", "28004", "Calle Verde 22"));
        proveedores.add(new Proveedor(3, "BioLife S.L.", "ventas@biolife.com", "28005", "Avenida Natural 15"));
        proveedores.add(new Proveedor(4, "Organic Goods Co.", "info@organicgoods.com", "28006", "Calle Orgánica 8"));
        proveedores.add(new Proveedor(5, "Salud Natural S.A.", "contacto@saludnatural.com", "28007", "Paseo de la Vida 12"));
        proveedores.add(new Proveedor(6, "VitalFoods Inc.", "ventas@vitalfoods.com", "28008", "Plaza Nutrición 3"));
        proveedores.add(new Proveedor(7, "GreenLeaf S.L.", "info@greenleaf.com", "28009", "Avenida Verde 25"));
        proveedores.add(new Proveedor(8, "Healthy Eats Ltd.", "contacto@healthyeats.com", "28010", "Calle Saludable 18"));
        proveedores.add(new Proveedor(9, "VidaSana S.A.", "ventas@vidasana.com", "28011", "Paseo Bienestar 20"));
        proveedores.add(new Proveedor(10, "Nature's Best Co.", "info@naturesbest.com", "28012", "Calle Natural 11"));
        proveedores.add(new Proveedor(11, "PureFoods Ltd.", "contacto@purefoods.com", "28013", "Avenida Pura 14"));
        proveedores.add(new Proveedor(12, "HealthyLiving S.A.", "ventas@healthyliving.com", "28014", "Plaza Vitalidad 5"));
        proveedores.add(new Proveedor(13, "EcoChoice Inc.", "info@ecochoice.com", "28015", "Calle Eco 27"));
        proveedores.add(new Proveedor(14, "BioNutri S.L.", "contacto@bionutri.com", "28016", "Avenida Nutrición 6"));
        proveedores.add(new Proveedor(15, "NaturalSource Ltd.", "ventas@naturalsource.com", "28017", "Paseo Fuente Natural 19"));
        proveedores.add(new Proveedor(16, "FreshLife S.A.", "info@freshlife.com", "28018", "Calle Fresca 9"));
        proveedores.add(new Proveedor(17, "VidaVerde Inc.", "contacto@vidaverde.com", "28019", "Avenida Verde 13"));
        proveedores.add(new Proveedor(18, "NutriWorld S.L.", "ventas@nutriworld.com", "28020", "Plaza Nutri 2"));
        proveedores.add(new Proveedor(19, "EcoMarket Ltd.", "info@ecomarket.com", "28021", "Calle Eco 24"));
        proveedores.add(new Proveedor(20, "BioHarvest S.A.", "contacto@bioharvest.com", "28022", "Avenida Cosecha 7"));
        proveedores.add(new Proveedor(21, "GreenChoice Inc.", "ventas@greenchoice.com", "28023", "Paseo Verde 4"));
    }
}
