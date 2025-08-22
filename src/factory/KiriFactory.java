package factory;

import builder.NinjaBuilder;
import object.Aldea;
import object.Jutsu;

public class KiriFactory extends NinjaFactory {
    private Aldea aldea = new Aldea("Kiri", "País del Agua");

    @Override
    public NinjaBuilder crearNinja() {
        return new NinjaBuilder()
                .setAldea(aldea)
                .addJutsu(new Jutsu("Prisión de Agua", "Ninjutsu", 60, 40))
                .addJutsu(new Jutsu("Jutsu de Ocultación en la Niebla", "Ninjutsu", 15, 20));
    }
}