import factory.*;
import builder.*;
import object.*;
import visitor.*;
import java.util.*;

public class App {
    public static void main(String[] args) {

        // Naruto - Genin
        NinjaFactory konohaFactory = new KonohaFactory();
        NinjaBuilder builderNaruto = konohaFactory.crearNinja();

        Ninja naruto = builderNaruto
                .setNombre("Naruto Uzumaki")
                .setRango(RangoNinja.Genin)
                .setAtaque(55)
                .setDefensa(40)
                .setChakra(80)
                .build(); 
        naruto.AgregarTecnica(new Jutsu("Modo Sabio", "Ninjutsu", 120, 50));

        // Gaara - Jonin
        NinjaFactory sunaFactory = new SunaFactory();
        NinjaBuilder builderGaara = sunaFactory.crearNinja();

        Ninja gaara = builderGaara
                .setNombre("Gaara")
                .setRango(RangoNinja.Jonin)
                .setAtaque(70)
                .setDefensa(65)
                .setChakra(100)
                .build();

        // Sakura - Chunin
        NinjaBuilder builderSakura = konohaFactory.crearNinja();
        Ninja sakura = builderSakura
                .setNombre("Sakura Haruno")
                .setRango(RangoNinja.Chunin)
                .setAtaque(45)
                .setDefensa(55)
                .setChakra(75)
                .build();

        // Haku - Jonin
        NinjaFactory kiriFactory = new KiriFactory();
        NinjaBuilder builderHaku = kiriFactory.crearNinja();

        Ninja haku = builderHaku
                .setNombre("Haku")
                .setRango(RangoNinja.Jonin)
                .setAtaque(65)
                .setDefensa(60)
                .setChakra(90)
                .build();
        haku.AgregarTecnica(new Jutsu("Senbon Preciso", "Ninjutsu", 70, 15));

        // Agregados
        System.out.println("- " + naruto.getNombre() + " de " + naruto.getAldea().getNombre() + " (" + naruto.getRango() + ")");
        System.out.println("- " + gaara.getNombre() + " de " + gaara.getAldea().getNombre() + " (" + gaara.getRango() + ")");
        System.out.println("- " + sakura.getNombre() + " de " + sakura.getAldea().getNombre() + " (" + sakura.getRango() + ")");
        System.out.println("- " + haku.getNombre() + " de " + haku.getAldea().getNombre() + " (" + haku.getRango() + ")");

      
        // Misiones
        // ----------------------------
        Mision misionD = new Mision("Recolectar Hierbas", "Misión básica de entrenamiento", RangoMision.D, 100);
        Mision misionC = new Mision("Escoltar Comerciante", "Proteger la caravana", RangoMision.C, 300);
        Mision misionB = new Mision("Cazar Bandidos", "Eliminar a los bandidos", RangoMision.B, 500);
        Mision misionA = new Mision("Infiltración", "Entrar en territorio enemigo", RangoMision.A, 800);
        Mision misionS = new Mision("Proteger al Daimyo", "Misión crítica de máxima dificultad", RangoMision.S, 1500);

        System.out.println("\n===== Misión D ====="); // Todos deben pasar
        misionD.agregarParticipante(naruto); //Genin
        misionD.agregarParticipante(sakura);  // Chunin
        misionD.agregarParticipante(gaara); // Jonin

        System.out.println("\n===== Misión C ====="); // Minimo Chunin o un líder jonin para poder pasar siendo Genin
        misionC.agregarParticipante(naruto); // No pasa, no hay Jonin y el es Genin
        misionC.agregarParticipante(haku);   // Si pasa, Jonin 
        misionC.agregarParticipante(naruto); // Si pasa, ya hay un lider Jonin
        misionC.agregarParticipante(sakura);  // Si pasa, Chunin
        misionC.agregarParticipante(gaara); // No pasa, cupo de 3 lleno

        System.out.println("\n===== Misión B ====="); // Minimo Chunin
        misionB.agregarParticipante(naruto); // No pasa, Genin
        misionB.agregarParticipante(sakura); // Si pasa, Chunin
        misionB.agregarParticipante(gaara);  // Si pasa, Jonin

        System.out.println("\n===== Misión A ====="); // Minimo Jonin
        misionA.agregarParticipante(sakura); // No pasa, Chunin
        misionA.agregarParticipante(gaara);  // Si pasa, Jonin

        System.out.println("\n===== Misión S ====="); // Minimo Jonin
        misionS.agregarParticipante(naruto); // No pasa, Genin
        misionS.agregarParticipante(haku);   // Si pasa, Jonin

      
        // Informes
        // ----------------------------
        System.out.println("\n\nSe ha solicitado la exportación del informe...");
        Set<Aldea> aldeas = new HashSet<>();
        aldeas.add(naruto.getAldea());
        aldeas.add(gaara.getAldea());
        aldeas.add(sakura.getAldea());
        aldeas.add(haku.getAldea());

        List<Mision> misiones = Arrays.asList(misionD, misionC, misionB, misionA, misionS);

        // Visitor - TXT
        TextVisitor textExport = new TextVisitor();
        textExport.exportarTodo(new ArrayList<>(aldeas), misiones);

        // Visitor - JSON
        JSONVisitor jsonExport = new JSONVisitor();
        jsonExport.exportarTodo(new ArrayList<>(aldeas), misiones);

        // Visitor - XML
        XMLVisitor xmlExport = new XMLVisitor();
        xmlExport.exportarTodo(new ArrayList<>(aldeas), misiones);
        }
}
