package factory;

import builder.NinjaBuilder;
import object.Aldea;
import object.Jutsu;

public class KumoFactory extends NinjaFactory {
    private Aldea aldea = new Aldea("Kumo", "País del Rayo");

    @Override
    public NinjaBuilder crearNinja() {
        return new NinjaBuilder()
                .setAldea(aldea)
                .addJutsu(new Jutsu("Armadura de Rayo", "Ninjutsu", 75, 50))
                .addJutsu(new Jutsu("Jutsu de Clon de Rayo", "Ninjutsu", 35, 20));
    }
}