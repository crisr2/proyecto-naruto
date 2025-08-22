package factory;

import builder.NinjaBuilder;
import object.Aldea;
import object.Jutsu;

public class OtoFactory extends NinjaFactory {
    private Aldea aldea = new Aldea("Oto", "País de los Campos de Arroz");

    @Override
    public NinjaBuilder crearNinja() {
        return new NinjaBuilder()
                .setAldea(aldea)
                .addJutsu(new Jutsu("Jutsu de la Destrucción de la Voz", "Ninjutsu", 75, 50))
                .addJutsu(new Jutsu("Jutsu de la Parálisis", "Genjutsu", 20, 10));
    }
}