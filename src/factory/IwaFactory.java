package factory;

import builder.NinjaBuilder;
import object.Aldea;
import object.Jutsu;

public class IwaFactory extends NinjaFactory {
    private Aldea aldea = new Aldea("Iwa", "País de la Tierra");

    @Override
    public NinjaBuilder crearNinja() {
        return new NinjaBuilder()
                .setAldea(aldea)
                .addJutsu(new Jutsu("Técnica del Golem de Tierra", "Ninjutsu", 80, 90))
                .addJutsu(new Jutsu("Jutsu del Muro de Tierra", "Ninjutsu", 20, 25));
    }
}