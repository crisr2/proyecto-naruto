package object;

// Para las listas de ninjas pertenecientes
import java.util.ArrayList;
import java.util.List;

public class Aldea {
    private String nombre;
    private String nacion;
    private List<Ninja> ninjas = new ArrayList<>();

    public Aldea(String nombre, String nacion) {
        this.nombre = nombre;
        this.nacion = nacion;
    }

    public void AgregarPoblacion(Ninja ninja) {
        ninjas.add(ninja);
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNacion() { return nacion; }
    public void setNacion(String nacion) { this.nacion = nacion; }

    public List<Ninja> getNinjas() { return ninjas; }
}

