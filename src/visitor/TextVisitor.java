package visitor;

import java.util.List;

import object.*;

public class TextVisitor implements Visitor {

    @Override
    public void visitarNinja(Ninja ninja) {
        System.out.println("\n===== Ninja =====");
        System.out.println("Nombre: " + ninja.getNombre());
        System.out.println("Rango: " + ninja.getRango());
        System.out.println("Aldea: " + ninja.getAldea().getNombre());
        System.out.println("Ataque: " + ninja.getAtaque());
        System.out.println("Defensa: " + ninja.getDefensa());
        System.out.println("Chakra: " + ninja.getChakra());
        System.out.println("Jutsus:");
        for (Jutsu jutsu : ninja.getJutsus()) {
            System.out.println(" - " + jutsu.getNombre() + " (" + jutsu.getTipo() + 
                               ") Poder: " + jutsu.getdaño() + 
                               " Chakra: " + jutsu.getchakra());
        }
    }

    @Override
    public void visitarAldea(Aldea aldea) {
        System.out.println("\n= Aldea ============================");
        System.out.println("Nombre: " + aldea.getNombre() + " (" + aldea.getNacion() + ")");
        System.out.println("Número de ninjas: " + aldea.getNinjas().size());
        for (Ninja n : aldea.getNinjas()) {
            visitarNinja(n); // Reuso el método para mostrar cada ninja
        }
        System.out.println("\n======================== Fin Aldea =\n");
    }

    public void exportarTodo(List<Aldea> aldeas) {
        for (Aldea aldea : aldeas) {
            visitarAldea(aldea);
        }
    }
}
