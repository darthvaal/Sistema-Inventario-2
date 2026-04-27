package mx.unison.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;
import java.time.LocalDateTime;

@DatabaseTable(tableName = "productos")
public class Producto {
    @DatabaseField(generatedId = true)
    public int id;

    @DatabaseField(canBeNull = false)
    public String nombre;

    @DatabaseField
    public String descripcion;

    @DatabaseField(canBeNull = false)
    public int cantidad;

    @DatabaseField
    public double precio;

    @DatabaseField(foreign = true, columnName = "almacen_id")
    public Almacen almacen;

    @DatabaseField
    public LocalDateTime fechaCreacion;

    @DatabaseField
    public LocalDateTime fechaModificacion;

    @DatabaseField
    public String ultimoUsuario;

    public Producto() {
    }

    public Producto(String nombre, String descripcion, int cantidad, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechaCreacion = LocalDateTime.now();
    }

    public int getAlmacenId() {
        return almacen != null ? almacen.id : -1;
    }

    public String getAlmacenNombre() {
        return almacen != null ? almacen.nombre : null;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                '}';
    }
}