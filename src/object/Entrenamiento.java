package object;

public class Entrenamiento {
    private String stat;
    private String dificultad;
    private int incremento;

    public Entrenamiento(String stat, String dificultad) {
        this.stat = stat;
        this.dificultad = dificultad;
    }

    // Baja: 2 puntos, Media: 5 puntos y Alta 7 puntos
    public void entrenar(Ninja ninja) {
        switch (dificultad.toLowerCase()) {
            case "baja":
                incremento = 2; 
                break;
            case "media":
                incremento = 5; 
                break;
            case "alta":
                incremento = 7; 
                break;
        }

        int valorAnterior = 0;
        switch (stat) {
            case "Ataque":
                valorAnterior = ninja.getAtaque();
                ninja.setAtaque(valorAnterior + incremento);
                break;
            case "Defensa":
                valorAnterior = ninja.getDefensa();
                ninja.setDefensa(valorAnterior + incremento);
                break;
            case "Chakra":
                valorAnterior = ninja.getChakra();
                ninja.setChakra(valorAnterior + incremento);
                break;
            default:
                System.out.println("Stat invalida");
                return;
        }


        System.out.println('\n' + ninja.getNombre() + " entrenó " + stat + " con dificultad " + dificultad + 
                           ", ganó " + incremento + " puntos.");
        System.out.println(stat + ": " + valorAnterior + " --> " + (valorAnterior + incremento));
    }
}
