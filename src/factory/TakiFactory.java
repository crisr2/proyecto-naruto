package factory;

import builder.NinjaBuilder;
import object.Aldea;
import object.Jutsu;

public class TakiFactory extends NinjaFactory {
    private Aldea aldea = new Aldea("Taki", "País de la Cascada");

    @Override
    public NinjaBuilder crearNinja() {
        return new NinjaBuilder()
                .setAldea(aldea)
                .addJutsu(new Jutsu("Jutsu del Estilo de Agua: Gran Cascada", "Ninjutsu", 60, 45))
                .addJutsu(new Jutsu("Jutsu de la Prisión de Agua", "Ninjutsu", 30, 25));
    }
}