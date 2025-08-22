package builder;

import object.*;
import java.util.ArrayList;
import java.util.List;

public class NinjaBuilder {
    private String nombre;
    private RangoNinja rango;
    private int ataque, defensa, chakra;
    private Aldea aldea;
    private List<Jutsu> jutsus = new ArrayList<>();

    public NinjaBuilder setNombre(String nombre) { this.nombre = nombre; return this; }
    public NinjaBuilder setRango(RangoNinja rango) { this.rango = rango; return this; }
    public NinjaBuilder setAtaque(int ataque) { this.ataque = ataque; return this; }
    public NinjaBuilder setDefensa(int defensa) { this.defensa = defensa; return this; }
    public NinjaBuilder setChakra(int chakra) { this.chakra = chakra; return this; }
    public NinjaBuilder setAldea(Aldea aldea) { this.aldea = aldea; return this; }
    public NinjaBuilder addJutsu(Jutsu jutsu) { jutsus.add(jutsu); return this; }

    public Ninja build() {
        Ninja n = new Ninja(nombre, rango, ataque, defensa, chakra, aldea);
        for (Jutsu j : jutsus) {
            n.AgregarTecnica(j);
        }
        if (aldea != null) aldea.AgregarPoblacion(n);
        return n;
    }
}