package factory;

import builder.NinjaBuilder;
import object.Aldea;
import object.Jutsu;

public class KonohaFactory extends NinjaFactory {
    private Aldea aldea = new Aldea("Konoha", "País del Fuego");

    @Override
    public NinjaBuilder crearNinja() {
        return new NinjaBuilder()
                .setAldea(aldea)
                .addJutsu(new Jutsu("Jutsu de Invocación: Boca de Sapo", "Ninjutsu", 85, 45))
                .addJutsu(new Jutsu("Taijutsu de la Hoja", "Taijutsu", 20, 5));
    }
}