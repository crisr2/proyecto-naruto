package object;

import java.util.ArrayList;
import java.util.List;
import visitor.Visitor;

public class Ninja {
    private String nombre;
    private RangoNinja rango;
    private int ataque, defensa, chakra; // Estadisticas
    private Aldea aldea;
    private List<Jutsu> jutsus = new ArrayList<>();

    public Ninja(String nombre, RangoNinja rango, int ataque, int defensa, int chakra, Aldea aldea) {
        this.nombre = nombre;
        this.rango = rango;
        this.ataque = ataque;
        this.defensa = defensa;
        this.chakra = chakra;
        this.aldea = aldea;
    }

    public void AgregarTecnica(Jutsu jutsu) { jutsus.add(jutsu); }

    public void accept(Visitor visitor) {
        visitor.visitarNinja(this);
    }
 
    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public RangoNinja getRango() { return rango; }
    public void setRango(RangoNinja rango) { this.rango = rango; }

    public int getAtaque() { return ataque; }
    public void setAtaque(int ataque) { this.ataque = ataque; }

    public int getDefensa() { return defensa; }
    public void setDefensa(int defensa) { this.defensa = defensa; }

    public int getChakra() { return chakra; }
    public void setChakra(int chakra) { this.chakra = chakra; }

    public Aldea getAldea() { return aldea; }
    public void setAldea(Aldea aldea) { this.aldea = aldea; }

    public List<Jutsu> getJutsus() { return jutsus; }
}

