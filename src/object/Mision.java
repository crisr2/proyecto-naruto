package object;

import java.util.ArrayList;
import java.util.List;

public class Mision {
    private String nombre;
    private String descripcion;
    private RangoMision dificultad;
    private int recompensa;
    private RangoNinja rangoMinimo;
    private List<Ninja> participantes = new ArrayList<>();

    public Mision(String nombre, String descripcion, RangoMision dificultad, int recompensa, RangoNinja rangoMinimo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.recompensa = recompensa;
        this.rangoMinimo = rangoMinimo;
    }

    // Revisar esto, no c si sirve o no jaja 

    /*public void AgregarParticipante(Ninja ninja) {
        if (ninja.getRango().ordinal() >= rangoMinimo.ordinal()) {
            participantes.add(ninja);
        } else {
            System.out.println(" x " + ninja.getNombre() + " no cumple el rango mínimo.");
        }
    }*/

    // Getters y Setters
    public String getTitulo() { return nombre; }
    public void setTitulo(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public RangoMision getRango() { return dificultad; }
    public void setRango(RangoMision rango) { this.dificultad = rango; }

    public int getRecompensa() { return recompensa; }
    public void setRecompensa(int recompensa) { this.recompensa = recompensa; }

    public RangoNinja getRangoMinimo() { return rangoMinimo; }
    public void setRangoMinimo(RangoNinja rangoMinimo) { this.rangoMinimo = rangoMinimo; }

    public List<Ninja> getParticipantes() { return participantes; }
}
