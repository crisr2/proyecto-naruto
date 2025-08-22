import factory.*;
import builder.*;
import object.*;
import visitor.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Aldea> aldeas = new ArrayList<>();

        // Naruto 
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

        // Gaara 
        NinjaFactory sunaFactory = new SunaFactory();
        NinjaBuilder builderGaara = sunaFactory.crearNinja();

        Ninja gaara = builderGaara
                .setNombre("Gaara")
                .setRango(RangoNinja.Jonin)
                .setAtaque(70)
                .setDefensa(65)
                .setChakra(100)
                .build();

        // Sakura 
        NinjaBuilder builderSakura = konohaFactory.crearNinja();
        Ninja sakura = builderSakura
                .setNombre("Sakura Haruno")
                .setRango(RangoNinja.Chunin)
                .setAtaque(45)
                .setDefensa(55)
                .setChakra(75)
                .build();

        // Haku 
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
        System.out.println("- " + naruto.getNombre() + " de " + naruto.getAldea().getNombre());
        System.out.println("- " + gaara.getNombre() + " de " + gaara.getAldea().getNombre());
        System.out.println("- " + sakura.getNombre() + " de " + sakura.getAldea().getNombre());
        System.out.println("- " + haku.getNombre() + " de " + haku.getAldea().getNombre());

        // Informe ver como va quedando
        if (!aldeas.contains(naruto.getAldea())) aldeas.add(naruto.getAldea());
        if (!aldeas.contains(gaara.getAldea())) aldeas.add(gaara.getAldea());
        if (!aldeas.contains(sakura.getAldea())) aldeas.add(sakura.getAldea());
        if (!aldeas.contains(haku.getAldea())) aldeas.add(haku.getAldea());

        // Visitor - TXT
        TextVisitor export = new TextVisitor();
        export.exportarTodo(aldeas);
    }
}
