// creo que esto esta horriblemente mal LOL
package builder;
public class Ninja {
    // Nombre, Rango, Lista de Jutsus, Estadisticas: Ataque, Defensa, Chakra
    private String nombre;
    private String rango;
    private int ataque;
    private int defensa;
    private String chakra;
    private String[] listaJutsus;
    // Aldea no se si deberia de ser su propia clase o un string ?

    private Ninja(NinjaBuilder builder){
        this.nombre = builder.nombre;
        this.rango = builder.rango;
        this.ataque = builder.ataque;
        this.defensa = builder.defensa;
        this.chakra = builder.chakra;
        this.listaJutsus = builder.listaJutsus;
    }

    public static NinjaBuilder builder() {
        return new NinjaBuilder();
    }

    static class NinjaBuilder() {
        private String nombre;
        private String rango;
        private int ataque;
        private int defensa;
        private String chakra;
        private String[] listaJutsus;

        private NinjaBuilder(){
        }

        NinjaBuilder nombre(String nombre){
            this.nombre = nombre;
            return this;
        }
        
        NinjaBuilder rango(String rango){
            this.rango = rango;
            return this;
        }   

        NinjaBuilder ataque(int ataque){
            this.ataque = ataque;
            return this;
        }

        NinjaBuilder defensa(int defensa){
            this.defensa = defensa;
            return this;
        }        
        
        NinjaBuilder chakra(String chakra){
            this.chakra = chakra;
            return this;
        }
        
        NinjaBuilder listaJutsus(String[] listaJutsus){
            this.listaJutsus = listaJutsus;
            return this;
        }      
        
        Ninja build() {
            return new Ninja(this);
        }
    }
}