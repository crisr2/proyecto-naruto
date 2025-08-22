package object;

public class Jutsu {
    private String nombre;
    private String tipo; // ninjutsu, taijutsu, genjutsu
    private int daño; // Que inflige
    private int chakra; // Que gasta

    public Jutsu(String nombre, String tipo, int daño, int chakra) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.daño = daño;
        this.chakra = chakra;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getdaño() { return daño; }
    public void setdaño(int daño) { if (daño >= 0) this.daño = daño; }

    public int getchakra() { return chakra; }
    public void setchakra(int chakra) { if (chakra >= 0) this.chakra = chakra; }
}
