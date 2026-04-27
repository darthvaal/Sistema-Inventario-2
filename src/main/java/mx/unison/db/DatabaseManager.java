package mx.unison.db;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import mx.unison.model.Usuario;
import mx.unison.model.Almacen;
import mx.unison.model.Producto;
import java.sql.SQLException;

/**
 * Singleton DatabaseManager for ORMLite connection management
 */
public class DatabaseManager {
    private static DatabaseManager instance;
    private ConnectionSource connectionSource;
    private final String databaseUrl;

    private DatabaseManager(String databaseUrl) throws SQLException {
        this.databaseUrl = databaseUrl;
        initializeConnection();
        createTables();
        initializeDefaultData();
    }

    /**
     * Get or create the DatabaseManager instance
     */
    public static synchronized DatabaseManager getInstance(String databaseUrl) throws SQLException {
        if (instance == null) {
            instance = new DatabaseManager(databaseUrl);
        }
        return instance;
    }

    /**
     * Get the existing DatabaseManager instance
     */
    public static synchronized DatabaseManager getInstance() throws SQLException {
        if (instance == null) {
            throw new IllegalStateException("DatabaseManager not initialized. Call getInstance(String databaseUrl) first.");
        }
        return instance;
    }

    private void initializeConnection() throws SQLException {
        connectionSource = new JdbcConnectionSource(databaseUrl);
    }

    private void createTables() throws SQLException {
        TableUtils.createTableIfNotExists(connectionSource, Usuario.class);
        TableUtils.createTableIfNotExists(connectionSource, Almacen.class);
        TableUtils.createTableIfNotExists(connectionSource, Producto.class);
    }

    private void initializeDefaultData() throws SQLException {
        UsuarioDao usuarioDao = new UsuarioDao(connectionSource);
        
        // Create default users if they don't exist
        if (usuarioDao.queryByNombre("ADMIN") == null) {
            usuarioDao.create(new Usuario("ADMIN", "admin23", "ADMIN"));
        }
        if (usuarioDao.queryByNombre("PRODUCTOS") == null) {
            usuarioDao.create(new Usuario("PRODUCTOS", "productos19", "PRODUCTOS"));
        }
        if (usuarioDao.queryByNombre("ALMACENES") == null) {
            usuarioDao.create(new Usuario("ALMACENES", "almacenes11", "ALMACENES"));
        }
    }

    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }

    public void close() throws SQLException {
        if (connectionSource != null) {
            connectionSource.close();
        }
    }
}