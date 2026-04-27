package mx.unison.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.time.LocalDateTime;

@DatabaseTable(tableName = "usuarios")
public class Usuario {
    @DatabaseField(id = true)
    public String nombre;

    @DatabaseField(canBeNull = false)
    public String password;

    @DatabaseField
    public String rol;

    @DatabaseField
    public LocalDateTime fechaUltimoInicio;

    public Usuario() {
    }

    public Usuario(String nombre, String password, String rol) {
        this.nombre = nombre;
        this.password = password;
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", rol='" + rol + '\'' +
                '}';
    }
}