package visitor;

import object.*;

public class ExportVisitor implements Visitor {
    @Override
    public void visitarNinja(Ninja ninja) {
        System.out.println("===== Ninja =====");
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
        System.out.println("=================\n");
    }
}

