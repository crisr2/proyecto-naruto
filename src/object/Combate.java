package object;

import java.util.List;
import java.util.Random;

public class Combate {

    private Ninja ninja1;
    private Ninja ninja2;
    private int hp1;
    private int hp2;
    private int chakra1;
    private int chakra2;
    private Random rand = new Random();

    public Combate(Ninja n1, Ninja n2) {
        this.ninja1 = n1;
        this.ninja2 = n2;
        this.hp1 = 100;
        this.hp2 = 100;
        this.chakra1 = n1.getChakra();
        this.chakra2 = n2.getChakra();
    }

    public void combatir() {
        System.out.println("Inicia combate " + ninja1.getNombre() + " vs " + ninja2.getNombre() + "\n"); // inicio aleatorio

        boolean turnoNinja1 = rand.nextBoolean();
        System.out.println((turnoNinja1 ? ninja1.getNombre() : ninja2.getNombre()) + " inicia atacando!\n");

        while(hp1 > 0 && hp2 > 0) {
            boolean pudoAtacar;
            if(turnoNinja1) {
                pudoAtacar = atacar(true);
                if(!pudoAtacar) {
                    System.out.println(ninja1.getNombre() + " no puede atacar, ha perdido. Combate finalizado!");
                    break;
                }
                if(hp2 <= 0) {
                    System.out.println(ninja2.getNombre() + " ha caido!");
                    break;
                }
            } else {
                pudoAtacar = atacar(false);
                if(!pudoAtacar) {
                    System.out.println(ninja2.getNombre() + " no puede atacar, ha perdido. Combate finalizado!");
                    break;
                }
                if(hp1 <= 0) {
                    System.out.println(ninja1.getNombre() + " ha caido!");
                    break;
                }
            }
            turnoNinja1 = !turnoNinja1;
            System.out.println();
        }

        System.out.println("\nCombate finalizado!");
        System.out.println(ninja1.getNombre() + " HP: " + hp1 + "/100, Chakra restante: " + chakra1);
        System.out.println(ninja2.getNombre() + " HP: " + hp2 + "/100, Chakra restante: " + chakra2);
    }

    private boolean atacar(boolean esNinja1) {
        Ninja atacante = esNinja1 ? ninja1 : ninja2;
        Ninja defensor = esNinja1 ? ninja2 : ninja1;
        int chakraTemporal = esNinja1 ? chakra1 : chakra2;

        // Jutsus disponibles
        List<Jutsu> jutsusDisponibles = new java.util.ArrayList<>();
        for (Jutsu j : atacante.getJutsus()) {
            if (j.getchakra() <= chakraTemporal) {
                jutsusDisponibles.add(j);
            }
        }

        if (jutsusDisponibles.isEmpty()) {
            return false;
        }

        // Jutsu aleatorio
        Jutsu j = jutsusDisponibles.get(rand.nextInt(jutsusDisponibles.size()));
        String ataqueNombre = j.getNombre();
        int dano = j.getdaño() + atacante.getAtaque() - (defensor.getDefensa());
        dano = Math.max(1, dano); // Daño mínimo 1, para evitar errores si hay mucha defensa
        int gastoChakra = j.getchakra();
        chakraTemporal -= gastoChakra;

        // Daño
        if(defensor == ninja1) hp1 = Math.max(0, hp1 - dano); // Vida mínima 0 
        else hp2 = Math.max(0, hp2 - dano); 

        // Chakra
        if(esNinja1) chakra1 = chakraTemporal;
        else chakra2 = chakraTemporal;

        System.out.println(atacante.getNombre() + " ataca con Jutsu \"" + ataqueNombre + "\"");
        System.out.println(atacante.getNombre() + " (Chakra Combate: " + chakraTemporal + "): -" + gastoChakra + " Chakra, Daño: " + dano);
        System.out.println(defensor.getNombre() + " HP restante: " + (defensor == ninja1 ? hp1 : hp2) + "/100");

        return true;
    }
}
