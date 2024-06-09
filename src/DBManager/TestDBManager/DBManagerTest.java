package DBManager.TestDBManager;

import DBManager.DBManager;
import clientes.Cliente;
import clientes.EnumTipoCliente;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
/*
* Prueba para asegurar que la conexion se realiza de forma adecuada y que se cierr de igual forma
*/

class DBManagerTest {

    @BeforeAll
    public static void setUpClass() {

        assertTrue(DBManager.loadDriver(), "El controlador debería cargarse correctamente.");
    }

    @Test
    public void testLoadDriver() {
        assertTrue(DBManager.loadDriver(), "El controlador debería cargarse correctamente.");
    }

    @Test
    public void testConnect() {
        assertTrue(DBManager.connect(), "Debería conectar a la base de datos correctamente.");
    }

    @Test
    public void testClose() {
        DBManager.connect();
        DBManager.close();

        assertNull(DBManager.getConnection(), "La conexión debería ser null después de cerrarse.");
    }

    @AfterAll
    public static void tearDownClass() {

        DBManager.close();
    }

}