package object;

import java.util.ArrayList;
import java.util.List;
import visitor.Visitor;

public class Mision {
    private String nombre;
    private String descripcion;
    private RangoMision dificultad;
    private int recompensa;
    private RangoNinja rangoMinimo;
    private List<Ninja> participantes = new ArrayList<>();

    public Mision(String nombre, String descripcion, RangoMision dificultad, int recompensa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.recompensa = recompensa;
        this.rangoMinimo = rangoMinimoPorMision(dificultad);
    }
    
    public boolean agregarParticipante(Ninja ninja) {
        
        // max 3 personas por misión
        if (participantes.size() >= 3) {
            System.out.println("X No se pueden agregar más ninjas a la misión " + nombre);
            return false;
        }

        // Si en la misión rango C hay un jonin, pueden ir Genin.
        if (dificultad == RangoMision.C && ninja.getRango() == RangoNinja.Genin) {
            boolean hayJonin = participantes.stream().anyMatch(n -> n.getRango() == RangoNinja.Jonin);
            if (!hayJonin) {
                System.out.println("X " + ninja.getNombre() + " es Genin y no puede ir a una misión C sin un Jonin líder.");
                return false;
            } else {
                participantes.add(ninja);
                Ninja joninLider = participantes.stream()
                        .filter(n -> n.getRango() == RangoNinja.Jonin)
                        .findFirst()
                        .orElse(null);
                System.out.println(":D " + ninja.getNombre() + " (Genin) se unió a la misión " + nombre 
                                + " bajo el liderazgo de " + joninLider.getNombre() + " (Jonin)");
                return true;
            }
        }

        // Con el mapeo se ve el orden de rango minimo y el rango del ninja y se ve si puede o no unirse.
        if (ninja.getRango().ordinal() >= rangoMinimo.ordinal()) {
            participantes.add(ninja);
            System.out.println(":D " + ninja.getNombre() + " se unió a la misión " + nombre);
            return true;
        }

        System.out.println("X " + ninja.getNombre() + " no cumple el rango mínimo (" + rangoMinimo + ") para la misión " + nombre);
        return false;
    }

    // condicionales mapeo
    private RangoNinja rangoMinimoPorMision(RangoMision rangoMision) {
        switch (rangoMision) {
            case D: return RangoNinja.Genin;
            case C: return RangoNinja.Chunin;
            case B: return RangoNinja.Chunin;
            case A: return RangoNinja.Jonin;
            case S: return RangoNinja.Jonin;
            default: return RangoNinja.Genin;
        }
    }

    public void accept(Visitor visitor) {
        visitor.visitarMision(this);
    }
    
    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public RangoMision getDificultad() { return dificultad; }
    public void setDificultad(RangoMision dificultad) { 
        this.dificultad = dificultad; 
        this.rangoMinimo = rangoMinimoPorMision(dificultad); 
    }

    public int getRecompensa() { return recompensa; }
    public void setRecompensa(int recompensa) { this.recompensa = recompensa; }

    public RangoNinja getRangoMinimo() { return rangoMinimo; }
    public void setRangoMinimo(RangoNinja rangoMinimo) { this.rangoMinimo = rangoMinimo; }

    public List<Ninja> getParticipantes() { return participantes; }
    public void setParticipantes(List<Ninja> participantes) { this.participantes = participantes; }
}
