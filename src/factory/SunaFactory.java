package factory;

import builder.NinjaBuilder;
import object.Aldea;
import object.Jutsu;

public class SunaFactory extends NinjaFactory {
    private Aldea aldea = new Aldea("Suna", "País del Viento");

    @Override
    public NinjaBuilder crearNinja() {
        return new NinjaBuilder()
                .setAldea(aldea)
                .addJutsu(new Jutsu("Funeral del Desierto", "Ninjutsu", 75, 50))
                .addJutsu(new Jutsu("Jutsu de Marionetas", "Ninjutsu", 30, 20));
    }
}