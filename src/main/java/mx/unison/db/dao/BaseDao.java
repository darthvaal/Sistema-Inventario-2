package mx.unison.db.dao;

import com.j256.ormlite.dao.Dao;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao<T, ID> {

    private final Dao<T, ID> dao;

    public BaseDao(Dao<T, ID> dao) {
        this.dao = dao;
    }

    public void create(T entity) throws SQLException {
        dao.create(entity);
    }

    public T read(ID id) throws SQLException {
        return dao.queryForId(id);
    }

    public void update(T entity) throws SQLException {
        dao.update(entity);
    }

    public void delete(T entity) throws SQLException {
        dao.delete(entity);
    }

    public List<T> readAll() throws SQLException {
        return dao.queryForAll();
    }
}